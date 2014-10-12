package brain;


import logic.LogicManager;
import logic.utility.Task;
import userInterface.UserIntSwing;
import dataStorage.BasicDataHandler;
import dataStorage.DataHandler;
import dataStorage.ObservableList;


public class ProcessorV2 {
	
    
	public static void main(String[] args) {

		//list = dataHand.getList("today");
	    ObservableList<Task> observableList = new ObservableList<Task>(null);
	    DataHandler dataHandler = new BasicDataHandler(observableList);
        LogicManager logicManager = new LogicManager(dataHandler);	    
        UserIntSwing swi = new UserIntSwing(logicManager, observableList);
        observableList.addObserver(swi);
		swi.execute();
	}
}