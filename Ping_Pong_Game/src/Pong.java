// import JFrame to the project - this provides the window that the game will 'sit' inside of
import javax.swing.JFrame;


//the 'Pong' class will inherit from the JFrame class in order to build the window for the game to be played inside
public class Pong extends JFrame{
	
	//future proofing the window by using 'final' variable to make the variables inside non changeable
	final static String WINDOW_TITLE = ("Ping-Pong");
	final static int WINDOW_WIDTH = 800;
	final static int WINDOW_HEIGHT = 600;
	
	//constructor to create the Ping Pong window
	public Pong() {
		
		setTitle(WINDOW_TITLE);
		
		//size is set (x,y)
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setResizable(false);
		
		//adding new pong panel to fil the JFrame window
		add(new PongPanel());
		setVisible(true);
		
		//sets what to do when the 'close' button is pressed on the window - other options are noting, or hide on close ffor example
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}

	 public static void main(String[] args) {   
		 
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
                  new Pong();
           }
         });
     }
}
