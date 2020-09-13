/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//determine all my stuffs here!
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;

public class Flywheel extends SubsystemBase {
  public final static SpeedController m_flywheel = new PWMVictorSPX(6);
  /**
   * 
   * Creates a new Flywheel.
   */
  public Flywheel() {


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
