package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Windmill groups the actions for one part of the robot.
 * The OpMode decides WHEN to act; this class describes HOW the hardware acts.
 * Actuators supplies the already-configured devices, so construction sends no commands.
 */
public class Windmill {
    private final DcMotor windmillMotor;

    /** Store references to the same devices used by Actuators; do not remap hardware. */
    public Windmill(DcMotor windmillMotor) {
        this.windmillMotor = windmillMotor;
    }

    /** Apply the requested power, selecting RUN_WITHOUT_ENCODER on every call. */
    public void windmillMotor(double power){
        windmillMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        windmillMotor.setPower(power);
    }
}
