package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Launcher groups the actions for one part of the robot.
 * The OpMode decides WHEN to act; this class describes HOW the hardware acts.
 * Actuators supplies the already-configured devices, so construction sends no commands.
 */
public class Launcher {
    private final DcMotor launchMotor;

    /** Store references to the same devices used by Actuators; do not remap hardware. */
    public Launcher(DcMotor launchMotor) {
        this.launchMotor = launchMotor;
    }

    /** Apply the requested power, selecting RUN_WITHOUT_ENCODER on every call. */
    public void launchMotor(double power){
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setPower(power);
    }
}
