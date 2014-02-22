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
	    setSize( 1024, 768 );
	    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
	    setLayout( new FlowLayout() );       // set the layout manager
	    label = new JLabel("Hello Swing!");  // construct a JLabel
	    add( label );                        // add the label to the JFrame
	  }
	  public static void main(String[] argv)
	  {
		  MainWindow window= new MainWindow("TEST");
		  window.setVisible(true);
	  }
}
