package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Windmill;

@Autonomous (name = "LAUNCHER TEST")
public class LauncherTEST extends LinearOpMode {
    private final Drivetrain drivetrain = new Drivetrain();
    private final Intake intake = new Intake();
    private final Launcher launcher = new Launcher();
    private final Windmill windmill = new Windmill();

    private double targetVelocity = 0;

    @Override
    public void runOpMode() {
        drivetrain.init(hardwareMap);
        intake.init(hardwareMap);
        launcher.init(hardwareMap);
        windmill.init(hardwareMap);

        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            setLauncher(1095);
            waitAndLog("Spin up", 2250);

            windmill.setMotorPower(0.45);
            waitAndLog("Shot 1", 1550);

            windmill.setMotorPower(0);
            waitAndLog("Recover", 750);

            windmill.setMotorPower(0.45);
            waitAndLog("Shot 2", 750);

            setLauncher(0);
            waitAndLog("Spin down", 500);
        }
    }

    private void setLauncher(double velocity) {
        targetVelocity = velocity;
        launcher.setMotorVelocity(velocity);
    }

    /** Waits for the given time while updating telemetry. */
    private void waitAndLog(String phase, long ms) {
        ElapsedTime timer = new ElapsedTime();
        while (opModeIsActive() && timer.milliseconds() < ms) {
            double velocity = launcher.getMotorVelocity();
            telemetry.addData("Phase", phase);
            telemetry.addData("Target velocity", targetVelocity);
            telemetry.addData("Velocity", velocity);
            telemetry.addData("Error", targetVelocity - velocity);
            telemetry.addData("At target", launcher.isAtTargetVelocity());
            telemetry.addData("Time in phase (ms)", (int) timer.milliseconds());
            telemetry.update();
        }
    }
}