//imports JPanel - used to create the field that sits inside the window created by JFrame
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
 public class PongPanel extends JPanel implements ActionListener, KeyListener {
	 
	 //final variable so the color of the background 
	 final static Color BACKGROUND_COLOR = Color.black;
	 
	 //constructor for the panel that sits inside the pong window.
	 public PongPanel() {
		 
		 //variable to set the color of the background to black
		 setBackground(BACKGROUND_COLOR);
      }

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
 
}
