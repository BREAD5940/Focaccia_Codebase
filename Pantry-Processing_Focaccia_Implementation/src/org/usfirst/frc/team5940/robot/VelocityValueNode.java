package org.usfirst.frc.team5940.robot;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class VelocityValueNode extends ValueNode <Float>{

	public VelocityValueNode(Network network, Logger logger, TalonSRX talon)
			throws IllegalArgumentException, IllegalStateException {
		super(network, logger);
	
	}
		float velocity = 0;
		// If button b is pressed, velocity = 1. If button y is pressed then velocity = -1
		
		

		@Override
		protected Float updateValue() {

			return velocity;
		}
}
