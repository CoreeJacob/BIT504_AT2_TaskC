 import java.awt.Color;
 
 public class Paddle extends Sprite {	//class for the paddle
	 
	 //variables for the paddles physical attributes
	 private static final int PADDLE_WIDTH = 10;
     private static final int PADDLE_HEIGHT = 100;
     private static final Color PADDLE_COLOR = Color.WHITE;
     private static final int DISTANCE_FROM_EDGE = 40;
       
       
 
 
 
 public Paddle(Player player, int panelWidth, int panelHeight) {
	 
    setWidth(PADDLE_WIDTH);
    setHeight(PADDLE_HEIGHT);		//setting the dimensions of the paddles in the constructor
    setColor(PADDLE_COLOR);
    int xPos;					//variable to hold the initial position of the paddle
    
    if(player == Player.One) {
        xPos = DISTANCE_FROM_EDGE;
    } 
    
    else {
        xPos = panelWidth - DISTANCE_FROM_EDGE - getWidth();
    }
    
    
    setInitialPosition(xPos, panelHeight / 2 - (getHeight() / 2));
    resetToInitialPosition();											//resetting to initial position if round finishes
    
 	}
}