import java.awt.Color;
import java.awt.Font;
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
	  
	  private final static Color PANEL_COLOR = Color.WHITE;
	  private final static int TIMER_DELAY = 5;
	  private final static int BALL_MOVE_SPEED = 3;
	  private final static int POINTS_TO_WIN = 7;		//sets the number of points needed to win
	  int player1Score = 0, player2Score = 0;			//playerscore variables will keep the score for each player
	  Player gameWinner;

	  
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
      public void keyPressed(KeyEvent event) {					//key event - codes for the moving of the paddle following input from the user
          if(event.getKeyCode() == KeyEvent.VK_UP) {
              paddle2.setyVelocity(-3);
         } else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
              paddle2.setyVelocity(3);
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
            paintScores(g);           

		}
		
		if (gameState == GameState.GameOver) {
			paintWinner(g);
			
		}
		
	}
	
	//method to paint the dotted line on the panel
	private void paintDottedLine(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.setPaint(Color.BLACK);
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g2d.dispose();
	}
	
	//
	private void paintScores(Graphics g) {
		 
		int xPadding = 100;
        int yPadding = 100;		
        int fontSize = 50;  
		
        Font scoreFont = new Font("Serif", Font.BOLD, fontSize);
        String leftScore = Integer.toString(player1Score);
        String rightScore = Integer.toString(player2Score);
         
        g.setFont(scoreFont);
        g.drawString(leftScore, xPadding, yPadding);
        g.drawString(rightScore, getWidth()-xPadding, yPadding);
       
        
    }
	
	 private final static int WINNER_TEXT_X = 200;
     private final static int WINNER_TEXT_Y = 200;
     private final static int WINNER_FONT_SIZE = 30;
     private final static String WINNER_FONT_FAMILY = "Serif";
     private final static String WINNER_MESSAGE = "Winner!!";
     
     private void paintWinner(Graphics g) {
    	 
         if(gameWinner != null) {
             Font winnerFont = new Font(WINNER_FONT_FAMILY, Font.BOLD, WINNER_FONT_SIZE);
            g.setFont(winnerFont);
            int xPosition = getWidth() / 2;
            if(gameWinner == Player.One) {
                xPosition -= WINNER_TEXT_X;
            } else if(gameWinner == Player.Two) {
                xPosition += WINNER_TEXT_X;
            }
            g.drawString(WINNER_MESSAGE, xPosition, WINNER_TEXT_Y);
        }
    }
		
	@Override
	public void actionPerformed(ActionEvent event) {
		
		update();
		repaint();
		
	}
	
	
	Ball ball;
	Paddle paddle1;			//creating the ball and paddle variables - these will be painted in another method
	Paddle paddle2;
	
	//method that sets the physical dimensions of the objects - ball and 2 paddles
	public void createObjects() {
		
		
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
			
	}
	
	GameState gameState = GameState.Initialising;		//sets the initial game state - 
														// important as changing the game state allows us to control certain variables
	private void update() {
		
		switch(gameState) {
		
		case Initialising: {		//prior to the game starting, creates the objects for the game
			
			createObjects();
			gameState = GameState.Playing;
			ball.setxVelocity(BALL_MOVE_SPEED);		//setting the x and y velocity of the balls
			ball.setyVelocity(BALL_MOVE_SPEED);
			break;
			
		}
		case Playing:{
			
			moveObject(paddle1);
			moveObject(paddle2);
			moveObject(ball);
			
			checkWallBounce();		
			checkPaddleBounce();	//call to check the wall and paddle bounces
            checkWin();				// call check win method to see if the game has been won

			break;
		}
		
		case GameOver:{
			
			break;
		}
		
		}
		
	}
	
	//method that paints the objects onto the screen - called via the Sprite class
	private void paintSprite(Graphics g, Sprite sprite) {
		
		g.setColor(sprite.getColor());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
		
	}
	
	//method that will hopefully allow for the moving of objects within the game
	private void moveObject(Sprite obj) {
		
		obj.setxPosition(obj.getxPosition() + obj.getxVelocity(), getWidth());
		obj.setyPosition(obj.getyPosition() + obj.getyVelocity(), getHeight());
		
	}
	
	//method which adds the score 
	private void addScore(Player player) {	
		
        if(player == Player.One) {
            player1Score++;
           
        } 
        
        else if(player == Player.Two) {
            player2Score++;
        }
    }
	
	//method to check if a player has won
	private void checkWin() {
		 
         if(player1Score >= POINTS_TO_WIN) {
             gameWinner = Player.One;
             gameState = GameState.GameOver;
             
         } 
         
        else if(player2Score >= POINTS_TO_WIN) {
             gameWinner = Player.Two;
             gameState = GameState.GameOver;
         }
     }
	
	private void checkWallBounce() {		//method to check if the ball has hit a 'wall'
		
		if(ball.getxPosition() <= 0) {
			
            // Hit left side of screen
            ball.setxVelocity(-ball.getxVelocity());
            addScore(Player.Two);
            resetBall();
        }
		
		else if(ball.getxPosition() >= getWidth() - ball.getWidth()) {
			
            // Hit right side of screen
            ball.setxVelocity(-ball.getxVelocity());
            addScore(Player.One);
            resetBall();
        }
		
        if(ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight()) {
        	
            // Hit top or bottom of screen
            ball.setyVelocity(-ball.getyVelocity());
        }
	}
	
	//method which checks for a collision between the ball and the paddle
	//if the ball and paddle intersect - the balls velocity is changed
	private void checkPaddleBounce() {
		
		 if(ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
	          ball.setxVelocity(BALL_MOVE_SPEED);
	      }
	      if(ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
	          ball.setxVelocity(-BALL_MOVE_SPEED);
	      }
		
	}
	
	
	private void resetBall() {
		
		ball.resetToInitialPosition();
	}
	
	
	
	
	
	  
 }