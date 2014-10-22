/**
 * @author Andy Hsu Wei Qiang 
 * This class creates the Help Menu when 
 * user hotkey <F1> is pressed
 * 
 */
package ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
/*
 * This class is the help menu the shows
 * different command to the user
 */
public class HelpMenu {
    public static JFrame frame;
	JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
	private static final int Xcoordinate = 310;
	private static final int Ycoordinate = 258;
	
//    public Container createHelpPane() {
//        //Create the content-pane-to-be.
//        JPanel contentPane = new JPanel(new BorderLayout());
//        contentPane.setOpaque(true);
// 
//        //Create a scrolled text area.
//        output = new JTextArea(5, 30);
//        output.setEditable(false);
//        scrollPane = new JScrollPane(output);
// 
//        //Add the text area to the content pane.
//        contentPane.add(scrollPane, BorderLayout.CENTER);
// 
//        return contentPane;
//    }
    /**
  	 * UIManager.setLookAndFeel() method to set the look and feel
  	 */
	private static void changeAppearance(){
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
	 * This class creates the Main Tab Menu
	 */
    private static JTabbedPane createMenuTab() {
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
	/**
	 * This class creates the Exit Insruction Label
	 */
    private static JLabel createExitLabel(){
        JLabel lblExit = new JLabel("   Press <F1> again to exit the Help Menu");
        lblExit.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblExit.setBackground(new Color(127, 255, 212));
        lblExit.setBounds(0, 193, 434, 30);
        lblExit.setOpaque(true);
        
        return lblExit;
    }
	/**
	 * Initialize the contents of the frame.
	 */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("-Help Menu-");
        frame.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent arg0) {
        		if(arg0.getKeyCode() == KeyEvent.VK_F1){
        			frame.dispose();
        		}
        	}
        });
        
        //Disable Resize
        frame.setResizable(false);
        
        //call the method to change the appearance of the frame
        changeAppearance();
        	
        //Create and set up the content pane.
        frame.getContentPane().add(createMenuTab(), BorderLayout.CENTER);
        
        //Display the window.
        frame.setSize(340, 450);
        frame.setVisible(true);
        
        //Set the location of the Help Menu beside the main window
        frame.setLocation(Xcoordinate, Ycoordinate);
        
        //Set the focus to the main frame
        frame.setFocusable(true);
        frame.getContentPane().setLayout(null);
        
        //Create the Exit Instruction Label
        frame.getContentPane().add(createExitLabel());
    }
	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}