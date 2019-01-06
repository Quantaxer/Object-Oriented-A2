import java.awt.EventQueue;

/**
* This is the Planner class, which can fulfill the user stories as defined by A1.
*
* @author Garnet McLaren
* @version Oct. 25, 2018
*/


public class Planner
{
    public static void main(String args[])
    {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
   
