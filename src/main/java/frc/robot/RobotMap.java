/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

/**
 * Class to contain all robot parts. Increases organization and reduces occurances of magic numbers.
 */
public final class RobotMap {
    private static Joystick joystick = new Joystick(0);

    private static WPI_TalonSRX motorFL = new WPI_TalonSRX(1);
    private static WPI_TalonSRX motorRL = new WPI_TalonSRX(2);
    private static WPI_TalonSRX motorFR = new WPI_TalonSRX(3);
    private static WPI_TalonSRX motorRR = new WPI_TalonSRX(4);

    private static SpeedControllerGroup leftDrive = new SpeedControllerGroup(motorFL, motorRL);
    private static SpeedControllerGroup rightDrive = new SpeedControllerGroup(motorFR, motorRR);

    private static DifferentialDrive diffDrive = new DifferentialDrive(leftDrive, rightDrive);

    public static DriveControl leftController = new DriveControl(2, 1, 0.05);
    public static DriveControl rightController = new DriveControl(2, 1, 0.05);

    public static void init() {
        motorFL.configFactoryDefault();
        motorRL.configFactoryDefault();
        motorFR.configFactoryDefault();
        motorRR.configFactoryDefault();

        motorFL.setInverted(true);
        motorRL.setInverted(true);

        diffDrive.setRightSideInverted(false); // true by default
    }

    public static Joystick getController() {
        return joystick;
    }

    public static DifferentialDrive getDrive() {
        return diffDrive;
    }
}
