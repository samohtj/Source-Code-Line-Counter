package linecounter.userinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import linecounter.RunResult;
import linecounter.Settings;

/**
 * A JLabel object that displays the run controls of the program.
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public class RunProgramPanel extends SourceCounterPanel {

	private JButton runButton;
    private JCheckBox ignoreCommentsCheckbox;

    Settings settings;

	/**
	 * Construct a panel to hold runtime controls.
	 * @param  set Settings to use.
	 * @return     New RunProgramPanel object.
	 */
	public RunProgramPanel(Settings set) {

		this.settings = set;

		runButton = new JButton("Run counter");
		ignoreCommentsCheckbox = new JCheckBox("Include comments and whitespace");

		ignoreCommentsCheckbox.setSelected(settings.ignoreComments);

        ignoreCommentsCheckbox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		settings.ignoreComments = ignoreCommentsCheckbox.isSelected();
        	}
        });

        setLayout(new GridLayout(2, 1));
        add(runButton);
        add(ignoreCommentsCheckbox);
	}

	/**
	 * Add an ActionListener to the button in this object.
	 * @param listener Listener to add.
	 */
	public void addButtonListener(ActionListener listener) {
		runButton.addActionListener(listener);
	}

	/**
	 * Update the panel.
	 * @param result Runtim result to pull data from.
	 */
	@Override
	public void update(RunResult result) {

	}
}
