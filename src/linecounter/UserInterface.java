package linecounter;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import linecounter.userinterface.LanguageSelectionPanel;
import linecounter.userinterface.ProjectSelectionPanel;
import linecounter.userinterface.ResultsPanel;
import linecounter.userinterface.RunProgramPanel;
import linecounter.userinterface.SourceCounterPanel;

/**
 * Displays a graphical user interface to control the application.
 */
public class UserInterface extends JFrame{
	private static final long serialVersionUID = -230222760615385955L;

    private Settings settings = Settings.load();

    // Create a project reader to look through the project files
    ProjectReader projectReader = new ProjectReader(settings);

    public UserInterface(){

        final ProjectReader projectReader = new ProjectReader(settings);

        if(settings.rootFolder==null){
            try {
                settings.chooseRootFolder();
            } catch (NoFileChosenException e) {
                System.exit(0);
            }
        }
        
        // Create panels to store the two different selection options (project folder and language)
        SourceCounterPanel langSelectionPanel   	= new LanguageSelectionPanel(settings);
        SourceCounterPanel projectSelectionPanel	= new ProjectSelectionPanel(settings);
        SourceCounterPanel runProgramPanel			= new RunProgramPanel(settings);
        SourceCounterPanel resultsPanel				= new ResultsPanel(settings);
        
        // ON WINDOW CLOSE
        addWindowListener(new java.awt.event.WindowAdapter(){
            public void windowClosing(java.awt.event.WindowEvent ev){
                Settings.save(settings);	// Serialize the settings
                System.exit(0);       		// Exit the program
            }
        });

        // LEFT SIDE PANEL
        JPanel leftPanel = new JPanel(new GridLayout(3, 1));
        leftPanel.add(projectSelectionPanel);
        leftPanel.add(runProgramPanel);
        leftPanel.add(resultsPanel);

        this.setLayout(new GridLayout(1, 2));
        this.add(leftPanel);
        this.add(langSelectionPanel);
    }

    public static void main(String[] args) {
        UserInterface frame = new UserInterface();
        frame.setTitle("Source Code Line Counter");
        frame.setSize(725, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);

    }

}
