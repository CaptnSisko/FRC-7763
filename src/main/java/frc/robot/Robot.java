/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * @author
 * FRC Team 7763 Carrborobotics
 */

package frc.robot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
  @Override
  public void robotInit() {
    RobotMap.init();
    Telemetry.init();
  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    Telemetry.update();
    if (!RobotMap.arcade) {
      RobotMap.diffDrive.tankDrive(
        RobotMap.tank_leftController.drive(RobotMap.joystick.getRawAxis(1)), 
        RobotMap.tank_rightController.drive(RobotMap.joystick.getRawAxis(5))
      );
    } else {
      RobotMap.diffDrive.arcadeDrive(
        RobotMap.arcade_forwardController.drive(RobotMap.joystick.getRawAxis(1) * -1),
        RobotMap.arcade_turnController.drive(RobotMap.joystick.getRawAxis(0)));
    }
  }

  @Override
  public void disabledInit() {
    //RobotMap.getCamera().close();
  }
}
