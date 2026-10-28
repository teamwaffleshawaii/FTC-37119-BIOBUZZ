package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous (name = "Autonomous")
public class Auto extends LinearOpMode {
    Actuators actuator = new Actuators();

    @Override
    public void runOpMode() {
        actuator.init(hardwareMap);
        waitForStart();
        if (opModeIsActive() && !isStopRequested()) {
            while (opModeIsActive() && !isStopRequested()) {
                // put what you want your robot to do here
                //actuator.launchMotor(1, 0.43);

                actuator.goForward(1, 0.2);
                actuator.goBackward(1, 0.2);
                actuator.turnLeft(1, 0.2);
                actuator.turnRight(1, 0.2);
                actuator.strafeLeft(1, 0.2);
                actuator.strafeRight(1, 0.2);

                actuator.launchMotor(0.45);
                sleep(3000);
                actuator.windmillMotor(1);
                sleep(1500);
                actuator.windmillMotor(0);
                actuator.launchMotor(0);
                sleep(1000);


                break;  //Break out of loop to stop the code
            }
        }
    }
}
