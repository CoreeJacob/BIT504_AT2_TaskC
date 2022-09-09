// import JFrame to the project - this provides the window that the game will 'sit' inside of
import javax.swing.JFrame;


//the 'Pong' class will inherit from the JFrame class in order to build the window for the game to be played inside
public class Pong extends JFrame{
	
	//constructor to create the Ping Pong window
	
	public Pong() {
		
		setTitle("Ping-Pong");
		
		//size is set (x,y)
		setSize(800,600);
		setResizable(false);
		setVisible(true);
		
		//sets what to do when the 'close' button is pressed on the window - other options are noting, or hide on close ffor example
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}

	public static void main(String[] args) {
		
		//call on pong to create window
		new Pong();

	}

}
