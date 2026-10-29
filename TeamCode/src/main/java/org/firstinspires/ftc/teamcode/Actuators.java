package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.teamcode.subsystems.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * The robot's hardware setup and subsystem container.
 * Hardware names must match the Driver Station configuration exactly.
 * Public device fields and old action methods remain available for existing code.
 * Call init once before using the subsystems: their constructors only store devices.
 */
public class Actuators{
    public Drivetrain drivetrain;
    public Intake intake;
    public Launcher launcher;
    public Windmill windmill;

    public DcMotor leftBackMotor;
    public DcMotor leftFrontMotor;
    public DcMotor rightBackMotor;
    public DcMotor rightFrontMotor;
    public DcMotor launchMotor;
    public DcMotor windmillMotor;
    public DcMotor intakeMotor;
    public Servo rightIntakeServo;
    public Servo leftIntakeServo;
    //public Servo launchServo;
    public void init(HardwareMap hwMap){
        // motor hardware here
        leftBackMotor = hwMap.get(DcMotor.class, "leftBackMotor");
        leftFrontMotor = hwMap.get(DcMotor.class, "leftFrontMotor");
        rightBackMotor = hwMap.get(DcMotor.class, "rightBackMotor");
        rightFrontMotor = hwMap.get(DcMotor.class, "rightFrontMotor");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        launchMotor = hwMap.get(DcMotor.class, "launchMotor");
        windmillMotor = hwMap.get(DcMotor.class, "windmillMotor");

        // BRAKE resists coasting at zero power; FLOAT lets the launcher coast.
        // Keep the original device setup sequence.
        // motor mode when power is zero
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launchMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        windmillMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Direction changes the sign used by the SDK to match the motor mounting.
        // change motor directions
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        windmillMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        // servo hardware here
        leftIntakeServo = hwMap.get(Servo.class, "intakeServoLeft");
        rightIntakeServo = hwMap.get(Servo.class, "intakeServoRight");
//        launchServo = hwMap.get(Servo.class, "launchServo");

        // Share these exact hardware objects; constructors do not move or reconfigure them.
        drivetrain = new Drivetrain(leftBackMotor, leftFrontMotor, rightBackMotor, rightFrontMotor);
        intake = new Intake(intakeMotor, leftIntakeServo, rightIntakeServo);
        launcher = new Launcher(launchMotor);
        windmill = new Windmill(windmillMotor);

    }
    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void leftBackMotor(double power){
        drivetrain.leftBackMotor(power);
    }
    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void leftFrontMotor(double power){
        drivetrain.leftFrontMotor(power);
    }
    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void rightBackMotor(double power){
        drivetrain.rightBackMotor(power);
    }
    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void rightFrontMotor(double power){
        drivetrain.rightFrontMotor(power);
    }
    /** Compatibility entry point; the Launcher subsystem performs this action. */
    public void launchMotor(double power){
        launcher.launchMotor(power);
    }

    /** Compatibility entry point; the Windmill subsystem performs this action. */
    public void windmillMotor(double power){
        windmill.windmillMotor(power);
    }

    /** Compatibility entry point; the Intake subsystem performs this action. */
    public void intakeIn(){
        intake.intakeIn();
    }
    /** Compatibility entry point; the Intake subsystem performs this action. */
    public void intakeOut(){
        intake.intakeOut();
    }

    /** Compatibility entry point; the Intake subsystem performs this action. */
    public void intakeOff(){
        intake.intakeOff();
    }
//    public void launchServoOn(){
//        launchServo.setPosition(1); // you need to adjust this
//    }
//    public void launchServoOff(){
//        launchServo.setPosition(0.5); // you need to adjust this
//    }

    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void goBackward(double rotation, double power){
        drivetrain.goBackward(rotation, power);
    }
    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void goForward(double rotation, double power) {
        drivetrain.goForward(rotation, power);
    }
    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void turnRight(double rotation, double power) {
        drivetrain.turnRight(rotation, power);
    }
    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void turnLeft(double rotation, double power) {
        drivetrain.turnLeft(rotation, power);
    }
    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void strafeRight(double rotation, double power) {
        drivetrain.strafeRight(rotation, power);
    }
    /** Compatibility entry point; the Drivetrain subsystem performs this action. */
    public void strafeLeft(double rotation, double power) {
        drivetrain.strafeLeft(rotation, power);
    }


}
