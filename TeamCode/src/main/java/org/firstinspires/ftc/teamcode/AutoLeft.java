package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Windmill;

/**
 * Autonomous sequence: the OpMode chooses the order and waits between actions.
 * Drivetrain handles encoder moves; Launcher and Windmill handle shooting.
 * Controls and action order remain those of FTC 37119.
 */
@Autonomous (name = "AutoLeft")
public class AutoLeft extends LinearOpMode {
    // Each subsystem owns one mechanism; this OpMode decides when it acts.
    private final Drivetrain drivetrain = new Drivetrain();
    private final Intake intake = new Intake();
    private final Launcher launcher = new Launcher();
    private final Windmill windmill = new Windmill();

    @Override
    public void runOpMode() {
        // Initialize each mechanism before START, using FTC 37119's own settings.
        drivetrain.init(hardwareMap);
        intake.init(hardwareMap);
        launcher.init(hardwareMap);
        windmill.init(hardwareMap);
        // Initialization finishes before the Driver Station START button is pressed.
        waitForStart();
        if (opModeIsActive() && !isStopRequested()) {
            while (opModeIsActive() && !isStopRequested()) {
                // put what you want your robot to do here

                // Each drive call blocks until its existing busy condition becomes false.
                // Keep the same movement order, rotations, and power.

                // Spin up for 3000 ms, feed for 1500 ms, then stop and wait 1000 ms.
                drivetrain.goForward(0.3,0.4);
                launcher.setMotorVelocity(1315);
                sleep(3500);
                windmill.setMotorPower(0.35);
                sleep(100);
                intake.on();
                sleep(1500);
                windmill.setMotorPower(0);
                intake.off();
                launcher.setMotorVelocity(0);
                sleep(1000);
                drivetrain.goForward(0.3,0.4);
                sleep(150);
                drivetrain.strafeLeft(4,0.4);
                sleep(100);
                intake.on();
                drivetrain.goBackward(0.6, 0.4);
                sleep(250);
                intake.off();
                drivetrain.goForward(0.6,0.4);
                drivetrain.strafeRight(4,0.4);
                sleep(250);
                drivetrain.goBackward(0.3,0.4);
                launcher.setMotorVelocity(1315);
                sleep(3500);
                windmill.setMotorPower(0.35);
                sleep(100);
                intake.on();
                sleep(1500);
                windmill.setMotorPower(0);
                intake.off();
                launcher.setMotorVelocity(0);
                sleep(1000);
                drivetrain.strafeLeft(1,0.8);
                drivetrain.goForward(6,0.8);
                drivetrain.strafeLeft(1,0.8);

                break;  //Break out of loop to stop the code
            }
        }
    }
}
