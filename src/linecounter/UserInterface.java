package linecounter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Displays a graphical user interface to control the application.
 */
public class UserInterface extends JFrame{
	private static final long serialVersionUID = -230222760615385955L;

	private JComboBox<?> languagesDropDown;

    private JLabel rootFolderLabel		= new JLabel("Root folder: None selected");
    private JButton chooseRootButton    = new JButton("Choose root folder");

    private JButton runButton           = new JButton("Run counter");
    private JCheckBox ignoreCommentsCheckbox = new JCheckBox("Include comments and whitespace");

    // Results labels
    private JLabel numLinesLabel    = new JLabel();
    private JLabel numFilesLabel    = new JLabel();
    private JLabel numFoldersLabel  = new JLabel();

    private JLabel commentLinesLabel = new JLabel();

    private Settings settings = Settings.load();

    // Create a project reader to look through the project files
    ProjectReader projectReader = new ProjectReader(settings);

    public UserInterface(){

        final ProjectReader projectReader = new ProjectReader(settings);

        ignoreCommentsCheckbox.setSelected(settings.ignoreComments);
        languagesDropDown  = new JComboBox<Object>(settings.availableLangs.availableLangs());
        languagesDropDown.setSelectedIndex(settings.selectedLangIndex);

        if(settings.rootFolder==null){
            try {
                settings.chooseRootFolder();
            } catch (NoFileChosenException e) {
                System.exit(0);
            }
        }
        
        rootFolderLabel.setText("Root folder: "+settings.rootFolder.getName());

        //
        //    ACTION LISTENERS
        //

        // LANGUAGES LIST DROPDOWN
        languagesDropDown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                // Update the settings
                settings.selectedLangIndex = ((JComboBox<?>) ev.getSource()).getSelectedIndex();
            }
        });

        // ROOT FOLDER BUTTON
        chooseRootButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                try {
                    // Try to choose the root folder of the project
                    settings.chooseRootFolder();

                    // Set the rootFolderLabel's text contents to the name of the root folder
                    rootFolderLabel.setText("Root folder: "+settings.rootFolder.getName());
                    
                } catch (NoFileChosenException e) {}
            }
        });
        
        // IGNORE COMMETNS CHECKBOX
        ignoreCommentsCheckbox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		// Update the settings
        		settings.ignoreComments = ignoreCommentsCheckbox.isSelected();
        	}
        });

        // RUN BUTTON
        runButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                // Run the program, and update the results labels appropriately
                projectReader.run();
                numLinesLabel.setText("Total lines: "+projectReader.totalLines());
                numFilesLabel.setText("Total files searched: "+projectReader.numFiles());
                numFoldersLabel.setText("Total folders searched: "+projectReader.numFolders());

                // Only update these labels if the ignoreComments box is checked
                // Otherwise, leave them blank
                commentLinesLabel.setText(!ignoreCommentsCheckbox.isSelected()?"Comment lines: "+projectReader.commentLines():"");
            }
        });

        // ON WINDOW CLOSE
        addWindowListener(new java.awt.event.WindowAdapter(){
            public void windowClosing(java.awt.event.WindowEvent ev){
                Settings.save(settings);	// Serialize the settings
                System.exit(0);       		// Exit the program
            }
        });

        //
        //    ORGANIZATION
        //

        // Create panels to store the two different selection options (project folder and language)
        JPanel langSelectionPanel       = new JPanel();
        JPanel projectSelectionPanel    = new JPanel();
        JPanel runProgramPanel          = new JPanel();
        JPanel resultsPanel             = new JPanel();

        // Add borders to the panels to differentiate them
        langSelectionPanel.setBorder(new TitledBorder("Language"));
        projectSelectionPanel.setBorder(new TitledBorder("Project"));
        resultsPanel.setBorder(new TitledBorder("Results"));

        // PROJECT SLECTION PANEL
        projectSelectionPanel.setLayout(new GridLayout(2, 1));
        projectSelectionPanel.add(chooseRootButton);
        projectSelectionPanel.add(rootFolderLabel);

        // LANGUAGE SELECTION PANEL
        langSelectionPanel.add(languagesDropDown);

        // RUN PROGRAM PANEL
        runProgramPanel.setLayout(new GridLayout(2, 1));
        runProgramPanel.add(runButton);
        runProgramPanel.add(ignoreCommentsCheckbox);

        // RESULTS PANEL
        resultsPanel.setLayout(new GridLayout(4, 2));
        resultsPanel.add(new JLabel("RESULTS"));
        resultsPanel.add(new JLabel());
        resultsPanel.add(numLinesLabel);
        resultsPanel.add(commentLinesLabel);
        resultsPanel.add(numFilesLabel);
        resultsPanel.add(new JLabel());
        resultsPanel.add(numFoldersLabel);

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
