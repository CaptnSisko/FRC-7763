/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.lang.Math;

/**
 * Used for control of drive motors. Designed to be insantiated, with an instance controlling 1 motor
 */
public class DriveControl {

    private double pow;  // exponent for processInput
    private double cft;  // coefficient for processInput
    private double acc;  // acceleration
    private double tgt = 0;  // target value
    private double cnt = 0;  // current value

    DriveControl() {  // default constructor
        pow = 1;
        cft = 1;
        acc = 1;
    }

    /** 
     *constructor that sets drive parameters
     */
    DriveControl(double power, double coefficient, double acceleration) {
        pow = power;
        cft = coefficient;
        acc = acceleration;
    }
    
    /**
     * functions for setting drive parameters
     */
    public void setPow(double power) {
        pow = power;
    }

    public void setCoefficient(double coefficient) {
        cft = coefficient;
    }

    public void setAcceleration(double acceleration) {
        acc = acceleration;
    }

    /**
     * runs a "step" of the acceleration
     */
    private double update() {
        if (Math.abs(cnt - tgt) < acc) {
            cnt = tgt;
        } else {
            cnt = tgt > cnt ? cnt + acc : cnt - acc;
        }
        return cnt;
    }

    /**
     * sets the targe value and updates
     */
    public double set(double target) {
        tgt = target;
        return update();
    }

    /**
     * same as set(), but runs input through processInput()
     * this function should be used when driving
     */
    public double drive(double input) {
        return set(processInput(input));
    }

    /**
     * Returns a processed value from -1 to 1 based on the input
     */
    private double processInput(double val) {
        double outVal = Math.pow(val, pow) * cft;  // raises to power, multiplies by coefficient
        outVal = Math.copySign(outVal, val);  // sets the sign of the output value to be the same as that of the input
        outVal = Math.min(1, Math.max(-1, outVal));  // bounds checking
        return outVal;
    }
}
