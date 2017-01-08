package org.usfirst.frc.team1758.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controller
{
	private Joystick joystick;
	public JoystickButton a, b, x, y, lb, rb;
	public enum Axes {
		LEFT_X(1), RIGHT_X(4), LEFT_Y(2), RIGHT_Y(5), TRIGGER(3);
		public int port;
		Axes(int port)
		{
			this.port = port;
		}
		public int getPort()
		{
			return port;
		}
	}
	public Controller(int port)
	{
		joystick = new Joystick(port);
		a = new JoystickButton(joystick, 1);
		b = new JoystickButton(joystick, 2);
		x = new JoystickButton(joystick, 3);
		y = new JoystickButton(joystick, 4);
		lb = new JoystickButton(joystick, 5);
		rb = new JoystickButton(joystick, 6);
	}
	public double getRawAxis(Axes axes){
		return joystick.getRawAxis(axes.getPort());
	}

}