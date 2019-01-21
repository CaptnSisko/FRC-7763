/**
 * this class is to be used for communicating with the frive station via shuffleboard
 */

package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.*;


 public final class Telemetry  {

    private static ShuffleboardTab DriveControl = Shuffleboard.getTab("DriveControl");
    
    public static void init() {
        DriveControl.add("test", 17);
        DriveControl.add("left", 0);
    }

    public static void update() {
        DriveControl.add("left", RobotMap.leftController.getSpeed());
    }

 }
