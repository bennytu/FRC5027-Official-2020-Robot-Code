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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
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

    Joystick joy1; //inputs for Joystick
    Joystick joy2;

    VictorSPX leftFrontMotor;
    VictorSPX leftBackMotor;

    SpeedControllerGroup leftMotorGroup;

    VictorSPX rightFrontMotor;
    VictorSPX rightBackMotor;

    SpeedControllerGroup rightMotorGroup;

    DifferentialDrive m_drive;

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

        joy1 = new Joystick(0);

        leftFrontMotor = new VictorSPX(can1);
        leftBackMotor = new VictorSPX(can2);

        // leftMotorGroup = new SpeedControllerGroup(leftFrontMotor,leftBackMotor); //Grouping Left Motor

        rightFrontMotor = new VictorSPX(can3);
        rightBackMotor = new VictorSPX(can4);

        // rightMotorGroup = new SpeedControllerGroup(rightFrontMotor,rightBackMotor); //Grouping Right Motor

        m_drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

    }

    @Override

    public void autonomousInit() {

        timeCount = new Timer();

        timeCount.start();

    }



    @Override

    public void autonomousPeriodic() {

        if (timeCount.get() < 10) {

            leftMotorGroup.set(1);

            rightMotorGroup.set(1);

        }

    }



    @Override

    public void teleopInit() {

        int turnSpeed = joy1.getX();

        int fbSpeed = joy1.getY();

        int turnSpeedr = joy2.getX();

        int fbSpeedr = joy2.getY();


    }



    @Override

    public void teleopPeriodic() {
      int stick1 = turnSpeed.getRawAxis(1);
      int stick2 = fbSpeed.getRawAxis(1);
      int stick3 = turnSpeedr.getRawAxis(1);
      int stick4 = fbSpeedr.getRawAxis(1);
      m_drive.arcadeDrive(fbSpeed, turnSpeed);
      leftFrontMotor.set(ControlMode.PercentOutput, stick1);
      leftBackMotor.set(ControlMode.PercentOutput, stick2);
      rightFrontMotor.set(ControlMode.PercentOutput, stick3);
      rightBackMotor.set(ControlMode.PercentOutput, stick4);


    }



    @Override

    public void testInit() {



    }



    @Override

    public void testPeriodic() {

    }


}
