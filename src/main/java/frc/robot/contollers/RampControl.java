/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.contollers;

import edu.wpi.first.wpilibj.Timer;

/**
 * Class for controlling ramp deployment
 */
public class RampControl {

    private Timer safetyTime = new Timer();
    private Timer deployTime = new Timer();
    private boolean deployed = false;
    private boolean lastState = false;
    private boolean reversed;
    private double safetyDuration, deployDuration;

    public RampControl(boolean reversed, double safetyDuration, double deployDuration) {
        this.reversed = reversed;
        this.safetyDuration = safetyDuration;
        this.deployDuration = deployDuration;
    }

    public double update(boolean btn) {
        if (btn && !lastState) {
            safetyTime.reset();
            safetyTime.start();
        }
        if(!btn) {
            safetyTime.stop();
        }
        if (btn && (safetyTime.get() > safetyDuration)) {
            deployed = true;
            deployTime.start();
        }
        lastState = btn;
        if (deployed && (deployTime.get() < deployDuration)) {
            return reversed ? -1 : 1;
        }
        return 0;
    }

}
