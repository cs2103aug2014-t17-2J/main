/**
 * @author Andy Hsu Wei Qiang 
 * This class process all the Help Menu
 * logic
 * 
 */
package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.guide.HelpGuide;

public class UserInterfaceHelpMenu {
    /**
  	 * UIManager.setLookAndFeel() method to set the look and feel
  	 */
	public static void changeAppearance(){
        try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This class creates the Exit Insruction Label
	 */
    public static JLabel createExitLabel(){
        JLabel lblExit = new JLabel("   Press <F1> again to exit the Help Menu");
        lblExit.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblExit.setBackground(new Color(127, 255, 212));
        lblExit.setBounds(0, 320, 434, 30);
        lblExit.setOpaque(true);
        
        return lblExit;
    }
	
	/**
	 * This class creates the Main Tab Menu
	 */
    public static JTabbedPane createMenuTab() {
		JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
        
        JPanel jpAdd = new JPanel();
        JLabel lblAdd = new JLabel();
        lblAdd.setText(HelpGuide.buildHelpGuideAddString());
        jpAdd.add(lblAdd);
        jtp.addTab("<Add>", jpAdd);
        
        JPanel jpView = new JPanel();
        JLabel lblView = new JLabel();
        lblView.setText(HelpGuide.buildHelpGuideViewString());
        jpView.add(lblView);
        jtp.addTab("<View>", jpView);
        
        JPanel jpEdit = new JPanel();
        JLabel lblEdit = new JLabel();
        lblEdit.setText(HelpGuide.buildHelpGuideEditString());
        jpEdit.add(lblEdit);
        jtp.addTab("<Edit>", jpEdit);
        
        JPanel jpDelete = new JPanel();
        JLabel lblDelete = new JLabel();
        lblDelete.setText(HelpGuide.buildHelpGuideDeleteString());
        jpDelete.add(lblDelete);
        jtp.addTab("<Delete>", jpDelete);
        
        JPanel jpSearch = new JPanel();
        JLabel lblSearch = new JLabel();
        lblSearch.setText(HelpGuide.buildHelpGuideSearchString());
        jpSearch.add(lblSearch);
        jtp.addTab("<Search>", jpSearch);
 
        return jtp;
    }
}
