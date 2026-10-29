package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Windmill;


/**
 * Robot-centric TeleOp: joystick directions are relative to the robot.
 * This class reads the gamepads; subsystems send commands to their mechanisms.
 * Controls and action order remain those of FTC 37119.
 */
@TeleOp(name = "TeleOp Robot Centric Drive")
public class TeleOpRobotCentric extends LinearOpMode {
    // Each subsystem owns one mechanism; this OpMode decides when it acts.
    private final Drivetrain drivetrain = new Drivetrain();
    private final Intake intake = new Intake();
    private final Launcher launcher = new Launcher();
    private final Windmill windmill = new Windmill();

    @Override
    public void runOpMode() {
        float vertical;
        float horizontal;
        float pivot;
        // Initialize each mechanism before START, using FTC 37119's own settings.
        drivetrain.init(hardwareMap);
        intake.init(hardwareMap);
        launcher.init(hardwareMap);
        windmill.init(hardwareMap);
        // Initialization finishes before the Driver Station START button is pressed.
        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            while (opModeIsActive() && !isStopRequested()) {
                telemetry.update();
                drive();
                intake();
                launch();
                telemetry.update();
            }
        }
    }

    /** Read driver input and apply the team's unchanged wheel-power calculations. */
    public void drive() {
        float vertical;
        float horizontal;
        float pivot;

        vertical = gamepad1.left_stick_y;
        horizontal = gamepad1.left_stick_x;
        pivot = gamepad1.right_stick_x;

        drivetrain.leftFrontMotor((pivot + vertical + horizontal));
        drivetrain.rightFrontMotor((-pivot + (vertical - horizontal)));
        drivetrain.leftBackMotor((pivot + (vertical - horizontal)));
        drivetrain.rightBackMotor((-pivot + vertical + horizontal));

        telemetry.addData("Left Front Power", drivetrain.getLeftFrontPower());
        telemetry.addData("Right Front Power", drivetrain.getRightFrontPower());
        telemetry.addData("Left Back Power", drivetrain.getLeftBackPower());
        telemetry.addData("Right Back Power", drivetrain.getRightBackPower());
    }


    public void intake() {
        // Either gamepad can control intake. A wins if A and B are held together.
        if (gamepad1.a || gamepad2.a) {
            intake.on();

        }

        else if (gamepad1.b || gamepad2.b){
            intake.reverse();
        }

        else {
            intake.off();
        }
    }

    /** Launcher is latched: releasing X does not turn it off; Y does. */
    public void launch() {
        if (gamepad2.x) {
            launcher.setMotorPower(0.5); //Adjust the power as needed
        }
        else if (gamepad2.y){
            launcher.setMotorPower(0);
        }

        // Windmill runs only while gamepad 2 right bumper is held.
        if (gamepad2.right_bumper) {
            windmill.setMotorPower(.50);
        }
        else {
            windmill.setMotorPower(0);
        }

    }
}