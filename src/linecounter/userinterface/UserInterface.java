package linecounter.userinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import linecounter.NoFileChosenException;
import linecounter.ProjectReader;
import linecounter.Settings;

/**
 * Displays a graphical user interface to control the application.
 * @author Jonathan Thomas
 *
 */
public class UserInterface extends JFrame{
	private static final long serialVersionUID = -230222760615385955L;

    private Settings settings = Settings.load();

    // Create a project reader to look through the project files
    ProjectReader projectReader = new ProjectReader(settings);

	/**
	 * Construct a new UserInterface frame.
	 * @return New user interface.
	 */
    public UserInterface(){

        final ProjectReader projectReader = new ProjectReader(settings);

        if (settings.rootFolder==null) {
            try {
                settings.chooseRootFolder();
            } catch (NoFileChosenException e) {
				// If the user clicks the "Cancel" button on the file chooser dialog, exit the app.
                System.exit(0);
            }
        }

        // Create panels to store the two different selection options (project folder and language)
        SourceCounterPanel langSelectionPanel   	= new LanguageSelectionPanel(settings);
        SourceCounterPanel projectSelectionPanel	= new ProjectSelectionPanel(settings);
        SourceCounterPanel runProgramPanel			= new RunProgramPanel(settings);

        // This object has to be final, since it's referenced in the action listener below.
        final SourceCounterPanel resultsPanel		= new ResultsPanel(settings);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent ev) {
                Settings.save(settings);	// Serialize the settings
                System.exit(0);       		// Exit the program
            }
        });

        /*
         * Add an action listener to the button on the run panel.
         * This has to be here, since it affects more than one object.
         */
        runProgramPanel.addButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                // Run the program, and update the results labels appropriately
                resultsPanel.update(projectReader.run());
            }
        });

        JPanel leftPanel = new JPanel(new GridLayout(3, 1));
        leftPanel.add(projectSelectionPanel);
        leftPanel.add(runProgramPanel);
        leftPanel.add(resultsPanel);

        this.setLayout(new GridLayout(1, 2));
        this.add(leftPanel);
        this.add(langSelectionPanel);
    }
}
