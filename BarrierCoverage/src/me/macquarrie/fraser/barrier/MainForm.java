/*
 * MainForm.java
 *
 * Created on November 21, 2008, 12:31 AM
 */

package me.macquarrie.fraser.barrier;

import java.awt.*;
import java.io.*;

/**
 *
 * @author Fraser
 */

public class MainForm extends javax.swing.JFrame {
	public static final long serialVersionUID = 1L;
	
	Simulation sim;
	Simulation2DCircle sim2DC;
	Simulation2DPolygon sim2DP;
	Simulation1DEquidistant sim1DE;
	Simulation1DStaggered sim1DS;

	Image initImg;
	Image finalImg;
	Graphics initDiag, finalDiag;

	/** Creates new form MainForm */
	public MainForm() {
		initComponents();
		initialize();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	//@SuppressWarnings("unchecked")
	private void initialize() {

		ImagePanel initialState = new ImagePanel();
		ImagePanel finalState = new ImagePanel();

		initImg = createImage(400, 400);
		finalImg = createImage(400, 400);

		initialState.setImage(initImg);
		finalState.setImage(finalImg);

		initDiag = initImg.getGraphics();
		finalDiag = finalImg.getGraphics();

		scaleBox.setText("1");
		numSensorsBox.setText("20");
		sensorRangeBox.setText("0.0575");

		initialState.setBackground(new Color(255, 255, 255));
		finalState.setBackground(new Color(255, 255, 255));

		maxLabel.setText("");
		sumLabel.setText("");
		feasibleLabel.setText("");

		jPanel3.add(initialState);
		jPanel4.add(finalState);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		scaleBox = new javax.swing.JTextField();
		sensorRangeBox = new javax.swing.JTextField();
		numSensorsBox = new javax.swing.JTextField();
		jPanel6 = new javax.swing.JPanel();
		jLabel8 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		sumLabel = new javax.swing.JLabel();
		maxLabel = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		feasibleLabel = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		jButton4 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		jPanel7 = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("COMP 3203 - Assignment #2: Boundary Defense");
		setBackground(new java.awt.Color(255, 255, 255));
		setMinimumSize(new java.awt.Dimension(800, 600));
		setResizable(false);

		jPanel1.setBackground(new java.awt.Color(102, 204, 255));
		jPanel1.setMaximumSize(new java.awt.Dimension(800, 200));
		jPanel1.setMinimumSize(new java.awt.Dimension(200, 200));
		jPanel1.setPreferredSize(new java.awt.Dimension(800, 200));

		jPanel2.setBackground(new java.awt.Color(153, 255, 255));

		jLabel1.setText("Number of Sensors");

		jLabel2.setText("Sensor Range");

		jLabel3.setText("Scale");

		scaleBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
		scaleBox.setText("200");

		sensorRangeBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
		sensorRangeBox.setText("25");

		numSensorsBox.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
		numSensorsBox.setText("50");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel2).addComponent(jLabel3).addComponent(jLabel1))
						.addGap(28, 28, 28)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(scaleBox, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
								.addComponent(sensorRangeBox, javax.swing.GroupLayout.DEFAULT_SIZE, 114,
										Short.MAX_VALUE)
								.addComponent(numSensorsBox, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1).addComponent(numSensorsBox,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(sensorRangeBox,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel3).addComponent(scaleBox, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel6.setBackground(new java.awt.Color(153, 255, 255));

		jLabel8.setText("Maximum");

		jLabel7.setText("Total Sum");

		sumLabel.setText("1234");

		maxLabel.setText("1234");

		jLabel5.setText("Feasible");

		feasibleLabel.setText("1234");

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel7).addComponent(jLabel8).addComponent(jLabel5))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(feasibleLabel).addComponent(maxLabel).addComponent(sumLabel))
						.addContainerGap(166, Short.MAX_VALUE)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel7).addComponent(sumLabel))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel8).addComponent(maxLabel))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5).addComponent(feasibleLabel))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jButton4.setText("2D Circle");
		jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton4MouseClicked(evt);
			}
		});

		jButton3.setText("2D Polygon");
		jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton3MouseClicked(evt);
			}
		});

		jButton2.setText("1D Staggered");
		jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton2MouseClicked(evt);
			}
		});

		jButton1.setText("1D Equidistant");
		jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton1MouseClicked(evt);
			}
		});

		jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel4.setText("Simulations");

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup().addGap(24, 24, 24).addComponent(jLabel4)
						.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel5Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel5Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(jLabel4)
						.addContainerGap(106, Short.MAX_VALUE))
				.addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel5Layout.createSequentialGroup().addGap(34, 34, 34)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 18,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 18,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 18,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(5, 5, 5)
								.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 18,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));

		jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel6.setText("Optimization");

		jButton5.setText("Minimum Sum");
		jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton5MouseClicked(evt);
			}
		});

		jButton6.setText("Minimum Maximum");
		jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton6MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel7Layout.createSequentialGroup()
						.addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel7Layout.createSequentialGroup().addContainerGap()
										.addGroup(jPanel7Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(jPanel7Layout.createSequentialGroup().addGap(30, 30, 30).addComponent(jLabel6)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel7Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLabel6)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 18,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 18,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap()
								.addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(133, 133, 133)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(156, 156, 156)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(13, 13, 13)));

		jPanel3.setBackground(new java.awt.Color(255, 255, 255));
		jPanel3.setMinimumSize(new java.awt.Dimension(400, 400));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 402, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 465, Short.MAX_VALUE));

		jPanel4.setBackground(new java.awt.Color(255, 255, 255));
		jPanel4.setMinimumSize(new java.awt.Dimension(400, 400));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 422, Short.MAX_VALUE));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 465, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel4,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton3MouseClicked
		String scaleStr = scaleBox.getText();
		int scale = 400 * Integer.parseInt(scaleStr);

		String numStr = numSensorsBox.getText();
		int numSens = Integer.parseInt(numStr);

		String rStr = sensorRangeBox.getText();
		int sensR = (int) (400 * (Double.parseDouble(rStr)));

		sim2DP = new Simulation2DPolygon(scale, numSens, sensR, createImage(scale, scale), this);
		sim = sim2DP;
		sim2DP.draw(initDiag);
		sim2DP.logSim();

		this.repaint();

		// TODO add your handling code here:
	}// GEN-LAST:event_jButton3MouseClicked

	private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton5MouseClicked
		if (sim != null) {
			sim.reset();
			sim.min();
			sim.draw(finalDiag);
			sim.logSim();
			repaint();// TODO add your handling code here:
		}
	}// GEN-LAST:event_jButton5MouseClicked

	private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton6MouseClicked
		if (sim != null) {
			sim.reset();
			sim.minmax();
			sim.draw(finalDiag);
			sim.logSim();
			repaint();// TODO add your handling code here:
		} // TODO add your handling code here:
	}// GEN-LAST:event_jButton6MouseClicked

	private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseClicked
		String scaleStr = scaleBox.getText();
		int scale = 400 * Integer.parseInt(scaleStr);
		String numStr = numSensorsBox.getText();
		int numSens = Integer.parseInt(numStr);
		String rStr = sensorRangeBox.getText();
		int sensR = (int) (400 * (Double.parseDouble(rStr)));
		sim1DE = new Simulation1DEquidistant(scale, numSens, sensR, createImage(scale, scale), this);
		sim = sim1DE;
		sim1DE.draw(initDiag);

		this.repaint();
	}// GEN-LAST:event_jButton1MouseClicked

	private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton2MouseClicked
		String scaleStr = scaleBox.getText();
		int scale = 400 * Integer.parseInt(scaleStr);
		String numStr = numSensorsBox.getText();
		int numSens = Integer.parseInt(numStr);
		String rStr = sensorRangeBox.getText();
		int sensR = (int) (400 * (Double.parseDouble(rStr)));
		sim1DS = new Simulation1DStaggered(scale, numSens, sensR, createImage(scale, scale), this);
		sim = sim1DS;
		sim1DS.draw(initDiag);

		this.repaint();
	}// GEN-LAST:event_jButton2MouseClicked

	private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton4MouseClicked
		// TODO add your handling code here:
		String scaleStr = scaleBox.getText();
		int scale = 400 * Integer.parseInt(scaleStr);
		String numStr = numSensorsBox.getText();
		int numSens = Integer.parseInt(numStr);
		String rStr = sensorRangeBox.getText();
		int sensR = (int) (400 * (Double.parseDouble(rStr)));
		sim2DC = new Simulation2DCircle(scale / 2, numSens, sensR, createImage(scale, scale), this);
		sim = sim2DC;
		sim2DC.draw(initDiag);
		sim2DC.logSim();
		this.repaint();
	}// GEN-LAST:event_jButton4MouseClicked

	public void setLabels(double d, double m, boolean f) {
		sumLabel.setText(String.valueOf(d));
		maxLabel.setText(String.valueOf(m));
		feasibleLabel.setText(String.valueOf(f));
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainForm().setVisible(true);
			}
		});
	}

	public static void writeFile(String s) {
		try {
			// Create file
			FileWriter fstream = new FileWriter("log.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(s);
			out.newLine();
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel feasibleLabel;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JLabel maxLabel;
	private javax.swing.JTextField numSensorsBox;
	private javax.swing.JTextField scaleBox;
	private javax.swing.JTextField sensorRangeBox;
	private javax.swing.JLabel sumLabel;
	// End of variables declaration//GEN-END:variables

}
