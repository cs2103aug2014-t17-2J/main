//package userInterface;
//
//import java.util.ArrayList;
//
//import brain.Processor;
//import logic.CommandHandler;
//import dataStorage.DataHandler;
//
//
//public class UserInterface {
//	
//	private Processor processor;
//
//	private final static ArrayList<String> list = new ArrayList<String>();
//
//	public UserInterface() 
//	{
//		
//		DataHandler dataHand = new DataHandler();
//		CommandHandler commHand = new CommandHandler(dataHand);	
//	}
//
//	public static void main(String[] args) {
//
//		list = dataHand.getList("today");
//		UserIntSwing swi = new UserIntSwing(this);
//		swi.execute();
//		
//		InteractiveForm interForm = new InteractiveForm();
//		interForm.execute();
//	}
//}