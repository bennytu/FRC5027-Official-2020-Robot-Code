/*----------------------------------------------------------------------------*/

/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/

//Reprogramed by Huy Le and Adrian Bao

package frc.robot; 



import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.SpeedControllerGroup;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.PWMVictorSPX;



/**

 * The VM is configured to automatically run this class, and to call the

 * functions corresponding to each mode, as described in the TimedRobot

 * documentation. If you change the name of this class or the package after

 * creating this project, you must also update the build.gradle file in the

 * project.

 */

public class Robot extends TimedRobot {

  public static final int pwm1 = 1; 

  public static final int pwm2 = 2; 

  public static final int pwm3 = 3; 

  public static final int pwm4 = 4; 



  Joystick joy1; //inputs for Joystick



  PWMVictorSPX  leftFrontMotor; 

  PWMVictorSPX  leftBackMotor;

  SpeedControllerGroup leftMotorGroup;



  PWMVictorSPX  rightFrontMotor;

  PWMVictorSPX  rightBackMotor;

  SpeedControllerGroup rightMotorGroup;



  DifferentialDrive m_drive;



  //Measurements Values

  double fbSpeed; //front back speed

  double turnSpeed; //turning speed

  Timer timeCount;



  /**

   * This function is run when the robot is first started up and should be used

   * for any initialization code.

   */

  @Override

  public void robotInit() {

    joy1 = new Joystick(0);



    leftFrontMotor = new PWMVictorSPX(pwm1); 

    leftBackMotor = new PWMVictorSPX(pwm2);

    leftMotorGroup = new SpeedControllerGroup(leftFrontMotor,leftBackMotor); //Grouping Left Motor



    rightFrontMotor = new PWMVictorSPX(pwm3);

    rightBackMotor = new PWMVictorSPX(pwm4);

    rightMotorGroup = new SpeedControllerGroup(rightFrontMotor,rightBackMotor); //Grouping Right Motor



    m_drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);



  }

  



  @Override

  public void autonomousInit() {

    timeCount = new Timer();

    timeCount.start();

  }



  @Override

  public void autonomousPeriodic() {

    if (timeCount.get() < 10){

      leftMotorGroup.set(1);

      rightMotorGroup.set(1);

    }

  }



  @Override

  public void teleopInit() {

    turnSpeed = joy1.getX();

    fbSpeed = joy1.getY();

  }



  @Override

  public void teleopPeriodic() {

    m_drive.arcadeDrive(fbSpeed,turnSpeed);

  }



  @Override

  public void testInit() {



  }



  @Override

  public void testPeriodic() {

  }



}