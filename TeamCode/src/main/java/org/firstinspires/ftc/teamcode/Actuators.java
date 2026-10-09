package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Actuators{
    public DcMotor leftBackMotor;
    public DcMotor rightBackMotor;
    //public DcMotor rightFrontMotor;
    //public DcMotor leftFrontMotor;
    public DcMotor launchMotor;
    public DcMotor intakeMotor;
    public Servo rightIntakeServo;
    public Servo leftIntakeServo;
    public Servo launchServo;
    public void init(HardwareMap hwMap){
        // motor hardware here
        leftBackMotor = hwMap.get(DcMotor.class, "leftBackMotor");
        rightBackMotor = hwMap.get(DcMotor.class, "rightBackMotor");
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
        launchMotor = hwMap.get(DcMotor.class, "launchMotor");
        // motor mode when power is zero
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // change motor directions
        leftBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        launchMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        // servo hardware here
        leftIntakeServo = hwMap.get(Servo.class, "intakeServoLeft");
        rightIntakeServo = hwMap.get(Servo.class, "intakeServoRight");
        launchServo = hwMap.get(Servo.class, "launchServo");
    }
    public void motorLeft(double power){
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setPower(power);
    }
    public void motorRight(double power){
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setPower(power);
    }
    public void setLaunchMotorOn(double power){
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setPower(power);
    }
    public void setLaunchMotorOff(){
        launchMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launchMotor.setPower(0);
    }
    public void IntakeMotor(double power){
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setPower(power);
    }

    public void IntakeServosOn(){
        leftIntakeServo.setPosition(1); // you need to adjust this
        rightIntakeServo.setPosition(1); // you need to adjust this
    }

    public void IntakeServosOff(){
        leftIntakeServo.setPosition(0); // you need to adjust this
        rightIntakeServo.setPosition(0); // you need to adjust this
    }
    public void launchServoOn(){
        launchServo.setPosition(0); // you need to adjust this
    }
    public void launchServoOff(){
        launchServo.setPosition(-0.5); // you need to adjust this
    }

    public void goForward(double rotation, double power){
        //this is a function to make your robot go forward
        //use actuator.goForward(1, 0.3); in your auto code
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setTargetPosition((int) (-537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (-537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        rightBackMotor.setPower(power);
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy()){
        }
    }
    public void goBackward(double rotation, double power) {
        //this is a function to make your robot go backward
        //use actuator.goBackward(1, 0.3); in your auto code
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setTargetPosition((int) (537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        rightBackMotor.setPower(power);
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy()){
        }
    }
    public void turnLeft(double rotation, double power) {
        //this is a function to make your robot turn left
        //use actuator.turnLeft(1, 0.3); in your auto code
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setTargetPosition((int) (537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (-537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        rightBackMotor.setPower(power);
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy()){
        }
    }
    public void turnRight(double rotation, double power) {
        //this is a function to make your robot turn right
        //use actuator.turnRight(1, 0.3); in your auto code
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setTargetPosition((int) (-537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        rightBackMotor.setPower(power);
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy()){
        }
    }
}
