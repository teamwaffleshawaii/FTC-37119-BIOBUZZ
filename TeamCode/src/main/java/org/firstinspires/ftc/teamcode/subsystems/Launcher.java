package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Launcher groups the actions for one part of the robot.
 * The OpMode decides WHEN to act; this class describes HOW the hardware acts.
 * This subsystem owns its hardware: init looks up and configures its devices.
 * Hardware names must match this team's Driver Station configuration exactly.
 */
public class Launcher {
    private DcMotor launchMotor;

    /**
     * Connect and configure this mechanism before waitForStart().
     * BRAKE resists coasting at zero power; FLOAT allows the launcher to coast.
     * No power, servo-position, or run-mode commands are added during initialization.
     */
    public void init(HardwareMap hardwareMap) {
        launchMotor = hardwareMap.get(DcMotor.class, "launchMotor");
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /** Apply the requested power, selecting RUN_WITHOUT_ENCODER on every call. */
    public void setMotorPower(double power){
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setPower(power);
    }
}
