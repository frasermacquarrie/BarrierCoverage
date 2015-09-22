/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package me.macquarrie.fraser.barrier;

import java.awt.*;

/**
 *
 * @author Fraser
 */
public class Simulation1DStaggered extends Simulation1D {

	public Simulation1DStaggered(double l, int n, int r, Image img, MainForm p) {
		super(l, n, r, img, p);
	}

	// Find the min-sum
	public void min() {

		optMax = width * 2;

		// Iterate repeatedly to close any gaps that might be missed the first
		// couple iterations
		for (int k = 0; k < 1000; k++) {

			for (int i = 0; i < width; i++) {

				int[] moveDirc = new int[numSensors];
				double distance = width * 2;
				int select = -1;

				for (int j = 0; j < numSensors; j++) {
					// Find the closest node to the current location that can be
					// moved to cover the node if necessary
					if (sensors[j].currentDistanceFrom(i) < distance
							&& ((sensors[j].signedDistanceFrom(i) > 0 && moveDirc[j] >= 0)
									|| (sensors[j].signedDistanceFrom(i) < 0 && moveDirc[j] <= 0))) {
						distance = sensors[j].currentDistanceFrom(i);
						select = j;
					}
				}

				// If there are no nodes in sensor range, move the closest node
				// just
				// enough so that this location falls inside the sensor's range
				if (distance >= sensorRange && distance < width) {
					if (sensors[select].signedDistanceFrom(i) > 0) {
						sensors[select].move(distance - sensorRange);
						moveDirc[select] = (sensorRange - (int) distance);
					} else {
						sensors[select].move(sensorRange - distance);
						moveDirc[select] = (int) distance - sensorRange;
					}
				}
			}
		}

		// Determine the min-max
		double maxDistance = 0;
		for (int i = 0; i < numSensors; i++) {
			if (sensors[i].distanceMoved() > maxDistance) {
				maxDistance = sensors[i].distanceMoved();
			}
		}
		if (maxDistance < optMax) {
			optMax = maxDistance;
		}

		// Determine the min-sum
		optDistance = Sensor1D.totalDistance(sensors, numSensors);

		// Display the calculated information
		parent.setLabels(optDistance / 400, optMax / 400, feasible());
	}

	// The min-max process is identical to the min-sum process
	public void minmax() {
		min();
	}

}