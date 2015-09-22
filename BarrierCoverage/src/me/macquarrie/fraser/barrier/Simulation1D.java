package me.macquarrie.fraser.barrier;

import java.awt.*;

/**
 * 
 * @author Fraser
 */
public abstract class Simulation1D extends Simulation {

	protected Sensor1D[] sensors;

	public Simulation1D(double l, int n, int r, Image img, MainForm p) {
		parent = p;
		numSensors = n;
		sensorRange = r;
		Sensor1D.height = l / 2;
		width = l;
		graph = img;
		sensors = new Sensor1D[numSensors];
		for (int i = 0; i < numSensors; i++) {
			sensors[i] = new Sensor1D(Math.random() * width, sensorRange);
		}

	}

	public abstract void min();

	public abstract void minmax();

	// Draw a graph of the simulation
	public void draw(Graphics g) {
		Graphics grap = graph.getGraphics();
		grap.setColor(Color.WHITE);
		grap.fillRect(0, 0, (int) width, (int) width);

		grap.setColor(Color.BLUE);
		grap.drawLine(0, (int) width / 2, (int) width, (int) width / 2);
		for (int i = 0; i < numSensors; i++) {
			sensors[i].draw(grap);
		}
		g.drawImage(graph, 0, 0, 400, 400, null);

	}

	// Reset each sensor to its original position
	public void reset() {
		for (int i = 0; i < numSensors; i++) {
			sensors[i].unmove();
		}
	}

	// Determine whether or not the sensors can protect the barrier
	public boolean feasible() {
		return sensorRange * 2 > width / numSensors;
	}
}
