package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Actuators{
//    public DcMotor leftMotor;
//    public DcMotor rightMotor;
    public DcMotor intakeMotor;
    public Servo rightIntakeServo;
    public Servo leftIntakeServo;

    public void init(HardwareMap hwMap){
        // motor hardware here
//        leftMotor = hwMap.get(DcMotor.class, "leftMotor");
//      rightMotor = hwMap.get(DcMotor.class, "rightMotor");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        // motor mode when power is zero
        //leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // change motor directions
//        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
          intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//        // servo hardware here
          leftIntakeServo = hwMap.get(Servo.class, "intakeServoLeft");
          rightIntakeServo = hwMap.get(Servo.class, "intakeServoRight");
    }
//    public void motorLeft(double power){
//        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        leftMotor.setPower(power);
//    }
//    public void motorRight(double power){
//        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightMotor.setPower(power);
//    }

    public void IntakeMotor(double power){
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setPower(power);
    }

    public void IntakeServosOn(){
        leftIntakeServo.setPosition(0); // you need to adjust this
        rightIntakeServo.setPosition(1); // you need to adjust this
    }

    public void IntakeServoOff(){
        leftIntakeServo.setPosition(0.5); // you need to adjust this
        rightIntakeServo.setPosition(0.5); // you need to adjust this
    }



}
