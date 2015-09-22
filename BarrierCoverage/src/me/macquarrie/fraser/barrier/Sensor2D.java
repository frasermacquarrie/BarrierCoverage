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
public class Sensor2D extends Sensor {

	private PointF location;
	private PointF initLocation;

	public Sensor2D(PointF l, int r) {
		location = new PointF(l);
		initLocation = new PointF(l);
		range = r;
	}

	public Sensor2D(int x, int y, int r) {
		this(new PointF(x, y), r);
	}

	public Sensor2D(Sensor2D s) {
		location = new PointF(s.location);
		initLocation = new PointF(s.initLocation);
		range = s.range;
	}

	public void moveTo(PointF l) {
		location = new PointF(l);
	}

	// From a list of nodes, some of which are available and some not, as
	// determined
	// by points, determine which of the available nodes is closest to this
	// sensor
	public int closestNode(PointF[] nodes, boolean[] points, int numNodes) {
		double distance = 999999;
		int cur = -1;

		for (int i = 0; i < numNodes; i++) {

			if ((Math.sqrt(Math.pow(nodes[i].x - location.x, 2) + Math.pow(nodes[i].y - location.y, 2))) < distance
					&& !points[i]) {
				distance = (Math.sqrt(Math.pow(nodes[i].x - location.x, 2) + Math.pow(nodes[i].y - location.y, 2)));
				cur = i;
			}
		}
		return cur;
	}

	public void unmove() {
		location = new PointF(initLocation);
	}

	public boolean moved() {
		return ((location.x != initLocation.x) || (initLocation.y != location.y));
	}

	public PointF getLocation() {
		return new PointF(location);
	}

	public double distanceFrom(PointF p) {
		return Math.sqrt(Math.pow(p.x - initLocation.x, 2) + Math.pow(p.y - initLocation.y, 2));
	}

	public double distanceMoved() {
		return (Math.sqrt(Math.pow(location.x - initLocation.x, 2) + Math.pow(location.y - initLocation.y, 2)));
	}

	// Draw this sensor
	public void draw(Graphics g) {
		if (location != initLocation) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawOval((int) initLocation.x - 2, (int) initLocation.y - 2, 4, 4);
			g.fillOval((int) initLocation.x - 2, (int) initLocation.y - 2, 4, 4);
			g.drawLine((int) location.x, (int) location.y, (int) initLocation.x, (int) initLocation.y);
		}

		g.setColor(Color.BLACK);
		g.drawOval((int) location.x - 2, (int) location.y - 2, 4, 4);
		g.fillOval((int) location.x - 2, (int) location.y - 2, 4, 4);
		g.setColor(Color.CYAN);
		g.drawOval((int) location.x - range, (int) location.y - range, range * 2, range * 2);

	}

	// Given an array of sensors, determine the total amount the have moved
	public static double totalDistance(Sensor2D[] sens, int numSens) {
		double distance = 0;

		for (int i = 0; i < numSens; i++) {
			distance += sens[i].distanceMoved();
		}

		return distance;
	}

	// Given an array of sensors, determine the most any one of them has moved
	public static double maxDistance(Sensor2D[] temp, int numSens) {
		double maxDistance = 0;

		for (int i = 0; i < numSens; i++) {
			if (temp[i].distanceMoved() > maxDistance) {
				maxDistance = temp[i].distanceMoved();
			}
		}

		return maxDistance;
	}

	public void log() {

		MainForm.writeFile("Sensor");
		MainForm.writeFile(
				"location:     (" + (String.valueOf(location.x)) + ", " + (String.valueOf(location.y)) + ")");
		MainForm.writeFile("range:        " + (String.valueOf(range)));
		if (moved()) {
			MainForm.writeFile("origin:       (" + (String.valueOf(initLocation.x)) + ", "
					+ (String.valueOf(initLocation.y)) + ")");
			MainForm.writeFile("displacement: " + (String.valueOf(distanceMoved())));
		}

		MainForm.writeFile("");

	}

}
