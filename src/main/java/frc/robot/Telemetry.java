/**
 * this class is to be used for communicating with the frive station via shuffleboard
 */

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;


import java.util.Map;

 public final class Telemetry {
    //private static ShuffleboardTab DriveControl = Shuffleboard.getTab("Drive Control");
    private static NetworkTableEntry offset, pow, dzn, accCon, accPro;

    public static void init() {
        offset = Shuffleboard.getTab("Drive Control")
        .add("Offset", RobotMap.OFFSET)
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();
        pow = Shuffleboard.getTab("Drive Control")
        .add("Power", RobotMap.POWER)
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();
        dzn = Shuffleboard.getTab("Drive Control")
        .add("Dead Zone", RobotMap.DEADZONE)
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();
        accCon = Shuffleboard.getTab("Drive Control")
        .add("Constant Accelecation", RobotMap.CONST_ACCEL)
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();
        accPro = Shuffleboard.getTab("Drive Control")
        .add("Proportional Accelecation", RobotMap.PROP_ACCEL)
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();

        Shuffleboard.getTab("Drive Control")
        .add("Drive Base", RobotMap.diffDrive)
        .withWidget(BuiltInWidgets.kDifferentialDrive);
    }

    public static void update() {
        RobotMap.leftController.setOffset(getOffset());
        RobotMap.leftController.setPow(getPower());
        RobotMap.leftController.setAcceleration(getAccConstant(), getAccProportion());
        RobotMap.leftController.setDeadzone(getDeadZone());

        RobotMap.rightController.setOffset(getOffset());
        RobotMap.rightController.setPow(getPower());
        RobotMap.rightController.setAcceleration(getAccConstant(), getAccProportion());
        RobotMap.rightController.setDeadzone(getDeadZone());
    }

    public static double getOffset() {
        return offset.getValue().getDouble();
    }

    public static double getPower() {
        return pow.getValue().getDouble();
    }

    public static double getAccConstant() {
        return accCon.getValue().getDouble();
    }

    public static double getAccProportion() {
        return accPro.getValue().getDouble();
    }

    public static double getDeadZone() {
        return dzn.getValue().getDouble();
    }
 }
