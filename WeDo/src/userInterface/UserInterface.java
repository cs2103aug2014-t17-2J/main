package userInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import logic.CommandHandler;
import dataStorage.DataHandler;


public class UserInterface {
	
	private final static ArrayList<String> list = new ArrayList<String>();

	public UserInterface() 
	{
		
		DataHandler dataHand = new DataHandler();
		CommandHandler commHandl = new CommandHandler(dataHand);	
	}

	public static void main(String[] args) {

		// list = dataHand.getList("today");
		UserIntSwing swi = new UserIntSwing();
		//swi.execute();
		
		InteractiveForm interForm = new InteractiveForm();
		interForm.execute();

	}
}