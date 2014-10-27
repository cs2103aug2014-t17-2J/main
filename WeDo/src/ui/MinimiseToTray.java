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
	
	private static final String FRAME_NAME = "Wedo";
	private static final String SYSTEMTRAY_OPEN = "Open";
	private static final String SYSTEMTRAY_EXIT = "Exit";
	
	public static void Minimise(WindowEvent arg){
		
		Image image = Toolkit.getDefaultToolkit().getImage("/ui/icon/WeDo.png");
		PopupMenu popup = new PopupMenu();
		trayIcon = new TrayIcon(image, FRAME_NAME, popup);
		trayIcon.setImageAutoSize(true);
		
		if(SystemTray.isSupported()){
			System.out.println("SystemTray supported");
			tray = SystemTray.getSystemTray();
			
			MenuItem popupItem = new MenuItem(SYSTEMTRAY_OPEN);
			popup.add(popupItem);
			popupItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exitSystemTray();
				}
			});
			MenuItem popupItemExit = new MenuItem(SYSTEMTRAY_EXIT);
			popup.add(popupItemExit);
			popupItemExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openSystemTray();
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
	
	private static void exitSystemTray(){
		UserIntSwing.frame.setVisible(true);
		UserIntSwing.frame.setExtendedState(JFrame.NORMAL);
		tray.remove(trayIcon);
		UserIntSwing.frame.setVisible(true);
		System.out.println("Tray icon removed");
	}
	
	private static void openSystemTray(){
		System.out.println("Exiting......");
		System.exit(0);
	}
}
