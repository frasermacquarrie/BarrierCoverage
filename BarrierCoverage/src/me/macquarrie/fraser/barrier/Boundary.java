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
public abstract class Boundary {

	public abstract void draw(Graphics g);

	public abstract Point2D moveToBorder(Point2D p);

	public abstract Point2D[] setNodes(Point2D[] n, int num);
}
