/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package me.macquarrie.fraser.barrier;

import java.awt.*;
//import java.lang.*;

/**
 *
 * @author Fraser
 */
public class CircleBoundary extends Boundary {

	private PointF center;
	private double radius;

	public CircleBoundary(PointF c, int r) {
		center = c;
		radius = r;
	}

	// Draw the barrier
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawOval((int) (center.x - radius), (int) (center.y - radius), (int) radius * 2, (int) radius * 2);
	}

	public double getWidth() {
		return (double) radius * 2;
	}

	public PointF getCenter() {
		return center;
	}

	// Return the closest point on the barrier to the point provided
	public PointF moveToBorder(PointF p) {
		double x = (p.x - center.x);
		double y = (p.y - center.y);
		double dist = Math.sqrt(x * x + y * y);

		return new PointF(((x * radius) / dist), ((y * radius) / dist));
	}

	// Set the nodes by beginning at a spot on the circle and rotating around
	// the circle
	public PointF[] setNodes(PointF[] nodes, int numNodes) {

		for (int i = 1; i < numNodes; i++) {
			double xcomp = Math.cos(Math.acos((nodes[0].x) / radius) + (2.0 * Math.PI * i) / numNodes);
			double ycomp = Math.sin(Math.asin((nodes[0].y) / radius) + (2.0 * Math.PI * i) / numNodes);

			nodes[i] = new PointF((xcomp) * (radius), ((ycomp) * (radius)));
		}
		return nodes;
	}

	public void log() {

		MainForm.writeFile("Circle Boundary");
		MainForm.writeFile("center: (" + (String.valueOf(center.x)) + ", " + (String.valueOf(center.y)) + ")");
		MainForm.writeFile("radius:    " + (String.valueOf(radius)));
		MainForm.writeFile("");

	}
}
