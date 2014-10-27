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
	static TrayIcon trayIcon;
	static SystemTray tray;
	public static void Minimise(WindowEvent arg){
		if(SystemTray.isSupported()){
			System.out.println("system tray supported");
			tray=SystemTray.getSystemTray();

			Image image=Toolkit.getDefaultToolkit().getImage("/ui/icon/WeDo.png");
			ActionListener exitListener=new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting....");
					System.exit(0);
				}
			};
			PopupMenu popup=new PopupMenu();
			MenuItem defaultItem=new MenuItem("Open");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			defaultItem=new MenuItem("Close");
			defaultItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserIntSwing.frame.setVisible(true);
					UserIntSwing.frame.setExtendedState(JFrame.NORMAL);
				}
			});
			popup.add(defaultItem);
			trayIcon=new TrayIcon(image, "WeDo", popup);
			trayIcon.setImageAutoSize(true);
		}
		else{
			System.out.println("system tray not supported");
		}

		if(arg.getNewState() == Frame.ICONIFIED){
			try{
				tray.add(trayIcon);
				UserIntSwing.frame.setVisible(false);
				System.out.println("added to System Tray");
			} catch (AWTException ex){
				System.out.println("unable to add to System Tray");
			}
		}
		if(arg.getNewState() == Frame.MAXIMIZED_BOTH){
			tray.remove(trayIcon);
			UserIntSwing.frame.setVisible(true);
			System.out.println("Tray icon removed");
		}
		if(arg.getNewState() == Frame.NORMAL){
			tray.remove(trayIcon);
			UserIntSwing.frame.setVisible(true);
			System.out.println("Tray icon removed");
		}
	}
}
