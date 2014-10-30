package ui.logic.command;

import java.awt.event.KeyEvent;

/**
 * @author Andy Hsu Wei Qiang 
 * This class create the Virtual Keyboard keys
 */
public class VK {
    
    public static int enter() {
    	return KeyEvent.VK_ENTER;
    }
    
    public static int help() {
    	return KeyEvent.VK_F1;
    }
    
    public static int add() {
    	return KeyEvent.VK_F2;
    }
    
    public static int view() {
    	return KeyEvent.VK_F3;
    }
    
    public static int edit() {
    	return KeyEvent.VK_F4;
    }
    
    public static int delete() {
    	return KeyEvent.VK_F5;
    }
    
    public static int search() {
    	return KeyEvent.VK_F6;
    }
  
    public static int undo() {
    	return KeyEvent.VK_F11;
    }
    
    public static int redo() {
    	return KeyEvent.VK_F12;
    }
}
