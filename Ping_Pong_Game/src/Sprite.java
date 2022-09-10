import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
	
	 private int xPosition, yPosition;		//shared variables for ball and paddle position
	 private int xVelocity, yVelocity;		//shared variables for ball and paddle velocity
	 private int width, height;				//shared variables for height and width of the ball and paddle
	 private Color color;
	 private int initialXPosition, initialYPosition;


	 //setters and getters for position

	public int getxPosition() {
		return xPosition;
	}
	
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
		
	}
	
	public int getyPosition() {
		return yPosition;
	}
	
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	//setters that will ensure that the ball and paddles stay within the bounds of the game
	public void setXPosition(int newX, int panelWidth) {
	       xPosition = newX;
	       if(xPosition < 0) {
	           xPosition = 0;
	       } else if(xPosition + width > panelWidth) {
	           xPosition = panelWidth - width;
	       }
	 }
	
	 public void setYPosition(int newY, int panelHeight) {
	      yPosition = newY;
	      if(yPosition < 0) {
	          yPosition = 0;
	      } else if(yPosition + height > panelHeight) {
	          yPosition = panelHeight - height;
	      }
	 }
	 //setting the initial start positions of the paddles and ball
	 public void setInitialPosition(int initialX, int initialY) {
	       initialXPosition = initialX;
	       initialYPosition = initialY;
	 }
	       
	 public void resetToInitialPosition() {
	      setxPosition(initialXPosition);
	       setyPosition(initialYPosition);
	       
	 }

	//setters and getters for velocity
	
	public int getxVelocity() {
		return xVelocity;
	}
	
	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}

	public int getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	//setters and getters for height and width

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	//getters and setters for the color of the objects
	public Color getColor() {
		return color;	
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	 public Rectangle getRectangle() {		//method to draw the rectangle
         return new Rectangle(getxPosition(), getyPosition(), getWidth(), getHeight());
     }

}