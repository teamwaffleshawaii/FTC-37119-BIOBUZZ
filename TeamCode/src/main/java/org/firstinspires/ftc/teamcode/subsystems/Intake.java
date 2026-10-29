package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Intake groups the actions for one part of the robot.
 * The OpMode decides WHEN to act; this class describes HOW the hardware acts.
 * Actuators supplies the already-configured devices, so construction sends no commands.
 */
public class Intake {
    private final DcMotor intakeMotor;
    private final Servo leftIntakeServo;
    private final Servo rightIntakeServo;

    /** Store references to the same devices used by Actuators; do not remap hardware. */
    public Intake(DcMotor intakeMotor, Servo leftIntakeServo, Servo rightIntakeServo) {
        this.intakeMotor = intakeMotor;
        this.leftIntakeServo = leftIntakeServo;
        this.rightIntakeServo = rightIntakeServo;
    }

    /** Intake: power +1, left servo 1, right servo 0. */
    public void intakeIn(){
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setPower(1);
        leftIntakeServo.setPosition(1); // Servo positions use the SDK range 0 to 1.
        rightIntakeServo.setPosition(0);
    }

    /** Reverse intake: power -1, left servo 0, right servo 1. */
    public void intakeOut(){
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setPower(-1);
        leftIntakeServo.setPosition(0); // Servo positions use the SDK range 0 to 1.
        rightIntakeServo.setPosition(1);
    }

    /** Stop the motor and command both servos to the existing 0.5 position. */
    public void intakeOff(){
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setPower(0);
        leftIntakeServo.setPosition(0.5);
        rightIntakeServo.setPosition(0.5);
    }
}
