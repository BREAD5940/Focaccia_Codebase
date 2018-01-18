package org.usfirst.frc.team5940.robot;

import org.team5940.pantry.logging.loggers.Logger;
import org.team5940.pantry.processing_network.Network;
import org.team5940.pantry.processing_network.ValueNode;

public class SpeedControlNode extends ValueNode<Float> {
	public SpeedControlNode(Network network, Logger logger, boolean isLeft, ValueNode[] sourcesArray)
			throws IllegalArgumentException, IllegalStateException {
		super(network, logger);
		this.isLeft = isLeft;
	}
	boolean isLeft;
	float steering;
	int velocityRight;
	int velocityLeft = 0 - velocityRight;
	float accelerationVector = 20;
	float speedControlRight = 0;
	float speedControlLeft = 0;
	float potentialAcceleration = 0;
	float tpsRight = 0;
	float tpsLeft = 0;

	public void Gear1() {

		if (!(tpsRight < 1) || !(tpsLeft > -0.5) || !(tpsRight > -0.5) || !(tpsLeft < 1)) {
			for (tpsRight = velocityRight / accelerationVector + steering / 2; tpsRight < 1;) {
				accelerationVector = accelerationVector - 1;
			}
			for (tpsLeft = velocityLeft / accelerationVector - steering / 2; tpsLeft < 1;) {
				accelerationVector = accelerationVector - 1;
			}

		}
	}

	public void Gear2() {
		if (tpsRight < 1 && tpsLeft < 1) {
			if (tpsLeft > tpsRight) {
				potentialAcceleration = tpsLeft / tpsRight;

				tpsLeft = tpsLeft * potentialAcceleration;
				tpsRight = tpsRight * potentialAcceleration;

			} else if (tpsRight > tpsLeft) {
				potentialAcceleration = tpsRight / tpsLeft;

				tpsLeft = tpsLeft * potentialAcceleration;
				tpsRight = tpsRight * potentialAcceleration;
			} else {
				potentialAcceleration = 1 / ((tpsRight + tpsLeft) / 2);

				tpsLeft = tpsLeft * potentialAcceleration;
				tpsRight = tpsRight * potentialAcceleration;

			}

		}

		if (!(speedControlRight < tpsRight) || !(tpsLeft > -0.5) || !(tpsRight > -0.5)
				|| !(speedControlLeft < tpsLeft)) {
			for (speedControlRight = velocityRight / accelerationVector + steering / 2; speedControlRight < tpsRight;) {
				accelerationVector = accelerationVector - 1;

			}
			for (speedControlLeft = velocityLeft / accelerationVector + steering / 2; speedControlLeft < tpsLeft;) {
				accelerationVector = accelerationVector - 1;

			}

		}

	}

	@Override
	protected Float updateValue() {
		if (tpsRight < 1 && tpsLeft < 1) {
			if (tpsLeft > tpsRight) {
				potentialAcceleration = tpsLeft / tpsRight;

				tpsLeft = tpsLeft * potentialAcceleration;
				tpsRight = tpsRight * potentialAcceleration;

			} else if (tpsRight > tpsLeft) {
				potentialAcceleration = tpsRight / tpsLeft;

				tpsLeft = tpsLeft * potentialAcceleration;
				tpsRight = tpsRight * potentialAcceleration;
			} else {
				potentialAcceleration = 1 / ((tpsRight + tpsLeft) / 2);

				tpsLeft = tpsLeft * potentialAcceleration;
				tpsRight = tpsRight * potentialAcceleration;

			}

		}

		if (!(speedControlRight < tpsRight) || !(tpsLeft > -0.5) || !(tpsRight > -0.5)
				|| !(speedControlLeft < tpsLeft)) {
			for (speedControlRight = velocityRight / accelerationVector + steering / 2; speedControlRight < tpsRight;) {
				accelerationVector = accelerationVector - 1;

			}
			for (speedControlLeft = velocityLeft / accelerationVector + steering / 2; speedControlLeft < tpsLeft;) {
				accelerationVector = accelerationVector - 1;

			}

		}

		if (isLeft) {
			return speedControlLeft;
		} else {
			return speedControlRight;
		}
	}
}
