package linecounter.userinterface;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import linecounter.RunResult;
import linecounter.Settings;

/**
 * A JLabel object that displays the results of a program execution.
 * This class contains all the components needed to display information about a program execution.
 * Included are a line number display label, a file number display label, and a folder number display
 * label. All the information is created on execution.
 * <p>I will figure out how to make the results and sensors update in the future.
 * @author Jonathan Thomas
 *
 */
// Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public class ResultsPanel extends SourceCounterPanel {

    // Results labels
    private JLabel numLinesLabel    = new JLabel();
    private JLabel numFilesLabel    = new JLabel();
    private JLabel numFoldersLabel  = new JLabel();

    Settings settings;

	/**
	 * Construct a new JPanel to display a runtime result.
	 * @param  set Settings to use.
	 * @return     New ResultsPanel object.
	 */
	public ResultsPanel(Settings set) {
		setLayout(new GridLayout(3, 2));
        add(numLinesLabel);
        add(numFilesLabel);
        add(new JLabel());
        add(numFoldersLabel);
        setBorder(new TitledBorder("Results"));
	}

	/**
	 * Update the panel.
	 * @param result RunResult to pull data from.
	 */
	@Override
	public void update(RunResult result) {
		numLinesLabel.setText("Total lines: "+result.totalLines());
        numFilesLabel.setText("Total files searched: "+result.numFiles());
        numFoldersLabel.setText("Total folders searched: "+result.numFolders());
	}
}
