package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class Launcher {

    private DcMotorEx launchMotor;

    // PIDF gains (direct-drive 6000 RPM motor, 28 ticks/rev, max ~2800 ticks/sec)
    //MAXIMUM F value is from 32767/2800(max ticks/sec)
    //MAXMIMUM F VALUE IS 11.7
    private static final double P = 35; //change this
    private static final double I = 0.0;//Don't need to change since its smoothing error
    private static final double D = 0.0;//dampens probably doesn't have to change
    private static final double F = 10; //supplies motor with power at all times dangerous do not increase too high

    // Ticks per second
    private static final double VELOCITY_TOLERANCE = 25.0;

    private double targetVelocity = 0.0;

    public void init(HardwareMap hardwareMap) {
        launchMotor = hardwareMap.get(DcMotorEx.class, "launchMotor");

        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        launchMotor.setVelocityPIDFCoefficients(P, I, D, F);
    }

    public void setMotorVelocity(double velocity) {
        targetVelocity = velocity;
        launchMotor.setVelocity(velocity);
    }

    public double getMotorVelocity() {
        return launchMotor.getVelocity();
    }

    public double getCurrentAmps() {
        return launchMotor.getCurrent(CurrentUnit.AMPS);
    }

    public boolean isAtTargetVelocity() {
        return targetVelocity > 0
                && Math.abs(getMotorVelocity() - targetVelocity) <= VELOCITY_TOLERANCE;
    }

    public void stop() {
        setMotorVelocity(0.0);
    }
}