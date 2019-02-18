/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * @author
 * FRC Team 7763 Carrborobotics
 */

package frc.robot.util;

import frc.robot.contollers.DriveControl;
import frc.robot.contollers.LiftControl;
import frc.robot.contollers.RampControl;
import frc.robot.sensors.UltrasonicSensor;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

/**
 * Class to contain all robot parts. Increases organization and reduces occurances of magic numbers.
 */
public final class RobotMap {
    private static final WPI_VictorSPX motorFL = new WPI_VictorSPX(0);
    private static final WPI_VictorSPX motorRL = new WPI_VictorSPX(1);
    private static final WPI_VictorSPX motorFR = new WPI_VictorSPX(2);
    private static final WPI_VictorSPX motorRR = new WPI_VictorSPX(3);

    private static SpeedControllerGroup leftDrive = new SpeedControllerGroup(motorFL, motorRL);
    private static SpeedControllerGroup rightDrive = new SpeedControllerGroup(motorFR, motorRR);

    private static UsbCamera camera;

    public static final Joystick joystick = new Joystick(0);
    public static final DifferentialDrive diffDrive = new DifferentialDrive(leftDrive, rightDrive);

    //ultrasonic sensor
    public static final AnalogInput distInput = new AnalogInput(0);
    public static final int READING_COUNT = 25;
    public static final UltrasonicSensor distSensor = new UltrasonicSensor(distInput, READING_COUNT);
  
    public static final double POWER = 2;
    public static final double OFFSET = 0.28;
    public static final double DEADZONE = 0.05;
    public static final double CONST_ACCEL = 0.1;
    public static final double PROP_ACCEL = 0.2;
    public static final DriveControl tank_leftController = new DriveControl();
    public static final DriveControl tank_rightController = new DriveControl();
    public static final DriveControl arcade_forwardController = new DriveControl();
    public static final DriveControl arcade_turnController = new DriveControl();

    //lift control
    public static final Spark liftMotor = new Spark(0);
    private static final DigitalInput upperSwitch = new DigitalInput(0);
    private static final DigitalInput lowerSwitch = new DigitalInput(1);
    public static final LiftControl liftController = new LiftControl(upperSwitch, lowerSwitch, false);

    public static boolean getUpperSwitch() {
        return upperSwitch.get();
    }
    public static boolean getLowerSwitch() {
        return lowerSwitch.get();
    }
    
    public static boolean arcade = true;

    public static final VictorSP rampMotor = new VictorSP(1);
    public static final RampControl rampController = new RampControl(false, 1, 0.5);

    public static void init() {
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(320, 240);
        camera.setFPS(30);

        motorFL.configFactoryDefault();
        motorRL.configFactoryDefault();
        motorFR.configFactoryDefault();
        motorRR.configFactoryDefault();

        //motorFL.setInverted(true);
        //motorRL.setInverted(true);

        //diffDrive.setRightSideInverted(false); // true by default
    }

    public static UsbCamera getCamera() {
        return camera;
    }
}
