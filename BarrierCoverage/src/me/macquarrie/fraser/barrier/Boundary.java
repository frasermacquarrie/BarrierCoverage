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
public abstract class Boundary {

	public abstract void draw(Graphics g);

	public abstract PointF moveToBorder(PointF p);

	public abstract PointF[] setNodes(PointF[] n, int num);
}
