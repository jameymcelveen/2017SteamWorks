package org.usfirst.frc.team1758.robot.commands;

public class ToggleBallPickup extends CommandBase {
	private boolean finished;

	public ToggleBallPickup() {
		requires(ballPickup);
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		if (ballPickup.isEngaged()) {
			ballPickup.disengage();
		} else {
			ballPickup.engage();
		}
		finished = true;
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
