import java.awt.Color;
//series of action and key event 'listeners' that respond to players inputs and moves the paddles along the board
//also sets game code for ball flight etc
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//imports timer to provide a timed loop that updates the position of all the elements on the screen - gives the illusion of movement on the screen
import javax.swing.Timer;
//imports JPanel - method that sits 'on top' of the window - defines the playing area of the game
import javax.swing.JPanel;
//imports painting methods that allow elements of the game to be rendered on the panel
import java.awt.Graphics;
import java.awt.Graphics2D;
//import that draws the line down the middle of the panel
import java.awt.Stroke;
import java.awt.BasicStroke;
  
  public class PongPanel extends JPanel implements ActionListener, KeyListener {
	  
		 //Variables for the color, timer and velocity of the ball
	  private final static Color PANEL_COLOR = Color.BLACK;
	  private final static int TIMER_DELAY = 5;
	  private final static int BALL_MOVE_SPEED = 2;

	  
	  //constructor for the PongPanel class - 
	  public PongPanel() {
		  
		  setBackground(PANEL_COLOR);
		  Timer timer = new Timer(TIMER_DELAY, this);
		  timer.start();
		  addKeyListener(this);
		  setFocusable(true);
		  
	  }
	  

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	  @Override
      public void keyPressed(KeyEvent event) {
          if(event.getKeyCode() == KeyEvent.VK_UP) {
              paddle2.setyVelocity(-1);
         } else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
              paddle2.setyVelocity(1);
          }
      }
  
     @Override
     public void keyReleased(KeyEvent event) {
         if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
             paddle2.setyVelocity(0);
         }
     }
	
	
	@Override
	public void paintComponent(Graphics g) { //method which paints the objects onto the panel
		
		super.paintComponent(g);
		paintDottedLine(g);
		
		if (gameState != GameState.Initialising) {	//paints the objects while initialising
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);

		}
		
	}
	
	//method to paint the dotted line on the panel
	private void paintDottedLine(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g2d.dispose();
	}
		
	@Override
	public void actionPerformed(ActionEvent event) {
		
		update();
		repaint();
		
	}
	
	
	//creating the ball variable 
	Ball ball;
	
	
	//variable for the paddles
	Paddle paddle1;
	Paddle paddle2;
	
	//method that sets the physical dimensions of the objects - ball and 2 paddles
	public void createObjects() {
		
		
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
			
	}
	
	GameState gameState = GameState.Initialising;
	
	private void update() {
		
		switch(gameState) {
		
		case Initialising: {
			createObjects();
			gameState = GameState.Playing;
			ball.setxVelocity(BALL_MOVE_SPEED);
			ball.setyVelocity(BALL_MOVE_SPEED);
			break;
			
		}
		case Playing:{
			moveObject(paddle1);
			moveObject(paddle2);
			moveObject(ball);
			checkWallBounce();
			break;
		}
		case GameOver:{
			break;
		}
		
		}
		
	}
	
	//method that paints the Sprite class
	private void paintSprite(Graphics g, Sprite sprite) {
		
		g.setColor(sprite.getColor());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
		
	}
	
	//method that will hopefully allow for the moving of objects within the game
	private void moveObject(Sprite obj) {
		
		obj.setxPosition(obj.getxPosition() + obj.getxVelocity(), getWidth());
		obj.setyPosition(obj.getyPosition() + obj.getyVelocity(), getHeight());
	}
	
	private void checkWallBounce() {
		
		if (ball.getxPosition() <= 0) {
			//i.e it hits the left side of the panel
			ball.setxVelocity(-ball.getxVelocity());
			resetBall();
		}
		
		else if (ball.getxPosition() >= getWidth() - ball.getWidth()) {
			//i.e ball hits the right side of the screen
			ball.setxVelocity(-ball.getxVelocity());
			resetBall();

			
		}
		
		if (ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight()) {
			//i.e ball hits the top or the bottom of the screen
			ball.setyVelocity(-ball.getyVelocity());

			
		}
	}
	
	
	private void resetBall() {
		
		ball.resetToInitialPosition();
	}
	
	
	
	
	
	  
 }