package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/** Sensor access is kept separate from mechanisms that move the robot. */
public class Sensors {
    private IMU imu;

    public void init(HardwareMap hwMap) {
        // The name "imu" must match the configured hub IMU.

        // Describe the hub mounting: logo up, USB ports right. Keep the team's orientation.
        imu = hwMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot RevOrientation =
                new RevHubOrientationOnRobot(RevHubOrientationOnRobot.
                        LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT);
            imu.initialize(new IMU.Parameters(RevOrientation));
    }

    // Preserve the existing API: angleUnit is accepted, but the result is always degrees.
    // Yaw measures rotation about the robot's vertical axis.
    public double getHeading(AngleUnit angleUnit) {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES); // if using the imu
    }

}
