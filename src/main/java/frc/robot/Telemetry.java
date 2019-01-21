/**
 * this class is to be used for communicating with the frive station via shuffleboard
 */

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import frc.robot.*;


 public final class Telemetry {

    private static ShuffleboardTab DriveControl = Shuffleboard.getTab("DriveControl");
    private static NetworkTableEntry left;

    public static void init() {
        Shuffleboard
        .getTab("DriveControl")
        .add("Drive Base", RobotMap.diffDrive)
        .withWidget(BuiltInWidgets.kDifferentialDrive);
        DriveControl.add("test", 17);
        left = DriveControl.add("left", 0).getEntry();
    }

    public static void update() {
        left.setDouble(RobotMap.leftController.getSpeed());

    }

 }
