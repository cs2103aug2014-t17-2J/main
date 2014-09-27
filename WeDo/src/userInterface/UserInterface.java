package userInterface;

import java.util.ArrayList;

import logic.CommandHandler;
import dataStorage.DataHandler;


public class UserInterface {
	
	private final static ArrayList<String> list = new ArrayList<String>();

	public UserInterface() 
	{
		
		DataHandler dataHand = new DataHandler();
		CommandHandler commHandler = new CommandHandler(dataHand);	
	}

	public static void main(String[] args) {

		// list = dataHand.getList("today");
		UserIntSwing swi = new UserIntSwing();
		swi.execute();

	}
}