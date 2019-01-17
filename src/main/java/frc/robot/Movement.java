/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.lang.Math;

/**
 * Handles calculations for driving the robot
 */
public class Movement {
    public static void drive() {
        double leftSpeed = processInput(RobotMap.getController().getRawAxis(1), 2, 1);
        double rightSpeed = processInput(RobotMap.getController().getRawAxis(5), 2, 1);
        RobotMap.getDrive().tankDrive(leftSpeed, rightSpeed);
    }

    /**
     * Returns a processed value from -1 to 1 based on the input
     */
    private static double processInput(double val, double pow, double coefficient) {
        if (val < 0) coefficient *= -1;
        val = Math.pow(val, pow) * coefficient;
        if (Math.abs(val) < 1) return val;
        return val > 0 ? 1 : -1;
      }
}
