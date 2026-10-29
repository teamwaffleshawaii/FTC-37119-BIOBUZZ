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
| `subsystems/Sensors.java` | Sets up the IMU and reads yaw (heading). |
| `TeleOp.java` | Uses controller input and IMU heading to calculate wheel powers (field centric). |
| `Auto.java` | Runs the team's existing movement and launcher sequence. |

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
