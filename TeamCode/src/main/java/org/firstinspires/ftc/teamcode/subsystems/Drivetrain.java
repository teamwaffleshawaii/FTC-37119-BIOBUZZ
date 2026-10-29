package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Drivetrain groups the actions for one part of the robot.
 * The OpMode decides WHEN to act; this class describes HOW the hardware acts.
 * Robot supplies the already-configured devices, so construction sends no commands.
 */
public class Drivetrain {
    private final DcMotor leftBackMotor;
    private final DcMotor leftFrontMotor;
    private final DcMotor rightBackMotor;
    private final DcMotor rightFrontMotor;

    /** Store references to the same devices used by Robot; do not remap hardware. */
    public Drivetrain(DcMotor leftBackMotor, DcMotor leftFrontMotor, DcMotor rightBackMotor, DcMotor rightFrontMotor) {
        this.leftBackMotor = leftBackMotor;
        this.leftFrontMotor = leftFrontMotor;
        this.rightBackMotor = rightBackMotor;
        this.rightFrontMotor = rightFrontMotor;
    }

    /** Apply the requested power, selecting RUN_WITHOUT_ENCODER on every call. */
    public void leftBackMotor(double power){
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setPower(power);
    }

    /** Apply the requested power, selecting RUN_WITHOUT_ENCODER on every call. */
    public void leftFrontMotor(double power){
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontMotor.setPower(power);
    }

    /** Apply the requested power, selecting RUN_WITHOUT_ENCODER on every call. */
    public void rightBackMotor(double power){
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setPower(power);
    }

    /** Apply the requested power, selecting RUN_WITHOUT_ENCODER on every call. */
    public void rightFrontMotor(double power){
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setPower(power);
    }

    /**
     * Run the team's original encoder movement. Rotation is motor rotations, not degrees.
     * The signed targets below select each wheel's direction; 537.7 is the existing
     * ticks-per-rotation conversion. The int cast discards the fractional tick.
     * Power and every command remain in their original order.
     */
    public void goBackward(double rotation, double power){
        // Keep the original mode setup, then make the current position encoder zero.
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Set all four signed destinations before selecting RUN_TO_POSITION.
        leftBackMotor.setTargetPosition((int) (-537.7 * rotation));
        leftFrontMotor.setTargetPosition((int) (-537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (-537.7 * rotation));
        rightFrontMotor.setTargetPosition((int) (-537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        leftFrontMotor.setPower(power);
        rightBackMotor.setPower(power);
        rightFrontMotor.setPower(power);
        // This blocks the caller. && means it exits when ANY motor is no longer busy.
        // There is no stop-request check, timeout, or final zero-power command here.
        // Those are existing behaviors, preserved for this organization-only change.
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy() && leftFrontMotor.isBusy() && rightFrontMotor.isBusy()){
        }
    }

    /**
     * Run the team's original encoder movement. Rotation is motor rotations, not degrees.
     * The signed targets below select each wheel's direction; 537.7 is the existing
     * ticks-per-rotation conversion. The int cast discards the fractional tick.
     * Power and every command remain in their original order.
     */
    public void goForward(double rotation, double power) {
        // Keep the original mode setup, then make the current position encoder zero.
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Set all four signed destinations before selecting RUN_TO_POSITION.
        leftBackMotor.setTargetPosition((int) (537.7 * rotation));
        leftFrontMotor.setTargetPosition((int) (537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (537.7 * rotation));
        rightFrontMotor.setTargetPosition((int) (537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        leftFrontMotor.setPower(power);
        rightBackMotor.setPower(power);
        rightFrontMotor.setPower(power);
        // This blocks the caller. && means it exits when ANY motor is no longer busy.
        // There is no stop-request check, timeout, or final zero-power command here.
        // Those are existing behaviors, preserved for this organization-only change.
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy() && leftFrontMotor.isBusy() && rightFrontMotor.isBusy()){
        }
    }

    /**
     * Run the team's original encoder movement. Rotation is motor rotations, not degrees.
     * The signed targets below select each wheel's direction; 537.7 is the existing
     * ticks-per-rotation conversion. The int cast discards the fractional tick.
     * Power and every command remain in their original order.
     */
    public void turnRight(double rotation, double power) {
        // Keep the original mode setup, then make the current position encoder zero.
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Set all four signed destinations before selecting RUN_TO_POSITION.
        leftBackMotor.setTargetPosition((int) (537.7 * rotation));
        leftFrontMotor.setTargetPosition((int) (537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (-537.7 * rotation));
        rightFrontMotor.setTargetPosition((int) (-537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        leftFrontMotor.setPower(power);
        rightBackMotor.setPower(power);
        rightFrontMotor.setPower(power);

        // This blocks the caller. && means it exits when ANY motor is no longer busy.
        // There is no stop-request check, timeout, or final zero-power command here.
        // Those are existing behaviors, preserved for this organization-only change.
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy() && leftFrontMotor.isBusy() && rightFrontMotor.isBusy()){
        }
    }

    /**
     * Run the team's original encoder movement. Rotation is motor rotations, not degrees.
     * The signed targets below select each wheel's direction; 537.7 is the existing
     * ticks-per-rotation conversion. The int cast discards the fractional tick.
     * Power and every command remain in their original order.
     */
    public void turnLeft(double rotation, double power) {
        // Keep the original mode setup, then make the current position encoder zero.
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Set all four signed destinations before selecting RUN_TO_POSITION.
        leftBackMotor.setTargetPosition((int) (-537.7 * rotation));
        leftFrontMotor.setTargetPosition((int) (-537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (537.7 * rotation));
        rightFrontMotor.setTargetPosition((int) (537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        leftFrontMotor.setPower(power);
        rightBackMotor.setPower(power);
        rightFrontMotor.setPower(power);

        // This blocks the caller. && means it exits when ANY motor is no longer busy.
        // There is no stop-request check, timeout, or final zero-power command here.
        // Those are existing behaviors, preserved for this organization-only change.
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy() && leftFrontMotor.isBusy() && rightFrontMotor.isBusy()){
        }
    }

    /**
     * Run the team's original encoder movement. Rotation is motor rotations, not degrees.
     * The signed targets below select each wheel's direction; 537.7 is the existing
     * ticks-per-rotation conversion. The int cast discards the fractional tick.
     * Power and every command remain in their original order.
     */
    public void strafeRight(double rotation, double power) {
        // Keep the original mode setup, then make the current position encoder zero.
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Set all four signed destinations before selecting RUN_TO_POSITION.
        leftBackMotor.setTargetPosition((int) (-537.7 * rotation));
        leftFrontMotor.setTargetPosition((int) (537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (537.7 * rotation));
        rightFrontMotor.setTargetPosition((int) (-537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        leftFrontMotor.setPower(power);
        rightBackMotor.setPower(power);
        rightFrontMotor.setPower(power);

        // This blocks the caller. && means it exits when ANY motor is no longer busy.
        // There is no stop-request check, timeout, or final zero-power command here.
        // Those are existing behaviors, preserved for this organization-only change.
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy() && leftFrontMotor.isBusy() && rightFrontMotor.isBusy()){
        }
    }

    /**
     * Run the team's original encoder movement. Rotation is motor rotations, not degrees.
     * The signed targets below select each wheel's direction; 537.7 is the existing
     * ticks-per-rotation conversion. The int cast discards the fractional tick.
     * Power and every command remain in their original order.
     */
    public void strafeLeft(double rotation, double power) {
        // Keep the original mode setup, then make the current position encoder zero.
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Set all four signed destinations before selecting RUN_TO_POSITION.
        leftBackMotor.setTargetPosition((int) (537.7 * rotation));
        leftFrontMotor.setTargetPosition((int) (-537.7 * rotation));
        rightBackMotor.setTargetPosition((int) (-537.7 * rotation));
        rightFrontMotor.setTargetPosition((int) (537.7 * rotation));
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setPower(power);
        leftFrontMotor.setPower(power);
        rightBackMotor.setPower(power);
        rightFrontMotor.setPower(power);

        // This blocks the caller. && means it exits when ANY motor is no longer busy.
        // There is no stop-request check, timeout, or final zero-power command here.
        // Those are existing behaviors, preserved for this organization-only change.
        while (leftBackMotor.isBusy() && rightBackMotor.isBusy() && leftFrontMotor.isBusy() && rightFrontMotor.isBusy()){
        }
    }
}
