package linecounter.userinterface;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import linecounter.RunResult;
import linecounter.Settings;

/**
 * 
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public abstract class SourceCounterPanel extends JPanel{
	
	Settings settings;
	
	public abstract void update(RunResult result);

	public void addButtonListener(ActionListener actionListener) {
		
	}
}
