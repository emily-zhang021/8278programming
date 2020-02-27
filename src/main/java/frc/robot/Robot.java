package frc.robot;


import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;



public class Robot extends TimedRobot {
  

  private final XboxController m_controller = new XboxController(0);
  // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
  public double JoyLeftY=m_controller.getY(GenericHID.Hand.kRight);
  public double JoyRightY=m_controller.getY(GenericHID.Hand.kLeft);
  public double idealSpeed=.75;
  private boolean turbo=false;
  private double leftBumper;
  private double rightBumper;
  private boolean intakeUp;
  private boolean intakeDown;
  

  @Override
  public void teleopInit() {
    CameraServer.getInstance().startAutomaticCapture();
  }
 
  
  @Override
  public void autonomousPeriodic() {
    
    
    
  }

  @Override
  public void teleopPeriodic() {
    leftBumper = m_controller.getTriggerAxis(Hand.kLeft);
    rightBumper = m_controller.getTriggerAxis(Hand.kRight);
    intakeUp = m_controller.getYButton();
    intakeDown = m_controller.getAButton();


    //Intake.m_intake.set(0.5);
      
    SmartDashboard.putNumber("Joystick X value", m_controller.getY(GenericHID.Hand.kRight));
    SmartDashboard.putNumber("gyro", Drivetrain.gyro.getAngle());
    SmartDashboard.putBoolean("intake", intakeUp);
    SmartDashboard.putNumber("rotationl", m_controller.getTriggerAxis(Hand.kLeft));
    SmartDashboard.putNumber("rotationr", m_controller.getTriggerAxis(Hand.kRight));


    Drivetrain.m_robotDrive.tankDrive(-m_controller.getTriggerAxis(Hand.kLeft)*idealSpeed, m_controller.getTriggerAxis(Hand.kLeft)*idealSpeed);
    Drivetrain.m_robotDrive.tankDrive(m_controller.getTriggerAxis(Hand.kRight)*idealSpeed, -m_controller.getTriggerAxis(Hand.kRight)*idealSpeed);

    if (turbo==false) {
      Drivetrain.m_robotDrive.tankDrive(m_controller.getY(GenericHID.Hand.kLeft)*idealSpeed, m_controller.getY(GenericHID.Hand.kRight)*idealSpeed);
      }
    if (turbo==true) {
      Drivetrain.m_robotDrive.tankDrive(m_controller.getY(GenericHID.Hand.kLeft), m_controller.getY(GenericHID.Hand.kRight));
    }
    if (m_controller.getStartButtonPressed()==true) {
      if (turbo==false) {
        turbo=true;
        System.out.println("TURBO ACTIVATE");
      }
      else if (turbo==true){
        turbo=false;
        System.out.println("TURBO DEACTIVATE");
      }
      
      


      leftBumper = m_controller.getTriggerAxis(Hand.kLeft);
      rightBumper = m_controller.getTriggerAxis(Hand.kRight);

      Drivetrain.m_robotDrive.tankDrive(-leftBumper, leftBumper);
      Drivetrain.m_robotDrive.tankDrive(rightBumper, -rightBumper);
      
    }
    
   /* // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    final var xSpeed =
        -m_speedLimiter.calculate(m_controller.getY(GenericHID.Hand.kLeft))
            * Drivetrain.kMaxSpeed;

    // Get the rate of angular rotation. We are inverting this because we want a
    // positive value when we pull to the left (remember, CCW is positive in
    // mathematics). Xbox controllers return positive values when you pull to
    // the right by default.
    final var rot =
        -m_rotLimiter.calculate(m_controller.getX(GenericHID.Hand.kRight))
            * Drivetrain.kMaxAngularSpeed;

    m_drive.drive(xSpeed, rot);
    */
  }

  
}
