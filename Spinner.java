/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

//determine all my stuffs here!
public class Spinner extends SubsystemBase {
  public final static SpeedController m_spinner = new Talon(3);
  /**
   * Creates a new Spinner.
   */
  public Spinner() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
