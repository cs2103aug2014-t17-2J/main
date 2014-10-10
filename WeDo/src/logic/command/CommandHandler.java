package logic.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;












import logic.command.commandList.AddCommand;
import logic.command.commandList.ClearCommand;
import logic.command.commandList.Command;
import logic.command.commandList.DeleteCommand;
import logic.command.commandList.EditCommand;
import logic.command.commandList.ExitCommand;
import logic.command.commandList.RedoCommand;
import logic.command.commandList.SearchCommand;
import logic.command.commandList.UndoCommand;
import logic.command.commandList.ViewCommand;
import logic.taskParser.TaskParserPlus;
import logic.utility.KeyMatcher;
import logic.utility.StringHandler;
import logic.utility.Task;

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