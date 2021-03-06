package org.usfirst.frc.team1758.robot.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private CANTalon rf_Motor, rb_Motor, lf_Motor, lb_Motor;
	private RobotDrive tmDrive;
	private Logger logger;

	public enum Motor {
		FrontRight, FrontLeft, BackRight, BackLeft
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public DriveTrain() {
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Creating DriveTrain subsystem");
		rf_Motor = new CANTalon(RobotMap.RIGHT_FRONT_MOTOR);
		rb_Motor = new CANTalon(RobotMap.RIGHT_BACK_MOTOR);
		lf_Motor = new CANTalon(RobotMap.LEFT_FRONT_MOTOR);
		lb_Motor = new CANTalon(RobotMap.LEFT_BACK_MOTOR);
		configureEncoders(RobotMap.ENCODER_CODES_PER_REVOLUTION);
		tmDrive = new RobotDrive(lf_Motor, lb_Motor, rf_Motor, rb_Motor);
		tmDrive.setInvertedMotor(MotorType.kFrontRight, true);
		tmDrive.setInvertedMotor(MotorType.kRearRight, true);
	}

	public void mecanumDriveCartesian(double x, double y, double rotation, double gyroAngle) {
		logger.trace("Mecanum Cartesian: X:{} Y:{} Rotation:{} Gyro:{}", x, y, rotation, gyroAngle);
		tmDrive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
	}

	public void mecanumDrivePolar(double magnitude, double direction, double rotation) {
		logger.trace("Mecanum Polar: Magnitude:{} Direction:{} Rotation:{}", magnitude, direction, rotation);
		tmDrive.mecanumDrive_Polar(magnitude, direction, rotation);
	}

	public void tankDrive(double left, double right) {
		logger.trace("Tank: Left:{} Right:{}", left, right);
		tmDrive.tankDrive(left, right);
	}

	public void configureEncoders(int codesPerRev) {
		logger.debug("Configuring the Encoders to {} per revolution", codesPerRev);
		lf_Motor.configEncoderCodesPerRev(codesPerRev);
		lb_Motor.configEncoderCodesPerRev(codesPerRev);
		rf_Motor.configEncoderCodesPerRev(codesPerRev);
		rb_Motor.configEncoderCodesPerRev(codesPerRev);
	}

	public void resetEncoderPosition() {
		logger.debug("Reseting encoder position");
		lf_Motor.setEncPosition(0);
		rf_Motor.setEncPosition(0);
		lb_Motor.setEncPosition(0);
		rb_Motor.setEncPosition(0);
	}

	public int getEncoderVelocity(Motor motor) {
		switch (motor) {
		case FrontLeft:
			return lf_Motor.getEncVelocity();
		case FrontRight:
			return rf_Motor.getEncVelocity();
		case BackLeft:
			return lb_Motor.getEncVelocity();
		default: //BackRight
			return rb_Motor.getEncVelocity();
		}
	}

	public int getEncoderPosition(Motor motor) {
		switch (motor) {
		case FrontLeft:
			return lf_Motor.getEncPosition();
		case FrontRight:
			return rf_Motor.getEncPosition();
		case BackLeft:
			return lb_Motor.getEncPosition();
		default: //BackRight
			return rb_Motor.getEncPosition();
		}
	}
}
