package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "TeleOp Robot Centric Drive")
public class TeleOpRobotCentric extends LinearOpMode {
    // One container connects the configured hardware to each subsystem.
    Actuators actuator = new Actuators();

    @Override
    public void runOpMode() {
        float vertical;
        float horizontal;
        float pivot;
        actuator.init(hardwareMap);
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

        actuator.drivetrain.leftFrontMotor((pivot + vertical + horizontal));
        actuator.drivetrain.rightFrontMotor((-pivot + (vertical - horizontal)));
        actuator.drivetrain.leftBackMotor((pivot + (vertical - horizontal)));
        actuator.drivetrain.rightBackMotor((-pivot + vertical + horizontal));

        telemetry.addData("Left Front Power", actuator.leftFrontMotor.getPower());
        telemetry.addData("Right Front Power", actuator.rightFrontMotor.getPower());
        telemetry.addData("Left Back Power", actuator.leftBackMotor.getPower());
        telemetry.addData("Right Back Power", actuator.rightBackMotor.getPower());
    }


    public void intake() {
        // Either gamepad can control intake. A wins if A and B are held together.
        if (gamepad1.a || gamepad2.a) {
            actuator.intake.intakeIn();

        }

        else if (gamepad1.b || gamepad2.b){
            actuator.intake.intakeOut();
        }

        else {
            actuator.intake.intakeOff();
        }
    }

    /** Launcher is latched: releasing X does not turn it off; Y does. */
    public void launch() {
        if (gamepad2.x) {
            actuator.launcher.launchMotor(0.5); //Adjust the power as needed
        }
        else if (gamepad2.y){
            actuator.launcher.launchMotor(0);
        }

        // Windmill runs only while gamepad 2 right bumper is held.
        if (gamepad2.right_bumper) {
            actuator.windmill.windmillMotor(.50);
        }
        else {
            actuator.windmill.windmillMotor(0);
        }

    }
}