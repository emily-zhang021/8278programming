/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//determine all my stuffs here!
public class Intake extends SubsystemBase {

public final static SpeedController m_intake = new Talon(8);
public final static SpeedController m_conveyor = new PWMVictorSPX(5);

  /**
   * Creates a new Intake.
   */
  public Intake() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
