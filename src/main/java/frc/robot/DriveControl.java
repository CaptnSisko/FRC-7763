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
    private double ofs;  // offset for processInput
    private double dzn;  // dead zone for process input
    private double accCon;  // constant acceleration
    private double accPro;  // proportional acceleration
    private double inc;  // increment for current step
    private double tgt = 0;  // target value
    private double cnt = 0;  // current value

    public DriveControl() {  // default constructor
        pow = 1;
        ofs = 0.2;
        dzn = 0.1;
        accCon = 0.5;  // TODO: pick better defaults
        accPro = 0.5;
    }

    /** 
     *constructor that sets drive parameters
     */
    public DriveControl(double power, double offset, double dead, double constant, double proportional) {
        pow = power;
        ofs = offset;
        dzn = dead;
        accCon = Math.abs(constant);
        accPro = Math.abs(proportional);
    }
    
    /**
     * functions for setting drive parameters
     */
    public void setPow(double power) {
        pow = power;
    }

    public void setOffset(double offset) {
        ofs = offset;
    }

    public void setAcceleration(double constant, double proportional) {
        accCon = Math.abs(constant);
        accPro = Math.abs(proportional);
    }

    public double getSpeed() {
        return cnt;
    }

    public double getTarget() {
        return tgt;
    }

    /**
     * runs a "step" of the acceleration
     * acc is proportion of remaining distance to target that a step will cover
     */
    private double update() {
        accCon = Telemetry.getAccConstant();
        accPro = Telemetry.getAccProportion();
        pow = Telemetry.getPower();
        ofs = Telemetry.getOffset();
        inc = accCon + Math.abs(cnt - tgt) * accPro;
        if (Math.abs(cnt - tgt) < accCon) {
            cnt = tgt;
        } else {
            cnt = tgt > cnt ? cnt + inc : cnt - inc;
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
        //System.out.println(pow);
        return set(processInput(input));
    }

    /**
     * Returns a processed value from -1 to 1 based on the input
     */
    private double processInput(double val) {
        //double outVal = Math.pow(val, pow) * cft;  // raises to power, multiplies by coefficient
        if (Math.abs(val) < dzn) return 0;
        double outVal = Math.pow(Math.abs(val), pow) * (1 - ofs) + ofs;
        outVal = Math.copySign(outVal, val);  // sets the sign of the output value to be the same as that of the input
        outVal = Math.min(1, Math.max(-1, outVal));  // bounds checking
        System.out.println(outVal);
        return outVal;
    }
}
