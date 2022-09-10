//imports JPanel - used to create the field that sits inside the window created by JFrame
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
 public class PongPanel extends JPanel implements ActionListener, KeyListener {
	 
	 //final variable so the color of the background 
	 final static Color BACKGROUND_COLOR = Color.black;
	 final static int TIMER_DELAY = 5;
	 
	 //constructor for the panel that sits inside the pong window.
	 public PongPanel() {
		 
		 //variable to set the color of the background to black
		 setBackground(BACKGROUND_COLOR);
		 
		 //added a timer to the project, this provides a timed-loop to the game which allows for the game to react to inputs etc
	      Timer timer = new Timer(TIMER_DELAY, this);
	         timer.start();
      }
	 
	 private void update() {
         
	 }
	 
	 //method which paints the dotted line onto the middle of the screen
	 private void paintDottedLine(Graphics g) {
	      Graphics2D g2d = (Graphics2D) g.create();
	         Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	         g2d.setStroke(dashed);
	         g2d.setPaint(Color.WHITE);
	         g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
	         g2d.dispose();
	 }
	 
	 //method that paints a white rectangle onto the screen - no positioning as of yet
	 @Override
	 public void paintComponent(Graphics g) {
		 
	     super.paintComponent(g);
	     g.setColor(Color.WHITE);		//sets the color
	     g.fillRect(20, 20, 100, 100);	//sets the dimensions of the rectangle
	     
	     paintDottedLine(g); //paints the dotted line to the screen
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
		
		update();
		
	}
 
}
