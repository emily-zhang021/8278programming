/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
 

public class Lift extends SubsystemBase {
  public final static SpeedController m_winch = new PWMVictorSPX(4);
  public final static SpeedController m_lift = new PWMVictorSPX(9);
  /**
   * Creates a new Lift.
   */
  public Lift() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
