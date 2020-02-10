/*----------------------------------------------------------------------------*/

/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/

//Reprogramed by Huy Le and Adrian Bao

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {

    public static final int can1 = 1;
    public static final int can2 = 2;
    public static final int can3 = 3;
    public static final int can4 = 4;

    Joystick joy1 = new Joystick(4); //inputs for Joystick

    VictorSPX leftFrontMotor = new VictorSPX(can1);
    VictorSPX leftRearMotor  = new VictorSPX(can2);

    VictorSPX rightFrontMotor = new VictorSPX(can3);
    VictorSPX rightRearMotor  = new VictorSPX(can4);;

    //SpeedControllerGroup leftMotorGroup;
    //SpeedControllerGroup rightMotorGroup;
    //DifferentialDrive m_drive;

    //Measurements Values

    double fbSpeed; //front back speed
    double turnSpeed; //turning speed
    double fbSpeedr; //front back speed
    double turnSpeedr; //turning speed

    Timer timeCount;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */

    @Override

    public void robotInit() {

    }

    @Override

    public void autonomousInit() {

        timeCount = new Timer();
        timeCount.reset();
        timeCount.start();

    }



    @Override

    public void autonomousPeriodic() {

      if (timeCount.get() < 3.0) {
        final double stick1 = 0.5;
        double leftMotorPower = stick1;
        double rightMotorPower = stick1 * -1;
        leftFrontMotor.set(ControlMode.PercentOutput, leftMotorPower);
        leftRearMotor.set(ControlMode.PercentOutput, leftMotorPower);
        rightFrontMotor.set(ControlMode.PercentOutput, rightMotorPower);
        rightRearMotor.set(ControlMode.PercentOutput, rightMotorPower);
      }
      else 
      {
        final double stick1 = 0.0;
        double leftMotorPower = stick1;
        double rightMotorPower = stick1 * -1;
        leftFrontMotor.set(ControlMode.PercentOutput, leftMotorPower);
        leftRearMotor.set(ControlMode.PercentOutput, leftMotorPower);
        rightFrontMotor.set(ControlMode.PercentOutput, rightMotorPower);
        rightRearMotor.set(ControlMode.PercentOutput, rightMotorPower); 
      }

    }



    @Override

  public void teleopInit() {

    final double turnSpeed = joy1.getX();
    final double fbSpeed = joy1.getY();   
  }

  @Override

  public void teleopPeriodic() {
    final double stick1 = joy1.getRawAxis(1);
    double leftMotorPower = stick1;
    double rightMotorPower = stick1 * -1;
    leftFrontMotor.set(ControlMode.PercentOutput, leftMotorPower);
    leftRearMotor.set(ControlMode.PercentOutput, leftMotorPower);
    rightFrontMotor.set(ControlMode.PercentOutput, rightMotorPower);
    rightRearMotor.set(ControlMode.PercentOutput, rightMotorPower);
    timeCount = new Timer();
    timeCount.reset();
    timeCount.start();
    if(turnSpeed > 0.0) {
      rightMotorPower = -turnSpeed;
    //  double rightRearMotor = -turnSpeed;
     // double leftFrontMotor = turnSpeed;
      leftMotorPower = turnSpeed;
      leftFrontMotor.set(ControlMode.PercentOutput, leftMotorPower);
       leftRearMotor.set(ControlMode.PercentOutput, leftMotorPower);
       if(timeCount.get() < 0.5) {
      rightFrontMotor.set(ControlMode.PercentOutput, rightMotorPower);
      rightRearMotor.set(ControlMode.PercentOutput, rightMotorPower);
       }
    }
    if(turnSpeed < 0.0) {
      rightMotorPower = turnSpeed;
    //  double rightRearMotor = -turnSpeed;
     // double leftFrontMotor = turnSpeed;
      leftMotorPower = -turnSpeed;
      leftFrontMotor.set(ControlMode.PercentOutput, leftMotorPower);
       leftRearMotor.set(ControlMode.PercentOutput, leftMotorPower);
       if(timeCount.get() < 0.5) {
      rightFrontMotor.set(ControlMode.PercentOutput, rightMotorPower);
      rightRearMotor.set(ControlMode.PercentOutput, rightMotorPower);
     }
    }
  }



    @Override

    public void testInit() {



    }



    @Override

    public void testPeriodic() {

    }


}
