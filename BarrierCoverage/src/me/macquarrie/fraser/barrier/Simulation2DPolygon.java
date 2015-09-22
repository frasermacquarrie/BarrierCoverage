/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package me.macquarrie.fraser.barrier;

import java.awt.*;
//import java.io.*;

/**
 *
 * @author Fraser
 */
public class Simulation2DPolygon extends Simulation2D {

	public Simulation2DPolygon(int w, int numSens, int sensRange, Image g, MainForm p) {
		super(w / 2, numSens, sensRange, g, p);

		Polygon poly = new Polygon();

		for (int i = 0; i < 50; i++) {

			double x = ((Math.random() - 0.5) * w);
			double y = ((Math.random() - 0.5) * w);

			if (PolygonBoundary.isSimpleWith(poly, new PointF(x, y))) {
				poly.addPoint((int) x, (int) y);
			}
		}

		boundary = new PolygonBoundary(poly);

		for (int i = 0; i < numSensors; i++) {

			double x;
			double y;

			do {
				x = ((Math.random() - 0.5) * w);
				y = ((Math.random() - 0.5) * w);
			} while (!poly.contains(x, y));

			this.sensors[i] = new Sensor2D(new PointF(x, y), sensorRange);
		}
	}

	// Determine whether or not the sensors can protect the barrier
	@Override
	public boolean feasible() {
		return sensorRange * 2 > ((PolygonBoundary) boundary).getPerimeterLength() / numSensors;
	}

	public void logSim() {
		MainForm.writeFile("Simulation: 2D Polygon");
		MainForm.writeFile("======================");
		MainForm.writeFile("");
		if (optDistance != 0) {
			MainForm.writeFile("Min-Sum:  " + String.valueOf(optDistance));
			MainForm.writeFile("Min-Max:  " + String.valueOf(optMax));
			MainForm.writeFile("Feasible: " + (feasible() ? "Yes" : "No" ));
		}
		((PolygonBoundary) boundary).log();

		for (int i = 0; i < numSensors; i++) {
			sensors[i].log();
		}
		MainForm.writeFile("------------------------------------------------------");
		MainForm.writeFile("");

	}
}
