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
public final class Movement {
    public static void drive() {
        double leftSpeed = processInput(RobotMap.getController().getRawAxis(1), 2, 1);
        double rightSpeed = processInput(RobotMap.getController().getRawAxis(5), 2, 1);
        RobotMap.getDrive().tankDrive(leftSpeed, rightSpeed);
    }

    /**
     * Returns a processed value from -1 to 1 based on the input
     */
    public static double processInput(double val, double pow, double coefficient) {
        double outVal = Math.pow(val, pow) * coefficient;  // raises to power, multiplies by coefficient
        outVal = Math.copySign(outVal, val);  // sets the sign of the output value to be the same as that of the input
        outVal = Math.max(1, Math.min(-1, outVal));  // bounds checking
        return outVal;
      }
}
