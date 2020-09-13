package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Spinner;
import edu.wpi.first.wpilibj.util.Color;






public class Robot extends TimedRobot {
  

  private final XboxController m_controller = new XboxController(0);
  // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
  public double JoyLeftY=m_controller.getY(GenericHID.Hand.kRight);
  public double JoyRightY=m_controller.getY(GenericHID.Hand.kLeft);
  public double idealSpeed=0.9;
  private boolean turbo=false;
  private boolean intakeUp;
  private boolean intakeDown;
  private boolean conveyorUp;
  private boolean conveyorDown;
  private boolean flywheel;
  private int flycheck=0;
  private boolean spinner;
  private int spincheck=0;
  private boolean liftUp;
  private boolean liftDown;
  private boolean liftSpinnerUp;
  private boolean liftSpinnerDown;
  private int liftSpinnerCheck=0;
  private boolean intake;
  private int intakecheck=0;
  
  
  private DoubleSolenoid wheelMover = new DoubleSolenoid(2,1);
  

  @Override
  public void teleopInit() {

    
    CameraServer.getInstance().startAutomaticCapture();
    wheelMover.set(DoubleSolenoid.Value.kOff);
    Flywheel.m_flywheel.set(0);
    Spinner.m_spinner.set(0);
    
  }
 
  
  @Override
  public void autonomousPeriodic() {

     Drivetrain.m_robotDrive.tankDrive(0.5,0.5);
      Timer.delay(3);
      Drivetrain.m_robotDrive.tankDrive(0,0);

    
    
  }

  @Override
  public void teleopPeriodic() {
    MotorSafety.checkMotors();
    DifferentialDrive.checkMotors();
    //leftRotate = m_controller.getTriggerAxis(Hand.kLeft);
    //rightRotate = m_controller.getTriggerAxis(Hand.kRight);
    //intakeUp = m_controller.getBButton();
    intakeDown = m_controller.getXButton();
    conveyorUp=m_controller.getYButton();
    conveyorDown=m_controller.getAButton();
    spinner = m_controller.getBumperPressed(Hand.kLeft);
    flywheel = m_controller.getBumperPressed(Hand.kRight);
    intake = m_controller.getBButtonPressed();
    liftUp = m_controller.getStartButtonPressed();
    liftDown=m_controller.getBackButton();


    if (intake==true) {
      if (intakecheck==0) {
      intakecheck=1;
      }
      else {
        intakecheck=0;
      }
    }
    if (intakecheck==1) {
      Intake.m_intake.set(0.25);
    }

    if (intakecheck==0) {
      Intake.m_intake.set(0);
    }

  
    
    if (liftUp==true) {
      if (liftSpinnerCheck==0) {
      liftSpinnerCheck=1;
      }
      else {
        liftSpinnerCheck=0;
      }
    }
    if (liftSpinnerCheck==1) {
      wheelMover.set(DoubleSolenoid.Value.kReverse);
    }

    if (liftSpinnerCheck==0) {
      wheelMover.set(DoubleSolenoid.Value.kForward);
    }

  
    

    

    if ( m_controller.getPOV()==90) {
      Lift.m_winch.set(1);
    }
    else if (m_controller.getPOV()==270) {
      Lift.m_winch.set(-1);
    }
    else {
      Lift.m_winch.set(0);
    }


    if ( m_controller.getPOV()==0) {
      Lift.m_lift.set(-1);
    }
    else if (m_controller.getPOV()==180) {
      Lift.m_lift.set(1);
    }
    else {
      Lift.m_lift.set(0);
    }
    
    
    



    //Intake.m_intake.set(0.5);
      
    SmartDashboard.putNumber("Joystick X value", m_controller.getY(GenericHID.Hand.kRight));
    //SmartDashboard.putNumber("gyro", Drivetrain.gyro.getAngle());
    SmartDashboard.putNumber("intake", intakecheck);
    SmartDashboard.putNumber("flywheel", flycheck);
    SmartDashboard.putNumber("rotationl", m_controller.getTriggerAxis(Hand.kLeft));
    SmartDashboard.putNumber("rotationr", m_controller.getTriggerAxis(Hand.kRight));
    SmartDashboard.putNumber("dpad", m_controller.getPOV());

    Spinner.m_spinner.set(m_controller.getTriggerAxis(Hand.kRight));
    Spinner.m_spinner.set(-m_controller.getTriggerAxis(Hand.kLeft));

    

    if (flywheel==true) {
      if (flycheck==0) {
      flycheck=1;
      }
      else {
        flycheck=0;
      }
    }
    if (flycheck==1) {
      Flywheel.m_flywheel.set(1);
    }

    if (flycheck==0) {
      Flywheel.m_flywheel.set(0);
    }

    if (spinner==true) {
      if (spincheck==0) {
      spincheck=1;
      }
      else {
        spincheck=0;
      }
    }
    if (spincheck==1) {
      Spinner.m_spinner.set(0.2);
    }

    if (spincheck==0) {
      Spinner.m_spinner.set(0);
    }

    //Drivetrain.m_robotDrive.tankDrive(-rightRotate, -rightRotate);
    //Drivetrain.m_robotDrive.tankDrive(-leftRotate, leftRotate);
    
  
    
    

    if (conveyorUp==true) {
      Intake.m_conveyor.set(-1);
    }
    else if (conveyorDown==true){
      Intake.m_conveyor.set(1);
    }
    else {
      Intake.m_conveyor.set(0);
    }
    
    


    if (turbo==false) {
      Drivetrain.m_robotDrive.tankDrive(m_controller.getY(GenericHID.Hand.kLeft)*idealSpeed, m_controller.getY(GenericHID.Hand.kRight)*idealSpeed);
      }
    if (turbo==true) {
      Drivetrain.m_robotDrive.tankDrive(m_controller.getY(GenericHID.Hand.kLeft), m_controller.getY(GenericHID.Hand.kRight));
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
