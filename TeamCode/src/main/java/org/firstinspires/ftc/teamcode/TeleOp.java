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
                actuator.motorLeft(gamepad1.left_stick_y*0.5);
                actuator.motorRight(gamepad1.right_stick_y*0.5);

                if (gamepad1.left_trigger > 0.5){
                    actuator.IntakeServosOn();
                    actuator.IntakeMotor(1);
                }
                else {
                    actuator.IntakeServosOff();
                    actuator.IntakeMotor(0);
                }
                if (gamepad1.right_trigger > 0.5){
                    actuator.setLaunchMotorOn(1); //Adjust the power as needed
                }
                else {
                    actuator.setLaunchMotorOff();
                }

                if (gamepad1.circleWasReleased()) {
                    actuator.launchServo();
                }
                else if (gamepad1.circleWasPressed()) {
                    actuator.launchServo();
                }
            }
        }
    }
}
