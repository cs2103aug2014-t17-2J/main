package ui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
 
/*
 * This class is the help menu the shows
 * different command to the user
 */
public class HelpMenu {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
 
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
        
        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu("<Add Command>");
        menuBar.add(menu);
 
        //Build second menu in the menu bar.
        menu = new JMenu("<View Command>");
        menuBar.add(menu);
        
        //Build second menu in the menu bar.
        menu = new JMenu("<Edit Command>");
        menuBar.add(menu);
        
        //Build second menu in the menu bar.
        menu = new JMenu("<Delete Command>");
        menuBar.add(menu);
 
        return menuBar;
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Help Menu");
 
        //Create and set up the content pane.
        HelpMenu demo = new HelpMenu();
        frame.setJMenuBar(demo.createMenuBar());
        
        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
        
       // UserLogic.setupHelpFrameLocation();
    }
 
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