# FTC 37119: finding your way around the robot code

A **subsystem** is a Java class that groups the actions for one mechanism. An
**OpMode** is the program selected on the Driver Station. The OpMode decides when
to act, and the subsystem sends the hardware commands.

## Where to look

All Java files below live in `src/main/java/org/firstinspires/ftc/teamcode/`.

| File | Responsibility |
| --- | --- |
| `subsystems/Drivetrain.java` | Four wheel-power methods and the six original encoder movement methods. |
| `subsystems/Intake.java` | Coordinates the intake motor and two intake servos for in, out, and off. |
| `subsystems/Launcher.java` | Applies launcher motor power. |
| `subsystems/Windmill.java` | Applies windmill motor power. |
| `subsystems/RobotSensors.java` | Sets up the IMU and reads yaw (heading). |
| `TeleOpRobotCentric.java` | Reads the controllers and calculates wheel powers relative to the robot. |
| `TeleOpFieldCentric.java` | Uses controller input and IMU heading to calculate wheel powers. |
| `Auto.java` | Runs the team's existing movement and launcher sequence. |

## Follow one command

In TeleOp, `intake()` checks the A and B buttons. If A is pressed, it calls
`intake.on()`: the `intake` object receives the request, and `on()` commands the
intake motor and both servos.

Each OpMode declares its subsystems, for example:

```java
private final Intake intake = new Intake();
```

`new Intake()` creates the Java object. Before START, `intake.init(hardwareMap)`
looks up and configures its actual motor and servos. `private` keeps hardware
references inside the subsystem. `final` on the OpMode's subsystem reference
prevents replacing that subsystem object; it does not prevent hardware commands.
For telemetry, the drivetrain exposes methods such as `getLeftFrontPower()`
instead of exposing the motor objects.

## Same organization as FTC 35873

Both teams now use direct subsystem fields in OpModes and an `init(hardwareMap)`
method in each subsystem. Intake actions are `on()`, `reverse()`, and `off()`;
launcher power uses `setMotorPower()`; IMU access lives in `RobotSensors`.
FTC 37119 also has its own `Windmill` subsystem with `setMotorPower()`.
`Robot.java` and its forwarding methods are no longer needed and have been removed.
For example, `robot.intake.intakeIn()` becomes `intake.on()`.

The style is shared, but the teams have different hardware and behavior. Do not
copy 35873's configuration names, servo positions, IMU mounting, controller
mapping, or motor powers into 37119. The 37119 drive equations remain in its
OpModes, and its existing individual wheel-command methods preserve motor-command
order and run-mode behavior. Its encoder waits and heading reset also retain
37119's behavior rather than adopting 35873's different implementation.

## Existing controls

| Input | Result |
| --- | --- |
| Gamepad 1 left stick | Translation using that TeleOp's original formulas. |
| Gamepad 1 right stick X | Turning. |
| A on either gamepad | Intake in; takes priority if B is also held. |
| B on either gamepad | Intake out when neither A is held. |
| Neither A nor B | Intake motor power 0; both servos commanded to 0.5. |
| Gamepad 2 X | Launcher power 0.5; it stays on after release. |
| Gamepad 2 Y | Launcher power 0, unless X is also held (X has priority). |
| Gamepad 2 right bumper | Windmill power 0.50 while held, 0 otherwise. |
| Gamepad 1 Start, field-centric only | Updates the stored heading reference. |
| Gamepad 1 right bumper, field-centric only | Uses the existing fixed angle instead of gyro heading. |
| Gamepad 1 D-pad, field-centric only | Overrides the corresponding translation input with the existing ±0.5 value. |

`if` / `else if` ordering gives the earlier condition priority. A missing final
`else` in launcher control is deliberate preservation: no new launcher command
is sent when neither X nor Y is held. Motor power remains at its last command.

## Encoders and autonomous

`RUN_WITHOUT_ENCODER` is used for each manual motor-power command.
The autonomous movement methods keep their original sequence: select encoder
mode, reset counts, assign targets, select `RUN_TO_POSITION`, apply power, wait.
The target conversion remains `537.7 * rotation`, converted to an integer. Signed
wheel targets distinguish forward, backward, turns, and strafes. `rotation` is
motor rotations, not a turn angle in degrees.

Autonomous still runs forward, backward, left turn, right turn, left strafe,
right strafe (each 1 rotation at 0.2). It then runs the launcher at 0.45, waits
3000 milliseconds, runs the windmill at 1 for 1500 milliseconds, stops windmill
and launcher, waits 1000 milliseconds, and breaks out of the loop.

The movement wait uses `&&` (AND): it continues only while **all four** motors
are busy. It exits when any one reports not busy. It has no stop-request check,
timeout, or final zero-power command. These are pre-existing behaviors, not new
features of subsystems. The outer autonomous loop's stop checks do not run while
execution is inside a movement wait. Changes here should be a separate tested
behavior change.

## What this reorganization preserves

Hardware names, motor directions, brake/float settings, button priority, drive
formulas, power values, encoder targets, delays, telemetry, OpMode names, and
post-initialization action order remain the same. Initialization now groups the
devices by subsystem instead of configuring them all in one container. Each
device receives the same lookup and settings in the same order; no new power,
servo-position, or run-mode commands are added during initialization.

Heading reset still follows `drive()` in field-centric TeleOp;
`RobotSensors.getHeading(...)` still returns degrees regardless of its argument;
the existing telemetry labeled radians still uses its original /90 formula.
These details are documented rather than silently corrected.

For future changes, look in the OpMode for **controls and sequence**, the
subsystem's action methods for **mechanism behavior**, and that subsystem's
`init()` for **hardware setup**.

## Checking the change

From the repository root, run `python3 tools/verify_subsystem_refactor.py` to
compare executable source against the commit before this style change. It checks
all 15 mechanism action bodies, each device's initialization commands, power
getters, all OpMode method bodies and registration names, and sensor behavior.
It accounts for the new names and direct subsystem calls. This is a one-time
refactor check, not a general robot test suite.

A full Android build remains unverified: the editing environment could not
download Gradle. Build in Android Studio before deployment, then verify both
TeleOps and autonomous on the robot. Source comparison cannot establish physical
behavior.
