/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

public class Robot extends TimedRobot {
  WPI_TalonSRX _talonFL = new WPI_TalonSRX(1);
  WPI_TalonSRX _talonRL = new WPI_TalonSRX(2);
  SpeedControllerGroup leftDrive = new SpeedControllerGroup(_talonFL, _talonRL);
  WPI_TalonSRX _talonFR = new WPI_TalonSRX(3);
  WPI_TalonSRX _talonRR = new WPI_TalonSRX(4);
  SpeedControllerGroup rightDrive = new SpeedControllerGroup(_talonFR, _talonRR);

  DifferentialDrive _drive = new DifferentialDrive(leftDrive, rightDrive);
  Joystick _joystick = new Joystick(0);

  @Override
  public void teleopInit() {
    /* factory default values */
    _talonFL.configFactoryDefault();
    _talonRL.configFactoryDefault();
    _talonFR.configFactoryDefault();
    _talonRR.configFactoryDefault();

    /* flip values so robot moves forward when stick-forward/LEDs-green */
    _talonFL.setInverted(true); // <<<<<< Adjust this
    _talonRL.setInverted(true); // <<<<<< Adjust this

    //_talonR.setInverted(true); // <<<<<< Adjust this

    /*
     * WPI drivetrain classes defaultly assume left and right are opposite. call
     * this so we can apply + to both sides when moving forward. DO NOT CHANGE
     */
    _drive.setRightSideInverted(false);
  }

  @Override
  public void teleopPeriodic() {
    _drive.tankDrive(_joystick.getRawAxis(1), _joystick.getRawAxis(5), true);

    /* hold down btn1 to print stick values */
    //System.out.println("Hello World");
  }
}