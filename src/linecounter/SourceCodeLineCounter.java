package linecounter;

import javax.swing.JFrame;

import linecounter.userinterface.UserInterface;

/**
 * Main program entry point.
 * @author Jonathan Thomas
 *
 */
public class SourceCodeLineCounter {

	/**
	 * Main program entry point.
	 * This is a Swing application, so you don't get to pass in any parameters. Not yet, anyways.
	 * It's possible I might make it run from the command line later on.
	 * @param args
	 */
    public static void main(String[] args) {
        UserInterface frame = new UserInterface();
        frame.setTitle("Source Code Line Counter");
        frame.setSize(725, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

}
