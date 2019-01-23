/**
 * class for containing camera related processes
 */

 package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Vision implements Runnable{

    @Override
    public void run() {

        UsbCamera lifeCam = CameraServer.getInstance().startAutomaticCapture();
        lifeCam.setResolution(640, 480);
    }

 }