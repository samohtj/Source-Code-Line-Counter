package linecounter.userinterface;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import linecounter.RunResult;
import linecounter.Settings;

/**
 * Abstract definition of a panel for use in this application.
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public abstract class SourceCounterPanel extends JPanel {

	Settings settings;

	/**
	 * Update the panel.
	 * @param result Runtim result to pull data from.
	 */
	public abstract void update(RunResult result);

	/**
	 * Add a listener to any buttons on the panel.
	 * @param actionListener Listener to add.
	 */
	public void addButtonListener(ActionListener actionListener) {

	}
}
