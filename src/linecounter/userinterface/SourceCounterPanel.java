package linecounter.userinterface;

import javax.swing.JPanel;

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
	
	abstract void update();
}
