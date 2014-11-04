/**
 * 
 */
package logic.command.commandList;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.StringHandler;
import logic.utility.Task;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for ClearTask
 */
public class UncompleteCommand extends Command {
    ArrayList<Task> editTaskList = new ArrayList<Task>();

    public void execute() throws InvalidCommandException {

        System.out.println("incomplete");

        final int ARRAY_OFFSET = 1;
        final String NO_INTEGER_EXTRACTED = "No integer extracted";
        final String INVALID_LOWEST_INDEX = "Invalid lowest index";
        final String INVALID_HIGHEST_INDEX = "Invalid highest index";

        TreeSet<Integer> digitList = extractAllDigits(task.getDescription());

        if (digitList.isEmpty()) {
            throw new InvalidCommandException(NO_INTEGER_EXTRACTED);
        }

        if (!dataHandler.indexValid(digitList.first() - ARRAY_OFFSET)) {
            throw new InvalidCommandException(INVALID_LOWEST_INDEX);
        }

        if (!dataHandler.indexValid(digitList.last() - ARRAY_OFFSET)) {
            throw new InvalidCommandException(INVALID_HIGHEST_INDEX);
        }

        editTaskList = getTasksToComplete(ARRAY_OFFSET, digitList);
        inCompleteTask(editTaskList);

        undoHandler.addUndo(this);

    }

    /**
     * Incomplete all the task at completeTaskList
     * @param editTaskList the list of task to delete
     */
    private void inCompleteTask(ArrayList<Task> editTaskList) 
    {
        final boolean COMPLETE = false;
    
        for (Task task : editTaskList) 
        {
                dataHandler.setCompleteTask(task, COMPLETE);
        }
    }

    /**
     * Get all task to be completed
     * @param ARRAY_OFFSET the offset for array
     * @param digitList which contains of all the index to delete
     * @return the list of task to delete
     */
    private ArrayList<Task> getTasksToComplete(final int ARRAY_OFFSET,
            TreeSet<Integer> digitList) {

        
        ArrayList<Task> completeTaskList = new ArrayList<Task>();

        for (int index : digitList) {
            Task taskToDelete = dataHandler.getTask(index - ARRAY_OFFSET);
            completeTaskList.add(taskToDelete);
        }

        return completeTaskList;
    }

    /**
     * Extract all digits from the string
     * 
     * @param source
     *            the string which contains the digits
     * @return TreeSet<Integer> which contains all the digits extracted
     */
    private TreeSet<Integer> extractAllDigits(String source) {

        TreeSet<Integer> extractedDigitList = new TreeSet<Integer>();

        String regex = "(-{0,1}[0-9]+)\\s*(?:to|-)\\s*(-{0,1}[0-9]+)|(-{0,1}[0-9]+)";
        final int START_RANGE_GROUP = 1;
        final int END_RANGE_GROUP = 2;
        final int INDIVIDUAL_DIGIT_GROUP = 3;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);

        while (matcher.find()) {
            if (matcher.group(START_RANGE_GROUP) != null
                    && matcher.group(END_RANGE_GROUP) != null) {
                int startRange = StringHandler.parseStringToInteger(matcher
                        .group(START_RANGE_GROUP));
                int endRange = StringHandler.parseStringToInteger(matcher
                        .group(END_RANGE_GROUP));
                for (int index = startRange; index <= endRange; index++) {
                    extractedDigitList.add(index);
                }
            }

            if (matcher.group(INDIVIDUAL_DIGIT_GROUP) != null) {
                int index = StringHandler.parseStringToInteger(matcher
                        .group(INDIVIDUAL_DIGIT_GROUP));
                extractedDigitList.add(index);
            }
        }

        return extractedDigitList;

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() 
    {
        setComplete(editTaskList);
        
    }

    
    
    /**
     * Add the previously removed task back
     * @param undoTaskList the list of task that was deleted
     */
    private void setComplete(ArrayList<Task> undoTaskList) 
    {
        final boolean COMPLETE = true;
        
        for (Task task : editTaskList) 
        {
                dataHandler.setCompleteTask(task, COMPLETE);
        }
    }

    
    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#validate(java.util.EnumSet)
     */
    @Override
    public boolean validate(EnumSet<ParserFlags> parseFlags) {

        final EnumSet<ParserFlags> VALID_COMPLETE_PARSE = EnumSet.of(
                ParserFlags.DESCRIPTION_FLAG, ParserFlags.COMMAND_FLAG);
   
        
        return parseFlags.containsAll(VALID_COMPLETE_PARSE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
        return "Completed";
    }

}