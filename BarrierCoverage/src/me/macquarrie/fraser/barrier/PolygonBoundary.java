/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package me.macquarrie.fraser.barrier;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * 
 * @author Fraser
 */
public class PolygonBoundary extends Boundary {
	private Polygon polygon;

	public PolygonBoundary(Polygon p) {
		polygon = p;
	}

	// Draw the polygon
	public void draw(Graphics g) {
		if (polygon.npoints > 1) {
			g.setColor(Color.BLUE);
			for (int i = 1; i < polygon.npoints; i++) {
				g.drawLine(polygon.xpoints[i - 1], polygon.ypoints[i - 1], polygon.xpoints[i], polygon.ypoints[i]);
			}
			g.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[polygon.npoints - 1],
					polygon.ypoints[polygon.npoints - 1]);
		}
	}

	// Determine whether adding the point to the polygon will leave the polygon
	// simple or not
	public static boolean isSimpleWith(Polygon poly, Point2D p) {

		// Clearly any polygon with less than 4 points is simple, so adding the
		// point will leave the polygon simple
		if (poly.npoints < 3) {
			return true;
		}

		Point2D point0 = new Point2D.Double(poly.xpoints[0], poly.ypoints[0]);
		Point2D pointN = new Point2D.Double(poly.xpoints[poly.npoints - 1], poly.ypoints[poly.npoints - 1]);

		double s = slope(point0, pointN);

		// Place restrictions on the point to be added so that there will be no
		// errors due to precision loss later
		if (Math.abs(slope(point0, p) - s) < 1 || Math.abs(slope(pointN, p) - s) < 0.5) {
			return false;
		}

		else if (Math.abs(slope(point0, p)) > 1000 || Math.abs(slope(pointN, p)) > 2) {
			return false;
		}

		// Loop through the polygon and determine if any of its lines intersect
		// with the lines that will be formed by addin the point
		for (int i = 1; i < poly.npoints; i++) {

			Point2D p1 = new Point2D.Double(poly.xpoints[i - 1], poly.ypoints[i - 1]);
			Point2D p2 = new Point2D.Double(poly.xpoints[i], poly.ypoints[i]);

			if (intersects(p, point0, p1, p2) || intersects(p, pointN, p1, p2)) {
				return false;
			}
		}

		// If there are no intersections, the adding this point will leave the
		// polygon simple
		return true;
	}

	public static double slope(Point2D a, Point2D b) {
		if (a.getX() == b.getX()) {
			return 0.000001;
		}
		return (a.getY() - b.getY()) / (a.getX() - b.getX());
	}

	// Determine if two line segments intersect
	public static boolean intersects(Point2D a1, Point2D a2, Point2D b1, Point2D b2) {
		double A1 = a2.getY() - a1.getY();
		double B1 = a1.getX() - a2.getX();
		double C1 = A1 * a1.getX() + B1 * a1.getY();
		double A2 = b2.getY() - b1.getY();
		double B2 = b1.getX() - b2.getX();
		double C2 = A2 * b1.getX() + B2 * b1.getY();
		double det = A1 * B2 - A2 * B1;
		double x;
		double y;

		if (det == 0) {
			return false;
		} else {
			x = (B2 * C1 - B1 * C2) / det;
			y = (A1 * C2 - A2 * C1) / det;
		}

		// make sure the intersection is on the line segments when returning it
		return (Math.min(a1.getX(), a2.getX()) <= x && Math.max(a1.getX(), a2.getX()) >= x);

	}

	// Set the nodes by beginning at a spot on the polygon and working around
	// the perimeter of the polygon.

	public Point2D[] setNodes(Point2D[] nodes, int numNodes) {

		int i = closestLine(nodes[0]);
		int j = i + 1;
		double nodeDistance = getPerimeterLength() / numNodes;

		for (int k = 1; k < numNodes; k++) {

			double distToNode = nodeDistance;
			Point2D previous = new Point2D.Double(nodes[k - 1].getX(), nodes[k - 1].getY());

			while (true) {

				if (i == polygon.npoints)
					i = 0;
				if (j == polygon.npoints)
					j = 0;

				double dist = Math.sqrt(
						Math.pow(previous.getX() - polygon.xpoints[j], 2) + Math.pow(previous.getY() - polygon.ypoints[j], 2));

				// If the next node is on this line segment
				if (dist > distToNode) {
					Point2D p = new Point2D.Double(previous.getX(), previous.getY());
					p.setLocation(p.getX() - distToNode * (previous.getX() - polygon.xpoints[j]) / dist,
								  p.getY() - distToNode * (previous.getY() - polygon.ypoints[j]) / dist);
					nodes[k] = p;
					break;
				}

				// if the next node is not on this line segment
				else {
					previous.setLocation(polygon.xpoints[j], polygon.ypoints[j]);
					distToNode -= dist;
					i++;
					j++;
				}
			}
		}
		return nodes;
	}

	// Return the closest point on the barrier to the point provided
	public Point2D closestPoint(Point2D p) {

		double distance = 9999999;
		Point2D intersection = new Point2D.Double(0.0, 0.0);

		for (int i = 0; i < polygon.npoints; i++) {

			int j = i + 1;

			if (j == polygon.npoints)
				j = 0;
			Point2D in = new Point2D.Double(0, 0);
			double dist;

			double u = ((p.getX() - polygon.xpoints[i]) * (polygon.xpoints[j] - polygon.xpoints[i])
					+ (p.getY() - polygon.ypoints[i]) * (polygon.ypoints[j] - polygon.ypoints[i]))
					/ (Math.pow((polygon.xpoints[j] - polygon.xpoints[i]), 2)
							+ Math.pow((polygon.ypoints[j] - polygon.ypoints[i]), 2));

			in.setLocation(	polygon.xpoints[i] + u * (polygon.xpoints[j] - polygon.xpoints[i]),
							polygon.ypoints[i] + u * (polygon.ypoints[j] - polygon.ypoints[i]));

			if (Math.min(polygon.xpoints[i], polygon.xpoints[j]) <= in.getX()
					&& Math.max(polygon.xpoints[i], polygon.xpoints[j]) >= in.getX()) {

				dist = Math.sqrt(Math.pow(in.getX() - p.getX(), 2) + Math.pow(in.getY() - p.getY(), 2));

				if (dist < distance) {
					distance = dist;
					intersection = in;
				}
			}

			else {
				if (Math.sqrt(
						Math.pow(polygon.xpoints[i] - p.getX(), 2) + Math.pow(polygon.ypoints[i] - p.getY(), 2)) < distance) {
					distance = Math.sqrt(Math.pow(polygon.xpoints[i] - p.getX(), 2) + Math.pow(polygon.ypoints[i] - p.getY(), 2));
					intersection = new Point2D.Double(polygon.xpoints[i], polygon.ypoints[i]);
				}
				if (Math.sqrt(
						Math.pow(polygon.xpoints[j] - p.getX(), 2) + Math.pow(polygon.ypoints[j] - p.getY(), 2)) < distance) {
					distance = Math.sqrt(Math.pow(polygon.xpoints[j] - p.getX(), 2) + Math.pow(polygon.ypoints[j] - p.getY(), 2));
					intersection = new Point2D.Double(polygon.xpoints[j], polygon.ypoints[j]);
				}
			}
		}
		return intersection;
	}

	// Return the closest line of the barrier to the point provided
	public int closestLine(Point2D p) {
		double distance = 9999999;
		int line = -1;

		for (int i = 0; i < polygon.npoints; i++) {

			int j = i + 1;
			if (j == polygon.npoints)
				j = 0;

			Point2D in = new Point2D.Double(0.0, 0.0);
			double dist;

			double u = ((p.getX() - polygon.xpoints[j]) * (polygon.xpoints[i] - polygon.xpoints[j])
					+ (p.getY() - polygon.ypoints[j]) * (polygon.ypoints[i] - polygon.ypoints[j]))
					/ (Math.pow((polygon.xpoints[i] - polygon.xpoints[j]), 2)
							+ Math.pow((polygon.ypoints[i] - polygon.ypoints[j]), 2));

			in.setLocation(polygon.xpoints[j] + u * (polygon.xpoints[i] - polygon.xpoints[j]), polygon.ypoints[j] + u * (polygon.ypoints[i] - polygon.ypoints[j]));

			dist = Math.sqrt(Math.pow(in.getX() - p.getX(), 2) + Math.pow(in.getY() - p.getY(), 2));

			if (dist < distance) {
				distance = dist;
				line = i;
			}
		}
		return line;
	}

	// Calculate the perimeter of the polygon
	double getPerimeterLength() {
		double distance = 0;
		for (int i = 0; i < polygon.npoints; i++) {
			int j = i + 1;
			if (j == polygon.npoints)
				j = 0;
			distance += Math.sqrt(Math.pow(polygon.xpoints[i] - polygon.xpoints[j], 2)
					+ Math.pow(polygon.ypoints[i] - polygon.ypoints[j], 2));
		}
		return distance;
	}

	// Return the closest point on the barrier to the point provided
	public Point2D moveToBorder(Point2D p) {

		return closestPoint(p);
	}

	public void log() {

		MainForm.writeFile("Polygon Boundary\n");
		for (int i = 0; i < polygon.npoints; i++) {
			MainForm.writeFile("Point " + String.valueOf(i) + ": (" + (String.valueOf(polygon.xpoints[i])) + ", "
					+ (String.valueOf(polygon.ypoints[i])) + ")");
		}

		MainForm.writeFile("");

	}

}
