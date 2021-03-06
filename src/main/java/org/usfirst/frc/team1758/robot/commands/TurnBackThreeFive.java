package org.usfirst.frc.team1758.robot.commands;

public class TurnBackThreeFive extends CommandBase {
	private boolean finished;

	public TurnBackThreeFive() {
		requires(sensors);
		requires(driveTrain);
	}

	protected void initialize() {
		finished = false;
		driveTrain.resetEncoderPosition();
		sensors.resetGyroAngle();
	}

	protected void execute() {
		if(sensors.getGyroAngle() < -30)
		{
			finished = true;
			driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
		} else {
			driveTrain.tankDrive(-.75, 0);
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
