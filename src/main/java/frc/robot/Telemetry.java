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
    private static NetworkTableEntry coeff, pow, accCon, accPro;
    private static NetworkTableEntry test = Shuffleboard.getTab("Drive Control").add("test", 0.0).getEntry();

    public static void init() {
        coeff = Shuffleboard.getTab("Drive Control")
        .add("Coefficient", 1.0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", -1.0, "max", 1.0))
        .getEntry();
        pow = Shuffleboard.getTab("Drive Control")
        .add("Power", 2.0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 1.0, "max", 5.0))
        .getEntry();
        accCon = Shuffleboard.getTab("Drive Control")
        .add("Constant Accelecation", 0.5)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0.0, "max", 2.0))
        .getEntry();
        accPro = Shuffleboard.getTab("Drive Control")
        .add("Proportional Accelecation", 0.5)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0.0, "max", 2.0))
        .getEntry();

        Shuffleboard.getTab("Drive Control")
        .add("Drive Base", RobotMap.diffDrive)
        .withWidget(BuiltInWidgets.kDifferentialDrive);
    }

    public static void update() {
        test.setDouble(pow.getValue().getDouble());
    }

    public static double getCoefficient() {
        return coeff.getValue().getDouble();
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
 }
