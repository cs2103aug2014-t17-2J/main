package logic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;










import logic.commandList.AddCommand;
import logic.commandList.ClearCommand;
import logic.commandList.Command;
import logic.commandList.DeleteCommand;
import logic.commandList.EditCommand;
import logic.commandList.ExitCommand;
import logic.commandList.RedoCommand;
import logic.commandList.SearchCommand;
import logic.commandList.UndoCommand;
import logic.commandList.ViewCommand;
import logic.taskParser.TaskParserPlus;
import logic.utility.KeyMatcher;
import logic.utility.StringHandler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import dataStorage.DataHandler;
import definedEnumeration.TaskFeedBack;

/**
 * @author Kuan Tien Long This class handle all the commands passed in by the user
 * 
 */
public class CommandHandler {

    private DataHandler dataHandler;
    private UndoHandler undoHandler;
    

    /**
     * Constructor for CommandHandler
     * 
     * @param dataHandler
     *            the handler which contains of all the data
     */
    public CommandHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        undoHandler = new UndoHandler();
    }

    /**
     * Execute Command based on userInput
     * 
     * @param userInput
     *            the input to execute command
     * @return TaskFeedBack to continue or exit textBuddy
     */
    public TaskFeedBack executeCommand(String userInput) {

        Task task;
        StringBuilder userInputBuilder = new StringBuilder(userInput);
        TaskParserPlus taskParser = new TaskParserPlus();
        CommandParser commandParser = new CommandParser();
       
        task = taskParser.buildTask(userInputBuilder);  
        Command command = commandParser.getCommand(userInputBuilder.toString());
        
        if (command == null) 
        {
            return TaskFeedBack.FEEDBACK_INVALID;
        }
        
        command.setDataHandler(dataHandler);
        command.setTask(task);        
        command.setUndoHandler(undoHandler);
        System.out.println(task);
        
        return command.execute();
    }   
    
}