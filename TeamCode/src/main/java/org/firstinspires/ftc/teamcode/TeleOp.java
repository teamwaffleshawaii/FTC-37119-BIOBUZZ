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
                //actuator.motorLeft(gamepad1.left_stick_y*0.5);
                //actuator.motorRight(gamepad1.right_stick_y*0.5);

                if (gamepad1.cross){
                    //actuator.clawGrabs();
                    actuator.IntakeMotor(0.5);
                }
                else if (gamepad1.circle){
                    //actuator.clawDrops();
                    actuator.IntakeMotor(0);
                }
            }
        }
    }
}
