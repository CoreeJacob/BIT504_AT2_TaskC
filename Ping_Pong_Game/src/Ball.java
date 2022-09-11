 import java.awt.Color;
   
   public class Ball extends Sprite {
	   
	   //variables for the ball
       private static final int BALL_WIDTH = 25;
       private static final int BALL_HEIGHT = 25;
       private static final Color BALL_COLOR = Color.WHITE;
   
       public Ball(int panelWidth, int panelHeight) {		//constructor for the ball to be used in play
    	   
    	   
          setWidth(BALL_WIDTH);
          setHeight(BALL_HEIGHT);
          setColor(BALL_COLOR);
          setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));
          resetToInitialPosition();		//used to reset the ball when it goes out of play i.e. when someone scores
      }
 }