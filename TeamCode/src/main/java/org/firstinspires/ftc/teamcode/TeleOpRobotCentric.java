package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Robot;


@TeleOp(name = "TeleOp Robot Centric Drive")
public class TeleOpRobotCentric extends LinearOpMode {
    // One container connects the configured hardware to each subsystem.
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        float vertical;
        float horizontal;
        float pivot;
        robot.init(hardwareMap);
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

        robot.drivetrain.leftFrontMotor((pivot + vertical + horizontal));
        robot.drivetrain.rightFrontMotor((-pivot + (vertical - horizontal)));
        robot.drivetrain.leftBackMotor((pivot + (vertical - horizontal)));
        robot.drivetrain.rightBackMotor((-pivot + vertical + horizontal));

        telemetry.addData("Left Front Power", robot.leftFrontMotor.getPower());
        telemetry.addData("Right Front Power", robot.rightFrontMotor.getPower());
        telemetry.addData("Left Back Power", robot.leftBackMotor.getPower());
        telemetry.addData("Right Back Power", robot.rightBackMotor.getPower());
    }


    public void intake() {
        // Either gamepad can control intake. A wins if A and B are held together.
        if (gamepad1.a || gamepad2.a) {
            robot.intake.intakeIn();

        }

        else if (gamepad1.b || gamepad2.b){
            robot.intake.intakeOut();
        }

        else {
            robot.intake.intakeOff();
        }
    }

    /** Launcher is latched: releasing X does not turn it off; Y does. */
    public void launch() {
        if (gamepad2.x) {
            robot.launcher.launchMotor(0.5); //Adjust the power as needed
        }
        else if (gamepad2.y){
            robot.launcher.launchMotor(0);
        }

        // Windmill runs only while gamepad 2 right bumper is held.
        if (gamepad2.right_bumper) {
            robot.windmill.windmillMotor(.50);
        }
        else {
            robot.windmill.windmillMotor(0);
        }

    }
}