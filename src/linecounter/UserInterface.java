package linecounter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class UserInterface extends JFrame{
	
	private JComboBox<?> languagesDropDown = new JComboBox<Object>(LanguagesList.availableLangs);
	private JLabel rootFolderLabel 		= new JLabel("Root folder: None selected");
	private JButton chooseRootButton 		= new JButton("Choose root folder");
	private JButton runButton				= new JButton("Run counter");
	
	private File rootDirectory = null;
	
	public UserInterface(){
		// Add an action listener to the languages drop down
		languagesDropDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				JComboBox<?> cb = (JComboBox<?>)ev.getSource();
		        String langName = (String)cb.getSelectedItem();
		        // TODO Actually make this do something
		        System.out.println(langName);
			}
		});
		
		// Add an action listener to the choose root folder button
		chooseRootButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				try {
					rootDirectory = LineCounter.chooseRootFolder();
					rootFolderLabel.setText("Root folder: "+rootDirectory.getName());
				} catch (NoFileChosenException e) {
					
				}
			}
		});
		
		// Add an action listener to the run button
		runButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				// TODO Make this do stuff
			}
		});
		
		// Create panels to store the two different selection options (project folder and language)
		JPanel langSelectionPanel = new JPanel();
		JPanel projectSelectionPanel = new JPanel();
		
		// Add borders to the panels to differentiate them
		langSelectionPanel.setBorder(new TitledBorder("Language"));
		projectSelectionPanel.setBorder(new TitledBorder("Project"));
		
		// Add components to the project selection panel
		projectSelectionPanel.setLayout(new GridLayout(2, 1));
		projectSelectionPanel.add(rootFolderLabel);
		projectSelectionPanel.add(chooseRootButton);
		
		// Add the languages drop down menu to the language selection panel
		langSelectionPanel.add(languagesDropDown);
		
		// Create a panel to store the output panel and project selection panel
		JPanel leftPanel = new JPanel(new GridLayout(2, 1));
		leftPanel.add(projectSelectionPanel);
		
		this.setLayout(new GridLayout(1, 2));
		this.add(leftPanel);
		this.add(langSelectionPanel);
	}
	
	public static void main(String[] args) {
		UserInterface frame = new UserInterface();
		
		frame.setSize(800, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
