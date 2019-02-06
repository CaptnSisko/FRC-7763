/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import edu.wpi.first.wpilibj.*;

public class UltrasonicSensor {

    private AnalogInput sensor;
    private double[] history;
    private int pos = 0;


    public UltrasonicSensor(AnalogInput sensor, int history) {
        this.sensor = sensor;
        this.history = new double[history];
    }

    private double mean() {
        double sum = 0;
        for (double i : history) {
            sum += i;
        }
        return sum / history.length;
    }

    private void update() {
        history[pos] = sensor.getVoltage();
        pos = pos < (history.length - 1) ? pos + 1 : 0;
    }

    // For use with MB1010 LV-MaxSonar-EZ1 sensor. Distance returned is in cm.
    private static double voltsToDist(double volts) {
        double dist = (3.0 * 5.0 * (volts / (5.0 / 1024.0))) / 10;  // * 3.0 is for calibration. not sure why it needs this?
        System.out.print(volts);
        System.out.print(", ");
        System.out.println(dist);
        return dist;
    }

    // take a reading and return the mean of the stored readings
    public double read() {
        this.update();
        return voltsToDist(this.mean());
    }

}
