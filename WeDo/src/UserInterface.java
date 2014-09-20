import java.util.ArrayList;

import logic.CommandHandler;
import dataStorage.DataHandler;


public class UserInterface {
	
	private final static ArrayList<String> list = new ArrayList<String>();

	public UserInterface() 
	{
		
		DataHandler dataHand = new DataHandler();
		CommandHandler commHand = new CommandHandler(dataHand);	
	}

	public static void main(String[] args) 
	{
		 //list = dataHand.getList();
	}

}
