"""Check this refactor against its fixed starting commit. Run from the repository root.

This checks source preservation, not FTC SDK compilation or physical robot behavior.
It is a one-time refactor check: intentional future logic changes should update/remove it.
"""
from pathlib import Path
import re
import subprocess

BASE = '6ab3822c28096874f5d8536c242f770e1545c499'
ROOT = Path('TeamCode/src/main/java/org/firstinspires/ftc/teamcode')

def original(name):
    return subprocess.check_output(['git', 'show', f'{BASE}:{ROOT}/{name}.java'], text=True)

def clean(source):
    # Preserve quoted strings while discarding comments and insignificant whitespace.
    pattern = r'"(?:\\.|[^"\\])*"|/\*[\s\S]*?\*/|//[^\n]*'
    source = re.sub(pattern, lambda m: m[0] if m[0].startswith('"') else '', source)
    return re.sub(r'\s+', '', source)

def body(source, name):
    source = clean(source)
    start = source.index('{', source.index('publicvoid' + name + '('))
    end, depth = start + 1, 1
    while depth:
        depth += (source[end] == '{') - (source[end] == '}')
        end += 1
    return source[start + 1:end - 1]

groups = {
    'Drivetrain': ['leftBackMotor', 'leftFrontMotor', 'rightBackMotor', 'rightFrontMotor',
                   'goBackward', 'goForward', 'turnRight', 'turnLeft', 'strafeRight', 'strafeLeft'],
    'Intake': ['intakeIn', 'intakeOut', 'intakeOff'],
    'Launcher': ['launchMotor'], 'Windmill': ['windmillMotor'],
}
old = original('Actuators')
new = (ROOT / 'Robot.java').read_text()
for cls, methods in groups.items():
    subsystem = (ROOT / 'subsystems' / (cls + '.java')).read_text()
    for method in methods:
        assert body(old, method) == body(subsystem, method), method
        args = 'rotation,power' if method.startswith(('go', 'turn', 'strafe')) else (
            '' if method.startswith('intake') else 'power')
        assert body(new, method) == f'{cls.lower()}.{method}({args});', method
init = body(new, 'init')
expected_setup = body(old, 'init')
assert init.startswith(expected_setup), 'Hardware initialization changed'
assert init[len(expected_setup):] == (
    'drivetrain=newDrivetrain(leftBackMotor,leftFrontMotor,rightBackMotor,rightFrontMotor);'
    'intake=newIntake(intakeMotor,leftIntakeServo,rightIntakeServo);'
    'launcher=newLauncher(launchMotor);windmill=newWindmill(windmillMotor);'
)
for name in ['Auto', 'TeleOpRobotCentric', 'TeleOpFieldCentric', 'Sensors']:
    current = (ROOT / (name + '.java')).read_text()
    current = current.replace('Robot robot = new Robot();', 'Actuators robot = new Actuators();')
    current = re.sub(r'\brobot\b', 'actuator', current)
    for cls in groups:
        current = current.replace('actuator.' + cls.lower() + '.', 'actuator.')
    assert clean(original(name)) == clean(current), name
print('PASS: 15 moved method bodies, 15 compatibility delegates, hardware setup,')
print('and all three OpModes plus Sensors preserve original executable source.')
