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
public class Sensor1D extends Sensor {

	private double location;
	private double initLocation;
	public static double height;

	public Sensor1D(double l, int r) {
		location = l;
		initLocation = l;
		range = r;
	}

	public void unmove() {
		location = initLocation;
	}

	public double getLocation() {
		return location;
	}

	public void moveTo(double l) {
		location = l;
	}

	public void move(double d) {
		location -= d;
		if (location < 0) {
			location = 0;
		} else if (location > height * 2) {
			location = height * 2;
		}
	}

	public double distanceMoved() {
		return Math.abs(location - initLocation);
	}

	public double signedDistanceMoved() {
		return location - initLocation;
	}

	public double distanceFrom(double l) {
		return Math.abs(initLocation - l);
	}

	public double currentDistanceFrom(double l) {
		return Math.abs(location - l);
	}

	public double signedDistanceFrom(double l) {
		return location - l;
	}

	public double signedInitDistanceFrom(double l) {
		return initLocation - l;
	}

	public static double totalDistance(Sensor1D[] sens, int numSens) {
		double distance = 0;

		for (int i = 0; i < numSens; i++) {
			distance += sens[i].distanceMoved();
		}

		return distance;
	}

	public void draw(Graphics g) {
		if (location != initLocation) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawOval((int) initLocation - 2, (int) height - 6, 4, 4);
			g.fillOval((int) initLocation - 2, (int) height - 6, 4, 4);
			g.drawLine((int) location, (int) height - 4, (int) initLocation, (int) height - 4);
		}

		g.setColor(Color.BLACK);
		g.drawOval((int) location - 2, (int) height - 2, 4, 4);
		g.fillOval((int) location - 2, (int) height - 2, 4, 4);
		g.setColor(Color.CYAN);
		g.drawOval((int) location - range, (int) height - range, range * 2, range * 2);

	}
}
