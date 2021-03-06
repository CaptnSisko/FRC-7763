/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.contollers;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.util.RobotMap;

/**
 * Class for controlling lift mechanism
 */
public class LiftControl {

    private static Timer time = new Timer();
    private boolean state = false;  // true == up unless inverted
    private boolean inverted = false;
    private boolean prevButton = false;
    private boolean manual = false;
    private DigitalInput upSwitch, downSwitch;

    public LiftControl(DigitalInput upSwitch, DigitalInput downSwitch) {
        this.upSwitch = upSwitch;
        this.downSwitch = downSwitch;
        time.start();
    }

    public LiftControl(DigitalInput upSwitch, DigitalInput downSwitch, boolean inverted) {
        this.upSwitch = upSwitch;
        this.downSwitch = downSwitch;
        this.inverted = inverted;
        time.start();
    }

    public void toManual() {
        manual = true;
    }

    public double update(boolean btn, boolean upButton, boolean downButton) {  // returns value to be fed to motor controller
        if (manual) {
            return 0.25 * ((upButton ? 1 : 0) - (downButton ? 1 : 0)) * (inverted ? -1 : 1);
        }

        if (btn && !prevButton && time.get() > 0.5) {
            state = !state;
            time.reset();
            time.start();
        }
        prevButton = btn;

        boolean up = inverted ? !state : state;

        //lights (operate only when in auto mode)
        RobotMap.liftStateOut.set(up);

        if (up ? !upSwitch.get() : !downSwitch.get()) {
            return 0.0;
        } else {
            return up ? 1.0 : -1.0;
        }
    }

    public void setState(boolean up) {
        state = up ? !inverted : inverted;
    }

    public boolean getState() {  // returns true if lift is up acccounting for inverted
        return inverted ? !state : state;
    }
}
