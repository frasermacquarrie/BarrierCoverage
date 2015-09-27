/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package me.macquarrie.fraser.barrier;

import java.awt.*;
import java.awt.geom.Point2D;
//import java.util.*;

/**
 *
 * @author Fraser
 */
public class Simulation2D extends Simulation {

	protected Sensor2D[] sensors;

	public Simulation2D(int radius, int numSens, int sensRange, Image g, MainForm p) {

		parent = p;
		width = (radius * 2);
		numSensors = numSens;
		sensorRange = sensRange;
		sensors = new Sensor2D[numSensors];
		graph = g;
	}

	// Find the min-max for a 2D barrier
	public void minmax() {
		optDistance = width * numSensors;
		optMax = width * numSensors;

		Sensor2D[] sens = null;
		Sensor2D[] temp = null;

		// Generate a set of nodes from each sensor and determine which is the
		// most optimized
		for (int i = 0; i < numSensors; i++) {

			// Generate the set of nodes from sensor[i] and move all sensors to
			// a node
			temp = initializeSensors(sensors, i);

			// If the set of nodes was distorted, initializeSensors will return
			// null
			// to signify that we should ignore this set and skip to the next
			if (temp != null) {

				// Sort the set of nodes to find the min-max for this set of
				// nodes
				temp = minmaxSort(temp);

				double maxDistance = Sensor2D.maxDistance(temp, numSensors);
				double totalDistance = Sensor2D.totalDistance(temp, numSensors);

				// Determine if this is the best min-max obtained so far, and
				// if it is, save it
				if (maxDistance < optMax) {
					optMax = maxDistance;
					optDistance = totalDistance;
					sens = temp;
				}

			}

		}
		if (sens != null) {
			sensors = sens;
		}

		// Display the calculated information
		parent.setLabels(2 * optDistance / width, 2 * optMax / width, feasible());
	}

	// Find the min-sum for a 2D barrier
	public void min() {
		optDistance = width * numSensors;
		optMax = width * numSensors;

		Sensor2D[] sens = null;
		Sensor2D[] temp = null;

		// Generate a set of nodes from each sensor and determine which is the
		// most optimized
		for (int i = 0; i < numSensors; i++) {

			// Generate the set of nodes from sensor[i] and move all sensors to
			// a node
			temp = initializeSensors(sensors, i);

			// If the set of nodes was distorted, initializeSensors will return
			// null
			// to signify that we should ignore this set and skip to the next
			if (temp != null) {

				// Sort the set of nodes to find the min-max for this set of
				// nodes
				temp = minSort(temp);

				double maxDistance = Sensor2D.maxDistance(temp, numSensors);
				double totalDistance = Sensor2D.totalDistance(temp, numSensors);

				// Determine if this is the best min-sum obtained so far, and
				// if it is, save it
				if (totalDistance < optDistance) {
					optMax = maxDistance;
					optDistance = totalDistance;
					sens = temp;
				}

			}

		}
		if (sens != null) {
			sensors = sens;
		}

		// Display the calculated information
		parent.setLabels(2 * optDistance / width, 2 * optMax / width, feasible());
	}

	// Generate a set of sensors starting from a given node: s
	public Sensor2D[] initializeSensors(Sensor2D[] sens, int s) {

		// copy the sensor array so that we do not destroy the original
		Sensor2D[] temp = new Sensor2D[numSensors];
		for (int i = 0; i < numSensors; i++) {
			temp[i] = new Sensor2D(sens[i]);
		}

		// Create the set of nodes
		Point2D[] nodes = new Point2D[numSensors];

		// Move the appropriate sensor to the barrier
		temp[s].moveTo(boundary.moveToBorder(temp[s].getLocation()));

		// Get the new location of the sensor on the barrier
		nodes[0] = new Point2D.Double(temp[s].getLocation().getX(), temp[s].getLocation().getY());

		// Generate the set of nodes by sending the first node to a barrier
		// function
		nodes = boundary.setNodes(nodes, numSensors);

		// If the set of nodes is invalid, throw an 'error'
		if (outOfBounds(nodes, numSensors)) {
			return null;
		}

		// If the set of nodes is valid, move the sensors to them and return
		// them
		return sensorsToNodes(temp, nodes, s);

	}

	// Determine if a set of nodes is invalid
	// By default, all sets of nodes are valid
	public boolean outOfBounds(Point2D[] pts, int num) {
		return false;
	}

	// Sort a set of sensors that are already on nodes using an algorithm to
	// minimuze the min-max
	public Sensor2D[] minmaxSort(Sensor2D[] temp) {

		boolean sort = true;

		// Iterate until the set is optimized
		while (sort) {
			sort = false;

			// Iterate pairwise
			for (int i = 0; i < numSensors; i++) {
				for (int j = i; j < numSensors; j++) {

					// If the maximum moved of two sensors can be lowed by
					// switching
					// their nodes, then do so
					if (Math.max(temp[i].distanceMoved(), temp[j].distanceMoved()) > Math.max(
							temp[i].distanceFrom(temp[j].getLocation()), temp[j].distanceFrom(temp[i].getLocation()))) {
						Point2D p = new Point2D.Double(temp[i].getLocation().getX(), temp[i].getLocation().getY());
						temp[i].moveTo(temp[j].getLocation());
						temp[j].moveTo(p);
						sort = true;
					}
				}
			}
		}
		// return the optimized array of sensors
		return temp;
	}

	// Sort a set of sensors that are already on nodes using an algorithm to
	// minimize the min-sum
	private Sensor2D[] minSort(Sensor2D[] temp) {

		boolean sort = true;

		// Iterate until the set is optimized
		while (sort) {
			sort = false;

			// Iterate pairwise
			for (int i = 0; i < numSensors; i++) {
				for (int j = i; j < numSensors; j++) {

					// If the total moved between the two sensors can be lowered
					// by switching their nodes, then do so
					if (temp[i].distanceMoved() + temp[j].distanceMoved() > temp[i].distanceFrom(temp[j].getLocation())
							+ temp[j].distanceFrom(temp[i].getLocation())) {
						Point2D p = new Point2D.Double(temp[i].getLocation().getX(), temp[i].getLocation().getY());
						temp[i].moveTo(temp[j].getLocation());
						temp[j].moveTo(p);
						sort = true;
					}
				}
			}
		}
		// return the optimized array of sensors
		return temp;
	}

	// Determine whether the sensors can protect a circular barrier of radius
	// width/2
	public boolean feasible() {
		return sensorRange * 2 > width * Math.PI / numSensors;
	}

	// Move a set of sensors to a valid set of nodes
	public Sensor2D[] sensorsToNodes(Sensor2D[] temp, Point2D[] nodes, int s) {

		int[] closestNode = new int[numSensors];
		double[] nodeDistance = new double[numSensors];

		boolean[] points = new boolean[numSensors];
		boolean[] sensorMoved = new boolean[numSensors];

		for (int i = 0; i < numSensors; i++) {
			sensorMoved[i] = false;
			points[i] = false;
		}

		// A sensor is already on the first node
		points[0] = true;
		sensorMoved[s] = true;

		// The outer loop determines which sensor to move and does so
		// Each iteration will move one sensor to a node
		for (int i = 1; i < numSensors; i++) {

			// The inner loop generates an array which keeps track of the
			// distance
			// between each sensor and its closest empty node, it also generates
			// an
			// array which keeps track of the node
			for (int j = 0; j < numSensors; j++) {
				closestNode[j] = temp[j].closestNode(nodes, points, numSensors);
				nodeDistance[j] = temp[j].distanceFrom(nodes[closestNode[j]]);
			}

			double dist = width;
			int select = 0;

			// Find the sensor that has not been moved that is closest to its
			// closest node, and move it to that node
			for (int j = 1; j < numSensors; j++) {
				if (nodeDistance[j] < dist && !sensorMoved[j]) {
					select = j;
					dist = nodeDistance[j];
				}
			}
			temp[select].moveTo(nodes[closestNode[select]]);
			sensorMoved[select] = true;
			points[closestNode[select]] = true;
		}

		// return the set of sensors, which are now on the barrier nodes
		return temp;
	}

	// reset each sensor back to its original position
	public void reset() {
		for (int i = 0; i < numSensors; i++) {
			sensors[i].unmove();
		}
	}

	// Draw a graph of the simulation
	public void draw(Graphics g) {
		Graphics grap = graph.getGraphics();
		grap.setColor(Color.WHITE);
		grap.fillRect(0, 0, (int) width, (int) width);
		grap.translate((int) width / 2, (int) width / 2);
		boundary.draw(grap);
		for (int i = 0; i < numSensors; i++) {
			sensors[i].draw(grap);
		}
		g.drawImage(graph, 0, 0, 400, 400, null);

	}
}
