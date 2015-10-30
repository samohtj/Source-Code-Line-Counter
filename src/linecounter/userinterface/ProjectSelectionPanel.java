package linecounter.userinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import linecounter.NoFileChosenException;
import linecounter.RunResult;
import linecounter.Settings;

/**
 * A JLabel object that displays the project selection controls.
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public class ProjectSelectionPanel extends SourceCounterPanel {

	private JLabel rootFolderLabel		= new JLabel("Root folder: None selected");
    private JButton chooseRootButton	= new JButton("Choose root folder");

	/**
	 * Construct a new panel for project slection.
	 * @param  set Settings to use.
	 * @return     New ProjectSelectionPanel object.
	 */
    public ProjectSelectionPanel(Settings set) {
    	this.settings = set;

    	rootFolderLabel.setText("Root folder: "+settings.rootFolder.getName());

        chooseRootButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    settings.chooseRootFolder();
                    rootFolderLabel.setText("Root folder: "+settings.rootFolder.getName());
                } catch (NoFileChosenException e) {}
            }
        });

        setLayout(new GridLayout(2, 1));
        add(chooseRootButton);
        add(rootFolderLabel);
        setBorder(new TitledBorder("Project"));
    }

	/**
	 * Update the panel.
	 * @param result Result of the run to take data from.
	 */
	@Override
	public void update(RunResult result) {
		// TODO Make it update.
	}

}
