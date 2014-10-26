package ui;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.WindowEvent;

import userInterface.UserIntSwing;

public class MinimiseToTray {
	static TrayIcon trayIcon;
    static SystemTray tray;
	public static void Minimise(WindowEvent arg){
		tray=SystemTray.getSystemTray();
		  if(arg.getNewState() == UserIntSwing.frame.ICONIFIED){
		      try {
		          tray.add(trayIcon);
		          UserIntSwing.frame.setVisible(false);
		          System.out.println("added to SystemTray");
		  } catch (AWTException ex) {
		      System.out.println("unable to add to tray");
		      }
		  }
		}
}
