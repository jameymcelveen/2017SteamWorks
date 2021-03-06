package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;

import ch.qos.logback.core.joran.conditional.ElseAction;

public class MoveForwardUntilSight extends CommandBase {
	private boolean finished;

	public MoveForwardUntilSight() {
		requires(sensors);
		requires(driveTrain);
	}

	protected void initialize() {
		finished = false;
		driveTrain.resetEncoderPosition();
		sensors.resetGyroAngle();
	}

	protected void execute() {
		if (sensors.getGyroAngle() > 30) {
			finished = true;
			driveTrain.tankDrive(0, 0);
		} else {
			driveTrain.tankDrive(.5, 0);
		}
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
