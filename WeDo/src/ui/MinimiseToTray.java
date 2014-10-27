package ui;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang 
 * This class handles the "Minimise To Tray"
 * operation
 */
public class MinimiseToTray {
	private static TrayIcon trayIcon;
	private static SystemTray tray;
	
	private static final String MAIN_FRAME_NAME = "Wedo";
	private static final String SYSTEMTRAY_MENU_OPEN = "Open";
	private static final String SYSTEMTRAY_MENU_EXIT = "Exit";
	
	public static void Minimise(WindowEvent arg){
		
		Image image = Toolkit.getDefaultToolkit().getImage(
				UserIntSwing.class.getResource("/ui/icon/WeDo.png"));
		PopupMenu popup = new PopupMenu();
		trayIcon = new TrayIcon(image, MAIN_FRAME_NAME, popup);
		trayIcon.setImageAutoSize(true);
		
		if(SystemTray.isSupported()){
			System.out.println("SystemTray supported");
			tray = SystemTray.getSystemTray();
			
			MenuItem popupItem = new MenuItem(SYSTEMTRAY_MENU_OPEN);
			popup.add(popupItem);
			popupItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openMainFrame();
				}
			});
			MenuItem popupItemExit = new MenuItem(SYSTEMTRAY_MENU_EXIT);
			popup.add(popupItemExit);
			popupItemExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exitMainFrame();
				}
			});
		}
		else{
			System.out.println("system tray not supported");
		}
		
		// Pass the 'iconified' parameter to minimise a frame
		if(arg.getNewState() == Frame.ICONIFIED){
			try{
				tray.add(trayIcon);
				UserIntSwing.frame.setVisible(false);
				System.out.println("added to System Tray");
			} catch (AWTException ex){
				System.out.println("unable to add to System Tray");
			}
		}
	}
	
	/**
	 * This operation opens the main frame when
	 * the "Open" menu on the SystemTray is pressed
	 */
	private static void openMainFrame(){
		UserIntSwing.frame.setVisible(true);
		UserIntSwing.frame.setExtendedState(JFrame.NORMAL);
		tray.remove(trayIcon);
		UserIntSwing.frame.setVisible(true);
		System.out.println("Tray icon removed");
	}
	
	/**
	 * This operation closes the main frame when
	 * the "Exit" menu on the SystemTray is pressed
	 */
	private static void exitMainFrame(){
		System.out.println("Exiting......");
		System.exit(0);
	}
}
