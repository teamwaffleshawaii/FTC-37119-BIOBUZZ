package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Actuators{
    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public Servo clawServo;

    public void init(HardwareMap hwMap){
        // motor hardware here
        leftMotor = hwMap.get(DcMotor.class, "leftMotor");
        rightMotor = hwMap.get(DcMotor.class, "rightMotor");
        // motor mode when power is zero
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // change motor directions
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // servo hardware here
        clawServo = hwMap.get(Servo.class, "clawServo");
    }
    public void motorLeft(double power){
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotor.setPower(power);
    }
    public void motorRight(double power){
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setPower(power);
    }
    public void clawGrabs(){
        clawServo.setPosition(0.4); // you need to adjust this
    }
    public void clawDrops(){
        clawServo.setPosition(0.6); // you need to adjust this
    }
}
