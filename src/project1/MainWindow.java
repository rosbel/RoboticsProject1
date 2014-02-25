/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project1;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class MainWindow extends javax.swing.JFrame {
	JPanel panel;
	JLabel label;
	PaintBot paintbot;
	Boolean painting = false;
	Vector<Location> paintinglocations = new Vector<Location>();
	  public void drawBot(double x1, double x2, double y2c, double x3, double y3c, double x4, double y4c) {
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  int height = screenSize.height;
		  double y2 = height - y2c;
		  double y3 = height - y3c;
		  double y4 = height - y4c;
	       Graphics g = paintCanvasPanel.getGraphics();
	       Graphics2D g2 = (Graphics2D) g;
	       //Draw first joint
	       //g2.setStroke(new BasicStroke(10));
	       g2.setColor(Color.WHITE);
	       g2.drawOval((int)(x1 - 5), height - 305, 10, 10);
	       //Draw first link
	       g2.setStroke(new BasicStroke(5));
	       g2.drawLine((int)x1,height - 300,(int)x2,(int)y2);
	       //Draw second joint
	       g2.setStroke(new BasicStroke(1));

	       g2.drawOval((int)(x2 - 5), (int)y2 - 5, 10, 10);
	       g2.setColor(Color.WHITE);
	       g2.setStroke(new BasicStroke(3));
	       //Draw second link
	       g2.drawLine((int)x2,(int)y2,(int)x3,(int)y3);
	       //Draw third joint
	       g2.setStroke(new BasicStroke(1));
	       g2.drawOval((int)(x3 - 5), (int)(y3 - 5), 10, 10);
	       g2.setColor(Color.WHITE);
	       //Draw third link

	       g2.drawLine((int)x3,(int)y3,(int)x4,(int)y4);
	       //Draw brush
	       g2.drawOval((int)(x4 - 5), (int)(y4 - 5), 10, 10);
	       g2.setColor(Color.WHITE);
	       g2.fillOval((int)(x4 - 5), (int)(y4 - 5), 10, 10);
	       
	   }

	  public void drawSlider(){
	       Graphics g = paintCanvasPanel.getGraphics();
	       Graphics2D g2 = (Graphics2D) g;
	       g2.setStroke(new BasicStroke(3));
	       g2.setColor(Color.red);
	       g2.drawLine(150, Toolkit.getDefaultToolkit().getScreenSize().height - 300, 450, Toolkit.getDefaultToolkit().getScreenSize().height - 300);
	  }
	  
	  public void drawLineLengths(int x, int x1, int y1, int x2, int y2, int x3, int y3){
	       Graphics g = paintCanvasPanel.getGraphics();
		  double link1length = Math.sqrt(Math.abs((x1 - x)^2 + (y1 - (Toolkit.getDefaultToolkit().getScreenSize().height - 300))^2));
	       String total1 = String.valueOf(link1length);
	       g.drawString(total1, 100, 100);
	       double link2length = Math.sqrt(((x2 - x1)^2) + ((y2 - y1)^2));
		   String total2 = String.valueOf(link2length);
		   g.drawString(total2, 100, 150);
	       double link3length = Math.sqrt(((x3 - x2)^2) + ((y3 - y2)^2));
		   String total3 = String.valueOf(link3length);
		   g.drawString(total3, 100, 200);
	  }
	  public void Rotate(double x, double y, double basex, double basey, double theta, int jointnum){
		  double newX = basex + (x-basex)*Math.cos(theta) - (y-basey)*Math.sin(theta);
		  double newY = basey + (x-basex)*Math.sin(theta) + (y-basey)*Math.cos(theta);
		  switch(jointnum){
		  case 1:
			  	paintbot.psi += theta;
		  		paintbot.joint2.x = (int)newX;
		  		paintbot.joint2.y = (int)newY;
		  		double tempx = Math.cos(paintbot.psi+paintbot.theta) * 100 + paintbot.joint2.x;
		  		paintbot.joint3.x = (int) tempx;
		  		double tempy = Math.sin(paintbot.psi+paintbot.theta) * 100 + paintbot.joint2.y;
		  		paintbot.joint3.y = (int) tempy;
		  		double brushx = Math.cos(paintbot.theta + paintbot.brushangle) * 75 + paintbot.joint3.x;
		  		paintbot.brush.x = (int) brushx;
		  		double brushy = Math.sin(paintbot.theta + paintbot.brushangle) * 75 + paintbot.joint3.y;
		  		paintbot.brush.y = (int) brushy;
		  		break;
		  case 2:
			  	paintbot.theta += theta;
			    paintbot.joint3.x = (int)newX;
			  	paintbot.joint3.y = (int)newY;
			  	double brushx2 = Math.cos(paintbot.theta + paintbot.brushangle) * 75 + paintbot.joint3.x;
			  	paintbot.brush.x = (int)brushx2;
			  	double brushy2 = Math.sin(paintbot.theta + paintbot.brushangle) * 75 + paintbot.joint3.y;
			  	paintbot.brush.y = (int)brushy2;
			  	break;
		  case 3:
			  	paintbot.brushangle += theta;
			  	paintbot.brush.x = (int)newX;
			  	paintbot.brush.y = (int)newY;
			  	break;
		  }
	  }
	  public void InitBot(){
		  PaintBot newpaintbot = new PaintBot(300,300,300,450,300,550,300,625);
		  paintbot = newpaintbot;
		  
	  }
	  
	  public void drawPaint(){
		  Graphics g = paintCanvasPanel.getGraphics();
		     if(painting == true){
		    	 	paintinglocations.add(new Location(paintbot.brush.x - 5,Toolkit.getDefaultToolkit().getScreenSize().height - paintbot.brush.y - 5));
		     }
		     for(int i=0; i<paintinglocations.size();i++){
			 	g.setColor(Color.GREEN);
		 		g.drawOval((int)paintinglocations.elementAt(i).x, (int)paintinglocations.elementAt(i).y , 10, 10);
		 		g.fillOval((int)paintinglocations.elementAt(i).x, (int)paintinglocations.elementAt(i).y  , 10, 10);
		     }
	  }
	  
	  public void drawAngles(){
		  Graphics g = this.getGraphics();
	       String total1 = String.valueOf(paintbot.psi);
	       g.drawString(total1, 100, 100);
	       String total2 = String.valueOf(paintbot.theta);
	       g.drawString(total2, 100, 120);
	       String total3 = String.valueOf(paintbot.brushangle);
	       g.drawString(total3, 100, 140);
	  }
	  @Override
	  public void paint(Graphics g) {
		 super.paintComponents(g);
		 double tempjointpos = new Double((int)paintbot.joint1.x);
		 paintbot.joint1.x = robotSlider.getValue()*3 + 150;
		 paintbot.joint2.x -= tempjointpos - paintbot.joint1.x ;
		 paintbot.joint3.x -= tempjointpos - paintbot.joint1.x;
		 paintbot.brush.x -= tempjointpos - paintbot.joint1.x;
	     drawBot(paintbot.joint1.x,paintbot.joint2.x,paintbot.joint2.y,paintbot.joint3.x,paintbot.joint3.y,paintbot.brush.x,paintbot.brush.y);
	     drawSlider();
	     drawPaint();
	     //drawAngles();
	     joint1YLabel.setText(String.valueOf(paintbot.joint1.y));
	     joint1XLabel.setText(String.valueOf(paintbot.joint1.x));
	     joint2YLabel.setText(String.valueOf(paintbot.joint2.y));
	     joint2XLabel.setText(String.valueOf(paintbot.joint2.x));
	     joint3YLabel.setText(String.valueOf(paintbot.joint3.y));
	     joint3XLabel.setText(String.valueOf(paintbot.joint3.x));
	  }
	  
	  /*public static void main(String[] argv)
	  {

	  }*/
/**
 *
 * @author rosbelsanroman
 */

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
		InitBot();
        initComponents();
        this.setTitle("Paint-Bot v1.0");
    }

    class SliderListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                repaint();
            }   				
		}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">  

    private void initComponents() {

        controlPanel = new javax.swing.JPanel();
        paintButton = new javax.swing.JToggleButton();
        jointButtonsPanel = new javax.swing.JPanel();
        j1Label = new javax.swing.JLabel();
        j3RightButton = new javax.swing.JButton();
        j2RightButton = new javax.swing.JButton();
        j2LeftButton = new javax.swing.JButton();
        j3LeftButton = new javax.swing.JButton();
        j1RightButton = new javax.swing.JButton();
        j1LeftButton = new javax.swing.JButton();
        j2Label = new javax.swing.JLabel();
        j3Label = new javax.swing.JLabel();
        robotSlider = new javax.swing.JSlider();
        robotSlider.addChangeListener(new SliderListener());
        coordinatesPanel = new javax.swing.JPanel();
        jointTitleLabel = new javax.swing.JLabel();
        xTitleLabel = new javax.swing.JLabel();
        yTitleLabel = new javax.swing.JLabel();
        joint1Label = new javax.swing.JLabel();
        joint1XLabel = new javax.swing.JLabel();
        joint1YLabel = new javax.swing.JLabel();
        joint2Label = new javax.swing.JLabel();
        joint2XLabel = new javax.swing.JLabel();
        joint2YLabel = new javax.swing.JLabel();
        joint3Label = new javax.swing.JLabel();
        joint3XLabel = new javax.swing.JLabel();
        joint3YLabel = new javax.swing.JLabel();
        paintCanvasPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        controlPanel.setPreferredSize(new java.awt.Dimension(362, 100));
        controlPanel.setSize(new java.awt.Dimension(200, 100));

        paintButton.setFont(paintButton.getFont().deriveFont(paintButton.getFont().getStyle() | java.awt.Font.BOLD, paintButton.getFont().getSize()+5));
        paintButton.setText("Paint");
        paintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paintButtonActionPerformed(evt);
            }
        });

        j1Label.setText("Joint 1");

        j3RightButton.setText(">");
        j3RightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j3RightButtonActionPerformed(evt);
            }
        });

        j2RightButton.setText(">");
        j2RightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j2RightButtonActionPerformed(evt);
            }
        });

        j2LeftButton.setText("<");
        j2LeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j2LeftButtonActionPerformed(evt);
            }
        });

        j3LeftButton.setText("<");
        j3LeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j3LeftButtonActionPerformed(evt);
            }
        });

        j1RightButton.setText(">");
        j1RightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1RightButtonActionPerformed(evt);
            }
        });

        j1LeftButton.setText("<");
        j1LeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1LeftButtonActionPerformed(evt);
            }
        });

        j2Label.setText("Joint 2");

        j3Label.setText("Joint 3");

        javax.swing.GroupLayout jointButtonsPanelLayout = new javax.swing.GroupLayout(jointButtonsPanel);
        jointButtonsPanel.setLayout(jointButtonsPanelLayout);
        jointButtonsPanelLayout.setHorizontalGroup(
            jointButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jointButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jointButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(j3Label)
                    .addComponent(j2Label)
                    .addComponent(j1Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jointButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jointButtonsPanelLayout.createSequentialGroup()
                        .addComponent(j1LeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(j1RightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jointButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jointButtonsPanelLayout.createSequentialGroup()
                            .addComponent(j3LeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(j3RightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jointButtonsPanelLayout.createSequentialGroup()
                            .addComponent(j2LeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(j2RightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jointButtonsPanelLayout.setVerticalGroup(
            jointButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jointButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jointButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(j3RightButton)
                    .addComponent(j3LeftButton)
                    .addComponent(j3Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jointButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(j2RightButton)
                    .addComponent(j2LeftButton)
                    .addComponent(j2Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jointButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(j1LeftButton)
                    .addComponent(j1RightButton)
                    .addComponent(j1Label))
                .addContainerGap())
        );

        coordinatesPanel.setLayout(new java.awt.GridLayout(4, 3));

        jointTitleLabel.setBackground(new java.awt.Color(200, 200, 200));
        jointTitleLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jointTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jointTitleLabel.setText("Joint");
        coordinatesPanel.add(jointTitleLabel);

        xTitleLabel.setBackground(new java.awt.Color(200, 200, 200));
        xTitleLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        xTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xTitleLabel.setText("X");
        coordinatesPanel.add(xTitleLabel);

        yTitleLabel.setBackground(new java.awt.Color(200, 200, 200));
        yTitleLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        yTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yTitleLabel.setText("Y");
        coordinatesPanel.add(yTitleLabel);

        joint1Label.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        joint1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joint1Label.setText("1");
        coordinatesPanel.add(joint1Label);

        joint1XLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joint1XLabel.setText("0");
        coordinatesPanel.add(joint1XLabel);

        joint1YLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joint1YLabel.setText("0");
        coordinatesPanel.add(joint1YLabel);

        joint2Label.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        joint2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joint2Label.setText("2");
        coordinatesPanel.add(joint2Label);

        joint2XLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joint2XLabel.setText("0");
        coordinatesPanel.add(joint2XLabel);

        joint2YLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joint2YLabel.setText("0");
        coordinatesPanel.add(joint2YLabel);

        joint3Label.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        joint3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joint3Label.setText("3");
        coordinatesPanel.add(joint3Label);

        joint3XLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joint3XLabel.setText("0");
        coordinatesPanel.add(joint3XLabel);

        joint3YLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joint3YLabel.setText("0");
        coordinatesPanel.add(joint3YLabel);

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(robotSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paintButton)
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(coordinatesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jointButtonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(paintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(coordinatesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jointButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(robotSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        paintCanvasPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout paintCanvasPanelLayout = new javax.swing.GroupLayout(paintCanvasPanel);
        paintCanvasPanel.setLayout(paintCanvasPanelLayout);
        paintCanvasPanelLayout.setHorizontalGroup(
            paintCanvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );
        paintCanvasPanelLayout.setVerticalGroup(
            paintCanvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintCanvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paintCanvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                        .addGap(35, 35, 35)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void paintButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here: 
    	if(painting == false)
    		painting = true;
    	else painting = false;
    }                                           

    private void j3RightButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
	     Rotate(paintbot.brush.x,paintbot.brush.y,paintbot.joint3.x,paintbot.joint3.y,-0.1,3);
	     repaint();

    }                                             

    private void j2RightButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
	     Rotate(paintbot.joint3.x,paintbot.joint3.y,paintbot.joint2.x,paintbot.joint2.y,-0.1,2);
	     repaint();

    }                                             

    private void j3LeftButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
	     Rotate(paintbot.brush.x,paintbot.brush.y,paintbot.joint3.x,paintbot.joint3.y,0.1,3);
	     repaint();
    }                                            

    private void j2LeftButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
	     Rotate(paintbot.joint3.x,paintbot.joint3.y,paintbot.joint2.x,paintbot.joint2.y,0.1,2);
	     repaint();
    }                                            

    private void j1LeftButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
	     Rotate(paintbot.joint2.x,paintbot.joint2.y,paintbot.joint1.x,paintbot.joint1.y,0.1,1);
	     repaint();
    }                                            
 
    private void j1RightButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
	     Rotate(paintbot.joint2.x,paintbot.joint2.y,paintbot.joint1.x,paintbot.joint1.y,-0.1,1);
	     repaint();

    }                                             

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel coordinatesPanel;
    private javax.swing.JLabel j1Label;
    private javax.swing.JButton j1LeftButton;
    private javax.swing.JButton j1RightButton;
    private javax.swing.JLabel j2Label;
    private javax.swing.JButton j2LeftButton;
    private javax.swing.JButton j2RightButton;
    private javax.swing.JLabel j3Label;
    private javax.swing.JButton j3LeftButton;
    private javax.swing.JButton j3RightButton;
    private javax.swing.JLabel joint1Label;
    private javax.swing.JLabel joint1XLabel;
    private javax.swing.JLabel joint1YLabel;
    private javax.swing.JLabel joint2Label;
    private javax.swing.JLabel joint2XLabel;
    private javax.swing.JLabel joint2YLabel;
    private javax.swing.JLabel joint3Label;
    private javax.swing.JLabel joint3XLabel;
    private javax.swing.JLabel joint3YLabel;
    private javax.swing.JPanel jointButtonsPanel;
    private javax.swing.JLabel jointTitleLabel;
    private javax.swing.JToggleButton paintButton;
    private javax.swing.JPanel paintCanvasPanel;
    private javax.swing.JSlider robotSlider;
    private javax.swing.JLabel xTitleLabel;
    private javax.swing.JLabel yTitleLabel;
    // End of variables declaration                   
}
