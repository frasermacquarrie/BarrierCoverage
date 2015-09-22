/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package me.macquarrie.fraser.barrier;

import java.awt.*;
//import java.util.*;

/**
 * 
 * @author Fraser
 */
public abstract class Simulation {
	protected int numSensors;
	protected int sensorRange;
	protected double width;
	protected double optDistance;
	protected double optMax;
	protected Boundary boundary;
	protected Image graph;
	protected MainForm parent;

	public abstract void draw(Graphics g);

	public abstract void min();

	public abstract void minmax();

	public abstract void reset();

	public void logSim() {
	}
}
