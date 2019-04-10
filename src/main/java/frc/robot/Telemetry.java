/**
 * this class is to be used for communicating with the frive station via shuffleboard
 */

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

 public final class Telemetry {
    //private static ShuffleboardTab DriveControl = Shuffleboard.getTab("Drive Control");
    private static NetworkTableEntry offset, pow, dzn, accCon, accPro, speed, arcade;

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
        speed = Shuffleboard.getTab("Drive Control")
        .add("Proportional Accelecation", RobotMap.SPEED)
        .withWidget(BuiltInWidgets.kTextView)
        .getEntry();
        arcade = Shuffleboard.getTab("Drive Control")
        .add("Tank Drive", RobotMap.arcade)
        .withWidget(BuiltInWidgets.kToggleSwitch)
        .getEntry();

        Shuffleboard.getTab("Information")
        .add("Drive Base", RobotMap.diffDrive)
        .withWidget(BuiltInWidgets.kDifferentialDrive);
        Shuffleboard.getTab("Information")
        .add("Camera", RobotMap.getCamera())
        .withWidget(BuiltInWidgets.kCameraStream);
    }

    public static void update() {
        RobotMap.tank_leftController.setParams(
            getPower(), 
            getOffset(), 
            getDeadZone(), 
            getAccConstant(), 
            getAccProportion(),
            getSpeed()
            );

        RobotMap.tank_rightController.setParams(
            getPower(), 
            getOffset(), 
            getDeadZone(), 
            getAccConstant(), 
            getAccProportion(),
            getSpeed()
            );

        RobotMap.arcade_forwardController.setParams(
            getPower(), 
            getOffset(), 
            getDeadZone(), 
            getAccConstant(), 
            getAccProportion(),
            getSpeed()
            );

        RobotMap.arcade_turnController.setParams(
            getPower(), 
            getOffset(), 
            getDeadZone(), 
            getAccConstant(), 
            getAccProportion(),
            getSpeed()
            );

        RobotMap.arcade = getArcade();
        //System.out.println(RobotMap.arcade);
    }

    public static double getOffset() {
        return offset.getDouble(RobotMap.OFFSET);
    }

    public static double getPower() {
        return pow.getDouble(RobotMap.POWER);
    }

    public static double getAccConstant() {
        return accCon.getDouble(RobotMap.CONST_ACCEL);
    }

    public static double getAccProportion() {
        return accPro.getDouble(RobotMap.PROP_ACCEL);
    }

    public static double getDeadZone() {
        return dzn.getDouble(RobotMap.DEADZONE);
    }

    public static double getSpeed() {
        return speed.getDouble(RobotMap.SPEED);
    }

    public static boolean getArcade() {
        return arcade.getBoolean(RobotMap.arcade);
    }
 }
