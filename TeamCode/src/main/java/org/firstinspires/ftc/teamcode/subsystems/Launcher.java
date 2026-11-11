
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

/**
 * Launcher groups the actions for one part of the robot.
 * The OpMode decides WHEN to act; this class describes HOW the hardware acts.
 *
 * PIDF implementation adapted from the official FTC SDK example:
 * ConceptChangePIDF
 * Original example credited to Tom (9/26/2017).
 * Updated 9/24/2021 for PIDF.
 *
 * Reference:
 * https://ftc-docs.firstinspires.org/en/latest/programming_resources/
 * shared/pidf_coefficients/pidf-coefficients.html
 *
 * This implementation uses the enhanced DcMotorEx PIDF methods
 * available with REV Robotics Control Hub and Expansion Hub.
 */
public class Launcher {

    private DcMotorEx launchMotor;

    // PIDF coefficients (tune for this specific launcher)
    public static final double NEW_P = 70;
    public static final double NEW_I = 0.0;
    public static final double NEW_D = 0.03;
    public static final double NEW_F = 20;

    // Velocity configuration (ticks per second)
    private static final double VELOCITY_TOLERANCE = 25.0;

    private double targetVelocity = 0.0;

    /**
     * Connect and configure launcher hardware.
     */
    public void init(HardwareMap hardwareMap) {

        launchMotor = hardwareMap.get(DcMotorEx.class, "launchMotor");

        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Read original PIDF coefficients (FTC example approach).
        PIDFCoefficients pidfOrig =
                launchMotor.getPIDFCoefficients(
                        DcMotor.RunMode.RUN_USING_ENCODER
                );

        // Apply new PIDF coefficients.
        PIDFCoefficients pidfNew =
                new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);

        launchMotor.setPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER,
                pidfNew
        );

        // Re-read coefficients to verify the change.
        PIDFCoefficients pidfModified =
                launchMotor.getPIDFCoefficients(
                        DcMotor.RunMode.RUN_USING_ENCODER
                );
    }

    /**
     * Set target launcher velocity in ticks per second.
     */
    public void setMotorVelocity(double velocity) {
        targetVelocity = velocity;
        launchMotor.setVelocity(velocity);
    }

    /**
     * Return current launcher velocity in ticks per second.
     */
    public double getMotorVelocity() {
        return launchMotor.getVelocity();
    }

    /**
     * Check whether launcher is within target velocity tolerance.
     */
    public boolean isAtTargetVelocity() {
        return targetVelocity > 0 &&
                Math.abs(getMotorVelocity() - targetVelocity)
                        <= VELOCITY_TOLERANCE;
    }

    /**
     * Stop launcher.
     */
    public void stop() {
        targetVelocity = 0.0;
        launchMotor.setVelocity(0.0);
    }
}
