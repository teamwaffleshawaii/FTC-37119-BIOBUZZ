package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Intake groups the actions for one part of the robot.
 * The OpMode decides WHEN to act; this class describes HOW the hardware acts.
 * This subsystem owns its hardware: init looks up and configures its devices.
 * Hardware names must match this team's Driver Station configuration exactly.
 */
public class Intake {
    private DcMotor intakeMotor;
    private Servo leftIntakeServo;
    private Servo rightIntakeServo;

    /**
     * Connect and configure this mechanism before waitForStart().
     * BRAKE resists coasting at zero power; FLOAT allows the launcher to coast.
     * No power, servo-position, or run-mode commands are added during initialization.
     */
    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftIntakeServo = hardwareMap.get(Servo.class, "intakeServoLeft");
        rightIntakeServo = hardwareMap.get(Servo.class, "intakeServoRight");
    }

    /** Intake: power +1, left servo 1, right servo 0. */
    public void on(){
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setPower(1);
        leftIntakeServo.setPosition(1); // Servo positions use the SDK range 0 to 1.
        rightIntakeServo.setPosition(0);
    }

    /** Reverse intake: power -1, left servo 0, right servo 1. */
    public void reverse(){
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setPower(-1);
        leftIntakeServo.setPosition(0); // Servo positions use the SDK range 0 to 1.
        rightIntakeServo.setPosition(1);
    }

    /** Stop the motor and command both servos to the existing 0.5 position. */
    public void off(){
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setPower(0);
        leftIntakeServo.setPosition(0.5);
        rightIntakeServo.setPosition(0.5);
    }
}
