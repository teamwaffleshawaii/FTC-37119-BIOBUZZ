package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous (name = "Autonomous")
public class Auto extends LinearOpMode {
    // One container connects the configured hardware to each subsystem.
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        // Initialization finishes before the Driver Station START button is pressed.
        waitForStart();
        if (opModeIsActive() && !isStopRequested()) {
            while (opModeIsActive() && !isStopRequested()) {
                // put what you want your robot to do here
                //robot.launcher.launchMotor(1, 0.43);

                // Each drive call blocks until its existing busy condition becomes false.
                // Keep the same movement order, rotations, and power.
                robot.drivetrain.goForward(1, 0.2);
                robot.drivetrain.goBackward(1, 0.2);
                robot.drivetrain.turnLeft(1, 0.2);
                robot.drivetrain.turnRight(1, 0.2);
                robot.drivetrain.strafeLeft(1, 0.2);
                robot.drivetrain.strafeRight(1, 0.2);

                // Spin up for 3000 ms, feed for 1500 ms, then stop and wait 1000 ms.
                robot.launcher.launchMotor(0.45);
                sleep(3000);
                robot.windmill.windmillMotor(1);
                sleep(1500);
                robot.windmill.windmillMotor(0);
                robot.launcher.launchMotor(0);
                sleep(1000);


                break;  //Break out of loop to stop the code
            }
        }
    }
}
