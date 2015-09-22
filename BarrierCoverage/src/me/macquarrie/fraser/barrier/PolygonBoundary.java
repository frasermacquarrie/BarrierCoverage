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
	public static boolean isSimpleWith(Polygon poly, PointF p) {

		// Clearly any polygon with less than 4 points is simple, so adding the
		// point will leave the polygon simple
		if (poly.npoints < 3) {
			return true;
		}

		PointF point0 = new PointF(poly.xpoints[0], poly.ypoints[0]);
		PointF pointN = new PointF(poly.xpoints[poly.npoints - 1], poly.ypoints[poly.npoints - 1]);

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

			PointF p1 = new PointF(poly.xpoints[i - 1], poly.ypoints[i - 1]);
			PointF p2 = new PointF(poly.xpoints[i], poly.ypoints[i]);

			if (intersects(p, point0, p1, p2) || intersects(p, pointN, p1, p2)) {
				return false;
			}
		}

		// If there are no intersections, the adding this point will leave the
		// polygon simple
		return true;
	}

	public static double slope(PointF a, PointF b) {
		if (a.x == b.x) {
			return 0.000001;
		}
		return (a.y - b.y) / (a.x - b.x);
	}

	// Determine if two line segments intersect
	public static boolean intersects(PointF a1, PointF a2, PointF b1, PointF b2) {
		double A1 = a2.y - a1.y;
		double B1 = a1.x - a2.x;
		double C1 = A1 * a1.x + B1 * a1.y;
		double A2 = b2.y - b1.y;
		double B2 = b1.x - b2.x;
		double C2 = A2 * b1.x + B2 * b1.y;
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
		return (Math.min(a1.x, a2.x) <= x && Math.max(a1.x, a2.x) >= x);

	}

	// Set the nodes by beginning at a spot on the polygon and working around
	// the perimeter of the polygon.

	public PointF[] setNodes(PointF[] nodes, int numNodes) {

		int i = closestLine(nodes[0]);
		int j = i + 1;
		double nodeDistance = getPerimeterLength() / numNodes;

		for (int k = 1; k < numNodes; k++) {

			double distToNode = nodeDistance;
			PointF previous = new PointF(nodes[k - 1]);

			while (true) {

				if (i == polygon.npoints)
					i = 0;
				if (j == polygon.npoints)
					j = 0;

				double dist = Math.sqrt(
						Math.pow(previous.x - polygon.xpoints[j], 2) + Math.pow(previous.y - polygon.ypoints[j], 2));

				// If the next node is on this line segment
				if (dist > distToNode) {
					PointF p = new PointF(previous);
					p.x -= distToNode * (previous.x - polygon.xpoints[j]) / dist;
					p.y -= distToNode * (previous.y - polygon.ypoints[j]) / dist;
					nodes[k] = p;
					break;
				}

				// if the next node is not on this line segment
				else {
					previous.x = polygon.xpoints[j];
					previous.y = polygon.ypoints[j];
					distToNode -= dist;
					i++;
					j++;
				}
			}
		}
		return nodes;
	}

	// Return the closest point on the barrier to the point provided
	public PointF closestPoint(PointF p) {

		double distance = 9999999;
		PointF intersection = new PointF(0, 0);

		for (int i = 0; i < polygon.npoints; i++) {

			int j = i + 1;

			if (j == polygon.npoints)
				j = 0;
			PointF in = new PointF(0, 0);
			double dist;

			double u = ((p.x - polygon.xpoints[i]) * (polygon.xpoints[j] - polygon.xpoints[i])
					+ (p.y - polygon.ypoints[i]) * (polygon.ypoints[j] - polygon.ypoints[i]))
					/ (Math.pow((polygon.xpoints[j] - polygon.xpoints[i]), 2)
							+ Math.pow((polygon.ypoints[j] - polygon.ypoints[i]), 2));

			in.x = polygon.xpoints[i] + u * (polygon.xpoints[j] - polygon.xpoints[i]);
			in.y = polygon.ypoints[i] + u * (polygon.ypoints[j] - polygon.ypoints[i]);

			if (Math.min(polygon.xpoints[i], polygon.xpoints[j]) <= in.x
					&& Math.max(polygon.xpoints[i], polygon.xpoints[j]) >= in.x) {

				dist = Math.sqrt(Math.pow(in.x - p.x, 2) + Math.pow(in.y - p.y, 2));

				if (dist < distance) {
					distance = dist;
					intersection = in;
				}
			}

			else {
				if (Math.sqrt(
						Math.pow(polygon.xpoints[i] - p.x, 2) + Math.pow(polygon.ypoints[i] - p.y, 2)) < distance) {
					distance = Math.sqrt(Math.pow(polygon.xpoints[i] - p.x, 2) + Math.pow(polygon.ypoints[i] - p.y, 2));
					intersection = new PointF(polygon.xpoints[i], polygon.ypoints[i]);
				}
				if (Math.sqrt(
						Math.pow(polygon.xpoints[j] - p.x, 2) + Math.pow(polygon.ypoints[j] - p.y, 2)) < distance) {
					distance = Math.sqrt(Math.pow(polygon.xpoints[j] - p.x, 2) + Math.pow(polygon.ypoints[j] - p.y, 2));
					intersection = new PointF(polygon.xpoints[j], polygon.ypoints[j]);
				}
			}
		}
		return intersection;
	}

	// Return the closest line of the barrier to the point provided
	public int closestLine(PointF p) {
		double distance = 9999999;
		int line = -1;

		for (int i = 0; i < polygon.npoints; i++) {

			int j = i + 1;
			if (j == polygon.npoints)
				j = 0;

			PointF in = new PointF(0, 0);
			double dist;

			double u = ((p.x - polygon.xpoints[j]) * (polygon.xpoints[i] - polygon.xpoints[j])
					+ (p.y - polygon.ypoints[j]) * (polygon.ypoints[i] - polygon.ypoints[j]))
					/ (Math.pow((polygon.xpoints[i] - polygon.xpoints[j]), 2)
							+ Math.pow((polygon.ypoints[i] - polygon.ypoints[j]), 2));

			in.x = polygon.xpoints[j] + u * (polygon.xpoints[i] - polygon.xpoints[j]);
			in.y = polygon.ypoints[j] + u * (polygon.ypoints[i] - polygon.ypoints[j]);

			dist = Math.sqrt(Math.pow(in.x - p.x, 2) + Math.pow(in.y - p.y, 2));

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
	public PointF moveToBorder(PointF p) {

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
