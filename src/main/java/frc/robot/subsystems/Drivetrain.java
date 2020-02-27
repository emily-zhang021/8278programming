/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends SubsystemBase {
  private final static SpeedController m_leftMaster = new PWMVictorSPX(0);
  private final static SpeedController m_leftFollower = new PWMVictorSPX(1);
  private final static SpeedController m_rightMaster = new PWMVictorSPX(2);
  private final static SpeedController m_rightFollower = new PWMVictorSPX(3);

  public static final Drivetrain m_drive = new Drivetrain();

  public final static SpeedControllerGroup m_leftGroup = new SpeedControllerGroup(m_leftMaster, m_leftFollower);
  public final static SpeedControllerGroup m_rightGroup = new SpeedControllerGroup(m_rightMaster, m_rightFollower);
  public final static DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftGroup, (m_rightGroup));

  public final static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  /**
   * Creates a new Drivetrain.
   */
  
  public Drivetrain() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
