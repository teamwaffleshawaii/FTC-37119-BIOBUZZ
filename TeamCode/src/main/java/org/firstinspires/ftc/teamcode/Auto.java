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
                actuator.goForward(1, 0.3);
                sleep(1000);
                actuator.goBackward(1, 0.3);
                sleep(1000);
                actuator.turnLeft(1, 0.3);
                sleep(1000);
                actuator.turnRight(1, 0.3);
                sleep(1000);

                actuator.intakeOn();
                sleep(1000);
                actuator.intakeOff();
                sleep(1000);

                actuator.launchMotor(0.75);
                sleep(1000);
                actuator.launchMotor(0);
                sleep(1000);

                actuator.launchServoOn();
                sleep(1000);
                actuator.launchServoOff();
                sleep(1000);


                break;  //Break out of loop to stop the code
            }
        }
    }
}
