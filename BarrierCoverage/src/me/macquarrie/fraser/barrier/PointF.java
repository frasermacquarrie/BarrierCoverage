/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package me.macquarrie.fraser.barrier;

//import java.math.*;

/**
 * 
 * @author Fraser
 */
public class PointF {
	public double x;
	public double y;

	public PointF(double m, double n) {
		x = m;
		y = n;
	}

	public PointF(PointF p) {
		this(p.x, p.y);
	}

}
