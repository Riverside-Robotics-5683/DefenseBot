// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package ravenrobotics.defensebot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import ravenrobotics.defensebot.Constants.DSConstants;
import ravenrobotics.defensebot.commands.DriveCommand;
import ravenrobotics.defensebot.subsystems.DriveSubsystem;

public class RobotContainer {
  //The CommandXboxController object so we can use inputs from the driver.
  private final CommandXboxController driveController = new CommandXboxController(DSConstants.kDriverController);
  
  //The drive command so we can pass the controller inputs and drive the robot.
  private final Command driveCommand = new DriveCommand(
    () -> driveController.getLeftY(),
    () -> driveController.getRightX());

  public RobotContainer() {
    //Get the active instance of DriveSubsystem, and set the default command to the driveCommand object so that we can always drive when enabled.
    DriveSubsystem.getInstance().setDefaultCommand(driveCommand);
  }
}
