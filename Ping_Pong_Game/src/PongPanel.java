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
	 
	 //Variables for the color and timer
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
	 
	 GameState gameState = GameState.INITIALISING;	//setting GameState to initialising - this is so objects load before the game commences
	 
	 Ball ball;										//calling the ball object
	 Paddle paddle1, paddle2;						//calling the paddle objects
	       
	  //method which handles the drawing of the objects
	  public void createObjects() {						
		  
	         ball = new Ball(getWidth(), getHeight());
	         paddle1 = new Paddle(Player.One, getWidth(), getHeight());
	         paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
	         
	         
	         
	  }
	  
	  //method to paint the objects onto the panel
	  private void paintSprite(Graphics g, Sprite sprite) {
		  
	      g.setColor(sprite.getColor());
	      g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
	  }
	  
	  
	  private void update() {
		  
          switch(gameState) {
          
              case INITIALISING: {
            	  
                  createObjects();
                 gameState = GameState.PLAYING;
                  break;
              }
              
              case PLAYING: {
                  break;
             }
              
             case GAMEOVER: {
                 break;
             }
         }
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
	 
	 //***REMOVED*** previous paint component method - 
	 
	 //ADDED new method which calls for the ball to be painted on screen - in the correct position
	 @Override
     public void paintComponent(Graphics g) {
		 
         super.paintComponent(g);
         paintDottedLine(g);
         if(gameState != GameState.INITIALISING) {
             paintSprite(g, ball);
             paintSprite(g, paddle1);			//painting the ball and paddle objects
             paintSprite(g, paddle2);
         }
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
