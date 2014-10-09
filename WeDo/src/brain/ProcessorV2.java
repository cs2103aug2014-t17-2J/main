package brain;


import logic.CommandHandler;
import logic.Task;
import userInterface.UserIntSwing;

import dataStorage.BasicDataHandler;
import dataStorage.DataHandler;
import dataStorage.ObservableList;


public class ProcessorV2 {
	
    
	public static void main(String[] args) {

		//list = dataHand.getList("today");
	    ObservableList<Task> observableList = new ObservableList<Task>(null);
	    DataHandler dataHandler = new BasicDataHandler(observableList);
        CommandHandler commandHandler = new CommandHandler(dataHandler);	    
        UserIntSwing swi = new UserIntSwing(commandHandler, observableList);
        observableList.addObserver(swi);
		swi.execute();
	}
}