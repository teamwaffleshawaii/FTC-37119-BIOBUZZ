package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp Remote Control")

public class TeleOp extends LinearOpMode {
    Actuators actuator = new Actuators();
    @Override
    public void runOpMode(){
        float vertical;
        float horizontal;
        float pivot;
        actuator.init(hardwareMap);
        waitForStart();

        if (opModeIsActive() && !isStopRequested()){
            while (opModeIsActive() && !isStopRequested()){
                vertical = gamepad1.right_stick_y;
                horizontal = gamepad1.right_stick_x;
                pivot = gamepad1.left_stick_x;

                actuator.leftFrontMotor((pivot + vertical + horizontal));
                actuator.rightFrontMotor((-pivot + (vertical - horizontal)));
                actuator.leftBackMotor((pivot + (vertical - horizontal)));
                actuator.rightBackMotor((-pivot + vertical + horizontal));

                telemetry.addData("Left Front Power", actuator.leftFrontMotor.getPower());
                telemetry.addData("Right Front Power", actuator.rightFrontMotor.getPower());
                telemetry.addData("Left Back Power", actuator.leftBackMotor.getPower());
                telemetry.addData("Right Back Power", actuator.rightBackMotor.getPower());
                telemetry.update();
                //Use left trigger to control intake
                if (gamepad1.left_trigger > 0.5 || gamepad2.left_trigger > 0.5){
                    actuator.intakeOn();
                }
                else {
                    actuator.intakeOff();
                }

                //Use right trigger to turn on launch motor
                if (gamepad1.right_trigger > 0.5){
                    actuator.launchMotor(0.425); //Adjust the power as needed
                    //sleep(1500);
                    //actuator.launchServoOn();
                    //sleep(100);
                }
                else {
                    actuator.launchMotor(0);
                    //actuator.launchServoOff();
                }

                if (gamepad1.circle) {
                    actuator.windmillMotor(.50);
                }
                else {
                    actuator.windmillMotor(0);
                }
                //Use circle to launch
//                if (gamepad2.circle) {
//                    actuator.launchServoOn();
//                }
//                else {
//                    actuator.launchServoOff();
//                }
            }
        }
    }
}
