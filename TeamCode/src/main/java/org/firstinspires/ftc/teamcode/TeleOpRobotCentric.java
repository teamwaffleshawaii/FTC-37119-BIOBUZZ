package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "TeleOp Robot Centric Drive")
public class TeleOpRobotCentric extends LinearOpMode {
    Actuators actuator = new Actuators();

    @Override
    public void runOpMode() {
        float vertical;
        float horizontal;
        float pivot;
        actuator.init(hardwareMap);
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

    public void drive() {
        float vertical;
        float horizontal;
        float pivot;

        vertical = gamepad1.left_stick_y;
        horizontal = gamepad1.left_stick_x;
        pivot = gamepad1.right_stick_x;

        actuator.leftFrontMotor((pivot + vertical + horizontal));
        actuator.rightFrontMotor((-pivot + (vertical - horizontal)));
        actuator.leftBackMotor((pivot + (vertical - horizontal)));
        actuator.rightBackMotor((-pivot + vertical + horizontal));

        telemetry.addData("Left Front Power", actuator.leftFrontMotor.getPower());
        telemetry.addData("Right Front Power", actuator.rightFrontMotor.getPower());
        telemetry.addData("Left Back Power", actuator.leftBackMotor.getPower());
        telemetry.addData("Right Back Power", actuator.rightBackMotor.getPower());
    }


    public void intake() {
        //Use left trigger to control intake
        if (gamepad1.a || gamepad2.a) {
            actuator.intakeIn();

        }

        else if (gamepad1.b || gamepad2.b){
            actuator.intakeOut();
        }

        else {
            actuator.intakeOff();
        }
    }

    public void launch() {
        if (gamepad2.x) {
            actuator.launchMotor(0.5); //Adjust the power as needed
        }
        else if (gamepad2.y){
            actuator.launchMotor(0);
        }

        if (gamepad2.right_bumper) {
            actuator.windmillMotor(.50);
        }
        else {
            actuator.windmillMotor(0);
        }

    }
}