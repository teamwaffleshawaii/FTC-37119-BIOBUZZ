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
                actuator.IntakeServosOn();
                sleep(1000);
                actuator.IntakeServosOff();
                sleep(1000);
                break;
            }
        }
    }
}
