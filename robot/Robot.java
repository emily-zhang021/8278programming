package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystems.Drivetrain;

public class Robot extends TimedRobot {
  private final static SpeedController m_leftMaster = new PWMVictorSPX(0);
  private final static SpeedController m_leftFollower = new PWMVictorSPX(1);
  private final static SpeedController m_rightMaster = new PWMVictorSPX(2);
  private final static SpeedController m_rightFollower = new PWMVictorSPX(3);

  private final XboxController m_controller = new XboxController(0);
  private final Drivetrain m_drive = new Drivetrain();

  public final static SpeedControllerGroup m_leftGroup = new SpeedControllerGroup(m_leftMaster, m_leftFollower);
  public final static SpeedControllerGroup m_rightGroup = new SpeedControllerGroup(m_rightMaster, m_rightFollower);
  private DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftGroup, (m_rightGroup));

  // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
  private final SlewRateLimiter m_speedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter m_rotLimiter = new SlewRateLimiter(3);
  public double JoyLeftY=m_controller.getY(GenericHID.Hand.kRight);
  public double JoyRightY=m_controller.getY(GenericHID.Hand.kLeft);
  public double idealSpeed=0.5;
  private boolean turbo=false;
  @Override
  public void autonomousPeriodic() {
    teleopPeriodic();
    m_drive.updateOdometry();
  }

  @Override
  public void teleopPeriodic() {
    System.out.print("Teleop Start!");

    m_robotDrive.tankDrive(-m_controller.getY(GenericHID.Hand.kLeft), -m_controller.getY(GenericHID.Hand.kRight));

    
    if (m_controller.getStartButtonPressed()==true) {
      if (turbo==false) {
        turbo=true;
        System.out.println("TURBO ACTIVATE");
      }
      else if (turbo==true){
        turbo=false;
        System.out.println("TURBO DEACTIVATE");
      }

      
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
