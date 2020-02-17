/*----------------------------------------------------------------------------*/

/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */

/* Open Source Software - may be modified and shared by FRC teams. The code   */

/* must be accompanied by the FIRST BSD license file in the root directory of */

/* the project.                                                               */

/*----------------------------------------------------------------------------*/

//Reprogramed by Huy Le and Adrian Bao and Tiffany Tran

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import com.ctre.phoenix.motorcontrol.can.*;


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
    public static final int can5 = 5;
    public static final int can6 = 6;
    public static final int buttonX = 0;
    public static final int buttonA = 1;
    public static final int buttonB = 2;
    public static final int buttonY = 3;

    Joystick joy1 = new Joystick(4); //inputs for Joystick
    JoystickButton LowPowerThrow = new JoystickButton(joy1, buttonX); 
    JoystickButton MediumPowerThrow = new JoystickButton(joy1, buttonA);
    JoystickButton MediumHighPowerThrow = new JoystickButton(joy1, buttonB);
    JoystickButton HighPowerThrow = new JoystickButton(joy1, buttonY);
    WPI_VictorSPX leftFrontMotor = new WPI_VictorSPX(can1);
    WPI_VictorSPX rightFrontMotor = new WPI_VictorSPX(can3);
    WPI_VictorSPX leftRearMotor = new WPI_VictorSPX(can2);
    WPI_VictorSPX rightRearMotor = new WPI_VictorSPX(can4);
    WPI_VictorSPX shooter_leftmotor = new WPI_VictorSPX(can5);
    WPI_VictorSPX shooter_rightmotor = new WPI_VictorSPX(can6);
    DifferentialDrive _drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
    double powerLevel = 0.0;
  /*  VictorSPX leftFrontMotor = new VictorSPX(can1);
    VictorSPX leftRearMotor  = new VictorSPX(can2);

    VictorSPX rightFrontMotor = new VictorSPX(can3);
    VictorSPX rightRearMotor  = new VictorSPX(can4);
*/
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
      //  final double xcontrol = 0.0;
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
  /* factory default values */
  leftFrontMotor.configFactoryDefault();
  rightFrontMotor.configFactoryDefault();

  /* flip values so robot moves forward when stick-forward/LEDs-green */
  leftFrontMotor.setInverted(false); // <<<<<< Adjust this
  rightFrontMotor.setInverted(true); // <<<<<< Adjust this

  /*
   * WPI drivetrain classes defaultly assume left and right are opposite. call
   * this so we can apply + to both sides when moving forward. DO NOT CHANGE
   */
  _drive.setRightSideInverted(false);
   /* final double turnSpeed = joy1.getX();
    final double fbSpeed = joy1.getY(); */

  }

  @Override

  public void teleopPeriodic() {
    double xSpeed = joy1.getRawAxis(1) * -1; // make forward stick positive
    double zRotation = joy1.getRawAxis(2); // WPI Drivetrain uses positive=> right
    boolean lowPowerThrow = joy1.getRawButton(buttonX);
    boolean MediumPowerThrow = joy1.getRawButton(buttonA);
    boolean MediumHighPowerThrow = joy1.getRawButton(buttonB);
    boolean HighPowerThrow = joy1.getRawButton(buttonY);

    _drive.arcadeDrive(xSpeed, zRotation);

    /* hold down btn1 to print stick values */
    if (joy1.getRawButton(1)) {
      System.out.println("xSpeed:" + xSpeed + "    zRotation:" + zRotation);
      }

    if(lowPowerThrow) {
      // Run shooter motors at low speed
      powerLevel = 0.25;
    } else if(MediumPowerThrow) {
      // Run shooter motors at medium speed
     powerLevel = 0.5;
    } else if(MediumHighPowerThrow) {
      // Run shooter motors at medium-high speed
     powerLevel = 0.75;
    } else if(HighPowerThrow) {
      // Run shooter motors at high speed
     powerLevel = 1.0; 
      }
      double shooter_leftmotor_power = powerLevel;
      double shooter_rightmotor_power = powerLevel * -1;
      shooter_leftmotor.set(ControlMode.PercentOutput, shooter_leftmotor_power);
      shooter_rightmotor.set(ControlMode.PercentOutput, shooter_rightmotor_power);
    }
    /*
    final double stick1 = joy1.getRawAxis(1);
    final double xcontrol = joy1.getRawAxis(1);
    double leftMotorPower = stick1;
    double rightMotorPower = stick1 * -1;
    leftFrontMotor.set(ControlMode.PercentOutput, leftMotorPower);
    leftRearMotor.set(ControlMode.PercentOutput, leftMotorPower);
    rightFrontMotor.set(ControlMode.PercentOutput, rightMotorPower);
    rightRearMotor.set(ControlMode.PercentOutput, rightMotorPower);
    timeCount = new Timer();
    timeCount.reset();
    timeCount.start();
    double leftMotorPowerd = xcontrol;
    double rightMotorPowerd = xcontrol * -1;
    if(turnSpeed > 0.0) {
      rightMotorPower = -turnSpeed;
    //  double rightRearMotor = -turnSpeed;
     // double leftFrontMotor = turnSpeed;
      leftMotorPower = turnSpeed;
       if(timeCount.get() < 3.0) {
        leftFrontMotor.set(ControlMode.PercentOutput, leftMotorPowerd);
        leftRearMotor.set(ControlMode.PercentOutput, leftMotorPowerd);
        rightFrontMotor.set(ControlMode.PercentOutput, rightMotorPowerd);
        rightRearMotor.set(ControlMode.PercentOutput, rightMotorPowerd);
       }
    } 
    if(turnSpeed < 0.0) {
      rightMotorPower = turnSpeed;
      leftMotorPower = -turnSpeed;
       if(timeCount.get() < 3.0) {
        leftFrontMotor.set(ControlMode.PercentOutput, leftMotorPower);
        leftRearMotor.set(ControlMode.PercentOutput, leftMotorPower);
        rightFrontMotor.set(ControlMode.PercentOutput, rightMotorPower);
        rightRearMotor.set(ControlMode.PercentOutput, rightMotorPower);
     }
    }
  }


*/
    @Override

    public void testInit() {



    }



    @Override

    public void testPeriodic() {

    }


}
