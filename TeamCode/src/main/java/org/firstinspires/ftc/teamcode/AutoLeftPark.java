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
@Autonomous (name = "AutoLeftPark")
public class AutoLeftPark extends LinearOpMode {
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
                drivetrain.goForward(1, 0.4);




                break;  //Break out of loop to stop the code
            }
        }
    }
}
