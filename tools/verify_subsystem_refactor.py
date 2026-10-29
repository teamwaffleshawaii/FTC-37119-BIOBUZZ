"""Verify the 35873-style reorganization preserves 37119's existing action code.

Run from the repository root. This is a one-time source comparison, not an SDK
build or robot simulation. Update/remove it when intentionally changing behavior.
"""
from pathlib import Path
import re
import subprocess

BASE = '5134dcfc749cb72616a9550c27e7bf9f66eadd38'
ROOT = Path('TeamCode/src/main/java/org/firstinspires/ftc/teamcode')
GROUPS = {
    'Drivetrain': ['leftBackMotor', 'leftFrontMotor', 'rightBackMotor', 'rightFrontMotor'],
    'Intake': ['intakeMotor', 'leftIntakeServo', 'rightIntakeServo'],
    'Launcher': ['launchMotor'], 'Windmill': ['windmillMotor'],
}
ACTIONS = {
    'Drivetrain': {n: n for n in GROUPS['Drivetrain'] + [
        'goBackward', 'goForward', 'turnRight', 'turnLeft', 'strafeRight', 'strafeLeft']},
    'Intake': {'intakeIn': 'on', 'intakeOut': 'reverse', 'intakeOff': 'off'},
    'Launcher': {'launchMotor': 'setMotorPower'},
    'Windmill': {'windmillMotor': 'setMotorPower'},
}

def original(path):
    return subprocess.check_output(['git', 'show', f'{BASE}:{ROOT}/{path}.java'], text=True)

def clean(source):
    pattern = r'"(?:\\.|[^"\\])*"|/\*[\s\S]*?\*/|//[^\n]*'
    source = re.sub(pattern, lambda m: m[0] if m[0].startswith('"') else '', source)
    return re.sub(r'\s+', '', source)

def body(source, name):
    source = clean(source)
    match = re.search(r'public(?:void|double)' + name + r'\([^)]*\)\{', source)
    assert match, name
    start = match.end()
    end, depth = start, 1
    while depth:
        depth += (source[end] == '{') - (source[end] == '}')
        end += 1
    return source[start:end - 1]

robot_init = body(original('subsystems/Robot'), 'init')
for cls, fields in GROUPS.items():
    current = (ROOT / 'subsystems' / (cls + '.java')).read_text()
    previous = original('subsystems/' + cls)
    for old, new in ACTIONS[cls].items():
        assert body(previous, old) == body(current, new), (cls, old)
    # Initialization now groups devices by subsystem. Each individual device must
    # still receive exactly the same lookup and configuration, in the same order.
    current_init = body(current, 'init').replace('hardwareMap', 'hwMap')
    expected = []
    for field in fields:
        commands = [s for s in robot_init.split(';') if s.startswith((field+'=', field+'.'))]
        expected.extend(commands)
    assert current_init == ';'.join(expected) + ';', cls
    # No public hardware fields or constructor parameters: init owns the devices.
    assert not re.search(r'public\s+(?:DcMotor|Servo)\s+', current), cls

for side in ['LeftFront', 'RightFront', 'LeftBack', 'RightBack']:
    drivetrain = (ROOT / 'subsystems/Drivetrain.java').read_text()
    assert body(drivetrain, 'get'+side+'Power') == 'return'+side[0].lower()+side[1:]+'Motor.getPower();'

for name, methods in {
    'Auto': ['runOpMode'],
    'TeleOpRobotCentric': ['runOpMode', 'drive', 'intake', 'launch'],
    'TeleOpFieldCentric': ['runOpMode', 'drive', 'resetAngle', 'getHeading', 'intake', 'launch'],
}.items():
    previous = original(name)
    current = (ROOT / (name + '.java')).read_text()
    for method in methods:
        old, new = body(previous, method), body(current, method)
        for cls, actions in ACTIONS.items():
            owner = cls.lower()
            for old_name, new_name in actions.items():
                new = new.replace(owner+'.'+new_name+'(', 'robot.'+owner+'.'+old_name+'(')
        for side in ['LeftFront', 'RightFront', 'LeftBack', 'RightBack']:
            new = new.replace('drivetrain.get'+side+'Power()', 'robot.'+side[0].lower()+side[1:]+'Motor.getPower()')
        new = new.replace('sensors.', 'sensor.')
        init = ''.join(cls.lower()+'.init(hardwareMap);' for cls in GROUPS)
        new = new.replace(init, 'robot.init(hardwareMap);')
        assert old == new, (name, method)
    # Keep Driver Station registration names and the OpMode class names.
    for pattern in [r'@(?:TeleOp|Autonomous)\([^)]*\)', r'public\s+class\s+\w+']:
        assert re.findall(pattern, previous) == re.findall(pattern, current), name

previous = original('subsystems/Sensors')
current = (ROOT / 'subsystems/RobotSensors.java').read_text()
for method in ['init', 'getHeading']:
    assert body(previous, method) == body(current, method), method
assert not (ROOT / 'subsystems/Robot.java').exists()
assert not (ROOT / 'subsystems/Sensors.java').exists()
print('PASS: 15 mechanism action bodies, per-device initialization, power getters,')
print('all OpMode method bodies/registration names, and sensor behavior preserved.')
