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


    private static ShuffleboardTab DriveControl = Shuffleboard.getTab("Drive Control");
    private static NetworkTableEntry coeff, pow, accCon, accPro;

    public static void init() {
        coeff = DriveControl.add("Coefficient", 1.0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("Min", -1, "Max", 1))
        .getEntry();
        pow = DriveControl.add("Power", 2.0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("Min", 1, "Max", 5))
        .getEntry();
        accCon = DriveControl.add("Constant Accelecation", 0.5)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("Min", 0, "Max", 2))
        .getEntry();
        accPro = DriveControl.add("Proportional Accelecation", 0.5)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("Min", 0, "Max", 2))
        .getEntry();

        Shuffleboard
        .getTab("DriveControl")
        .add("Drive Base", RobotMap.diffDrive)
        .withWidget(BuiltInWidgets.kDifferentialDrive);
    }

    public static void update() {
        //DriveControl.add("left", RobotMap.leftController.getTarget());
    }

    public static double getCoefficient() {
        return coeff.getDouble(1.0);
    }

    public static double getPower() {
        return pow.getDouble(2.0);
    }

    public static double getAccConstant() {
        return accCon.getDouble(0.5);
    }

    public static double getAccProportion() {
        return accPro.getDouble(0.5);
    }
 }
