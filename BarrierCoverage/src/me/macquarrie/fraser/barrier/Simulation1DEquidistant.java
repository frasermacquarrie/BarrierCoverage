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
public class Simulation1DEquidistant extends Simulation1D {

	public Simulation1DEquidistant(double l, int n, int r, Image img, MainForm p) {
		super(l, n, r, img, p);
	}

	// Find the min-sum
	public void min() {
		optMax = width * 2;

		double nodes[] = new double[numSensors];
		nodes[0] = (double) width / ((double) numSensors * 2.0);

		// Find the locations where the sensors should be on the barrier
		for (int i = 1; i < numSensors; i++) {
			nodes[i] = nodes[i - 1] + (double) width / (double) numSensors;
		}

		// Move the sensors to the nodes on the barrier
		for (int i = 0; i < numSensors; i++) {
			sensors[i].moveTo(nodes[i]);
		}

		boolean sort = true;

		// Iterate pair-wise until fully sorted
		while (sort) {
			sort = false;
			for (int i = 0; i < numSensors; i++) {
				for (int j = i; j < numSensors; j++) {
					// If it reduces total distance, swap the position of two
					// sensors.
					if (sensors[i].distanceMoved()
							+ sensors[j].distanceMoved() > sensors[i].distanceFrom(sensors[j].getLocation())
									+ sensors[j].distanceFrom(sensors[i].getLocation())) {
						double temp = ((Sensor1D) sensors[i]).getLocation();
						sensors[i].moveTo(sensors[j].getLocation());
						sensors[j].moveTo(temp);
						sort = true;
					}
				}
			}
		}

		// Find the longest amount a sensor had to move
		double maxDistance = 0;
		for (int i = 0; i < numSensors; i++) {
			if (sensors[i].distanceMoved() > maxDistance) {
				maxDistance = sensors[i].distanceMoved();
			}
		}
		if (maxDistance < optMax) {
			optMax = maxDistance;
		}

		// Find the total distance moved by all sensors
		optDistance = Sensor1D.totalDistance(sensors, numSensors);

		// Display the calculated information
		parent.setLabels(optDistance / 400, optMax / 400, feasible());
	}

	// Find the min-max
	public void minmax() {

		optMax = width * 2;

		double nodes[] = new double[numSensors];
		nodes[0] = (double) width / ((double) numSensors * 2.0);

		// Find the locations where the sensors should be on the barrier
		for (int i = 1; i < numSensors; i++) {
			nodes[i] = nodes[i - 1] + (double) width / (double) numSensors;
		}

		// Move the sensors to the nodes on the barrier
		for (int i = 0; i < numSensors; i++) {
			((Sensor1D) sensors[i]).moveTo(nodes[i]);
		}

		boolean sort = true;

		// Iterate pair-wise until fully sorted
		while (sort) {
			sort = false;
			for (int i = 0; i < numSensors; i++) {
				for (int j = i; j < numSensors; j++) {
					// If it reduces longer sensor movement, swap the position
					// of two sensors.
					if (Math.max(sensors[i].distanceMoved(), sensors[j].distanceMoved()) > Math.max(
							sensors[i].distanceFrom(sensors[j].getLocation()),
							sensors[j].distanceFrom(sensors[i].getLocation()))) {
						double temp = sensors[i].getLocation();
						sensors[i].moveTo(sensors[j].getLocation());
						sensors[j].moveTo(temp);
						sort = true;
					}
				}
			}
		}

		// Find the longest amount a sensor had to move
		double maxDistance = 0;
		for (int i = 0; i < numSensors; i++) {
			if (sensors[i].distanceMoved() > maxDistance) {
				maxDistance = sensors[i].distanceMoved();
			}
		}
		if (maxDistance < optMax) {
			optMax = maxDistance;
		}

		// Find the total distance moved by all sensors
		optDistance = Sensor1D.totalDistance(sensors, numSensors);

		// Display the calculated information
		parent.setLabels(optDistance / 400, optMax / 400, feasible());
	}

}
/**/