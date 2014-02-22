package project1;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	JPanel panel;
	JLabel label;
	
	  // constructor
	  MainWindow( String title )
	  {
	    super( title );                      // invoke the JFrame constructor
	    setSize( 800, 600 );
	    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
	    setLayout( new FlowLayout() );       // set the layout manager
	    label = new JLabel("Hello Swing!");  // construct a JLabel
	    add( label );                        // add the label to the JFrame
	  }
	  
	  public void drawBot(int x1, int x2, int y2c, int x3, int y3c, int x4, int y4c) {
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  int height = screenSize.height;
		  int y2 = height - y2c;
		  int y3 = height - y3c;
		  int y4 = height - y4c;
	       Graphics g = this.getGraphics();
	       //Draw first joint
	       g.drawOval(x1 - 5, height - 305, 10, 10);
	       g.setColor(Color.BLACK);
	       //g.fillOval(x1 - 5, height - 205, 10, 10);
	       //Draw first link
	       g.drawLine(x1,height - 300,x2,y2);
	       //Draw second joint
	       g.drawOval(x2 - 5, y2 - 5, 10, 10);
	       g.setColor(Color.BLACK);
	       //g.fillOval(x2 - 5, y2 - 5, 10, 10);
	       //Draw second link
	       g.drawLine(x2,y2,x3,y3);
	       //Draw third joint
	       g.drawOval(x3 - 5, y3 - 5, 10, 10);
	       g.setColor(Color.BLACK);
	       //g.fillOval(x3 - 5, y3 - 5, 10, 10);
	       //Draw third link
	       g.drawLine(x3,y3,x4,y4);
	       //Draw fourth joint
	       g.drawOval(x4 - 5, y4 - 5, 10, 10);
	       g.setColor(Color.BLACK);
	       g.fillOval(x4 - 5, y4 - 5, 10, 10);
	       
	   }
	    
	  public void drawSlider(){
	       Graphics g = this.getGraphics();
	       g.setColor(Color.red);
	       g.drawLine(150, Toolkit.getDefaultToolkit().getScreenSize().height - 300, 450, Toolkit.getDefaultToolkit().getScreenSize().height - 300);
	  }
	  
	  public void drawLineLengths(int x, int x1, int y1, int x2, int y2, int x3, int y3){
	       Graphics g = this.getGraphics();
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
	  public void Rotate(int x, int y, int basex, int basey, double theta){
		  double newX = basex + (x-basex)*Math.cos(theta) - (y-basey)*Math.sin(theta);
		  double newY = basey + (x-basex)*Math.sin(theta) + (y-basey)*Math.cos(theta);
		  drawBot(300,(int)newX,(int)newY, 500,600,700,600);
	  }
	  
	  @Override
	  public void paint(Graphics g) {
	     drawBot(300, 300,400,500,600, 700, 600);
	     drawSlider();
	     drawLineLengths(300,300,400,500,600, 700, 600);
	     Rotate(300,400,300,300,3.14);
	  }
	  
	  public static void main(String[] argv)
	  {
		  MainWindow window= new MainWindow("TEST");
		  window.setVisible(true);
	  }
}
