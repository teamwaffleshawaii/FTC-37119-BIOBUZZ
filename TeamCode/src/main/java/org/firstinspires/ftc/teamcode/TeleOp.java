package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;
import org.firstinspires.ftc.teamcode.subsystems.Windmill;
import org.firstinspires.ftc.teamcode.subsystems.Sensors;

/**
 * Field-centric TeleOp: IMU heading adjusts the joystick-to-wheel calculations.
 * Compare drive() with robot-centric drive to learn how heading changes the math.
 * Controls and action order remain those of FTC 37119.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends LinearOpMode {

    double reset_angle = 0;
    // Each subsystem owns one mechanism; this OpMode decides when it acts.
    private final Drivetrain drivetrain = new Drivetrain();
    private final Intake intake = new Intake();
    private final Launcher launcher = new Launcher();
    private final Windmill windmill = new Windmill();
    private final Sensors sensors = new Sensors();

    @Override
    public void runOpMode() {
        // Initialize each mechanism before START, using FTC 37119's own settings.
        drivetrain.init(hardwareMap);
        intake.init(hardwareMap);
        launcher.init(hardwareMap);
        windmill.init(hardwareMap);
        sensors.init(hardwareMap);

        // Initialization finishes before the Driver Station START button is pressed.
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                // Order matters: heading reset affects the next drive iteration.
                drive();
                resetAngle();
                intake();
                launch();
                telemetry.update();
            }
        }
    }

    /** Read driver input and apply the team's unchanged wheel-power calculations. */
    public void drive() {
        // Reserve some translation magnitude for turning, exactly as before.
        double Protate = - gamepad1.right_stick_x;
        double stick_x = - gamepad1.left_stick_x * Math.sqrt(Math.pow(1-Math.abs(Protate), 2) );
        //Accounts for Protate when limiting magnitude to be less than 1
        double stick_y = - gamepad1.left_stick_y * Math.sqrt(Math.pow(1-Math.abs(Protate), 2) );
        double theta = 0;
        double Px = 0;
        double Py = 0;

        double gyroAngle = getHeading() * Math.PI / 180; //Converts gyroAngle into radians
        if (gyroAngle <= 0) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (0 < gyroAngle && gyroAngle < Math.PI / 2) {
            gyroAngle = gyroAngle + (Math.PI / 2);
        } else if (Math.PI / 2 <= gyroAngle) {
            gyroAngle = gyroAngle - (3 * Math.PI / 2);
        }
        gyroAngle = -1 * gyroAngle;

        if(gamepad1.right_bumper){ //Disables gyro, sets to -Math.PI/2 so front is defined correctly.
            gyroAngle = -Math.PI/2;
        }

        //Linear directions in case you want to do straight lines.
        if(gamepad1.dpad_right){
            stick_x = 0.5;
        }
        else if(gamepad1.dpad_left){
            stick_x = -0.5;
        }
        if(gamepad1.dpad_up){
            stick_y = -0.5;
        }
        else if(gamepad1.dpad_down){
            stick_y = 0.5;
        }

        //MOVEMENT
        theta = Math.atan2(stick_y, stick_x) - gyroAngle - (Math.PI / 2);
        Px = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta + Math.PI / 4));
        Py = Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)) * (Math.sin(theta - Math.PI / 4));

        telemetry.addData("Stick_X", stick_x);
        telemetry.addData("Stick_Y", stick_y);
        telemetry.addData("Magnitude",  Math.sqrt(Math.pow(stick_x, 2) + Math.pow(stick_y, 2)));
        telemetry.addData("Motor Front Left", Py - Protate);
        telemetry.addData("Motor Front Right", Px + Protate);
        telemetry.addData("Motor Back Left", Px - Protate);
        telemetry.addData("Motor Back Right", Py + Protate);

        drivetrain.leftFrontMotor((Py - Protate));
        drivetrain.leftBackMotor((Px - Protate));
        drivetrain.rightBackMotor((Py + Protate));
        drivetrain.rightFrontMotor((Px + Protate));
    }

    public void resetAngle(){
        //Press "Start button" to reset the heading
        if(gamepad1.start){
            reset_angle = getHeading() + reset_angle;
        }
    }

    public double getHeading(){

        double heading;
        //Get heading from the IMU
        heading = sensors.getHeading(AngleUnit.DEGREES); //Detecting yaw value from imu

        if(heading < -180) {
            heading = heading + 360;
        }

        else if(heading > 180) {
            heading = heading - 360;
        }

        // Subtract the stored reference so the driver can redefine the forward heading.
        heading = heading - reset_angle;
        telemetry.addData("Heading (Degrees)", heading);
        // Preserve the original display formula (it uses /90, not the usual /180).
        telemetry.addData("Heading (Radians)", heading * 3.1415926535 / 90);
        return heading;
    }

    public void intake() {
        // Either gamepad can control intake. A wins if A and B are held together.
        if (gamepad1.a || gamepad2.a) {
            intake.on();

        }

        else if (gamepad1.b || gamepad2.b){
            intake.reverse();
        }

        else {
            intake.off();
        }
    }

    /** Launcher is latched: releasing X does not turn it off; Y does. */
    public void launch() {
        if (gamepad2.x) {
            launcher.setMotorPower(0.5); //Adjust the power as needed
        }
        else if (gamepad2.y){
            launcher.setMotorPower(0);
        }

        // Windmill runs only while gamepad 2 right bumper is held.
        if (gamepad2.right_bumper) {
            windmill.setMotorPower(.50);
        }
        else {
            windmill.setMotorPower(0);
        }
    }
}
