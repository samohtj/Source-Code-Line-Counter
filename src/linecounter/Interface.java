package linecounter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Interface extends JFrame{
	
	JComboBox<?> languagesDropDown = new JComboBox<Object>(LanguagesList.availableLangs);
	
	public Interface(){
		// Add an action listener to the lanugages drop down
		languagesDropDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				JComboBox<?> cb = (JComboBox<?>)ev.getSource();
		        String langName = (String)cb.getSelectedItem();
		        // TODO Actually make this do something
		        System.out.println(langName);
			}
		});
		
		JPanel langSelectionPanel = new JPanel();
		
		// Add the languages drop down menu to the language selection panel
		langSelectionPanel.add(languagesDropDown);
		
		this.setLayout(new GridLayout(2, 1));
		this.add(langSelectionPanel);
	}
	
	public static void main(String[] args) {
		Interface frame = new Interface();
		
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
