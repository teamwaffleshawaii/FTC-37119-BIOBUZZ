package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp Remote Control")

public class TeleOp extends LinearOpMode {
    Actuators actuator = new Actuators();
    @Override
    public void runOpMode(){
        actuator.init(hardwareMap);
        waitForStart();

        if (opModeIsActive() && !isStopRequested()){
            while (opModeIsActive() && !isStopRequested()){
                actuator.leftBackMotor(gamepad1.left_stick_y * 0.5); //adjust the speed as needed
                actuator.rightBackMotor(gamepad1.right_stick_y * 0.5); //adjust the speed as needed

                //Use left trigger to control intake
                if (gamepad1.left_trigger > 0.5){
                    actuator.intakeOn();
                }
                else {
                    actuator.intakeOff();
                }

                //Use right trigger to turn on launch motor
                if (gamepad1.right_trigger > 0.5){
                    actuator.launchMotor(0.75); //Adjust the power as needed
                }
                else {
                    actuator.launchMotor(0);
                }

                //Use circle to launch
                if (gamepad1.circle) {
                    actuator.launchServoOn();
                }
                else {
                    actuator.launchServoOff();
                }
            }
        }
    }
}
