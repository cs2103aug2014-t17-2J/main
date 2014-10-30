/**
 * 
 */
package logic.command.commandList;

import java.lang.reflect.Field;
import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.StringHandler;
import logic.utility.Task;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for edit
 */
public class EditCommand extends Command {

    private static final int NOT_SET = -1;

    private int index = NOT_SET;
    private Task source;

    public void execute() throws InvalidCommandException {

        System.out.println("Editing");

        if (index == NOT_SET) {
            String indexString = StringHandler.getIntegerFromFirstSlot(task
                    .getDescription());

            if (indexString == null) {
            }

            index = getIndex(indexString);

            if (!dataHandler.indexValid(index)) {
                throw new InvalidCommandException("Edit failed, invalid index");
            }

            task.setDescription(StringHandler.removeFirstMatched(
                    task.getDescription(), indexString));

            source = dataHandler.getTask(index);

        }

        if (!dataHandler.indexValid(index)) {
            throw new InvalidCommandException("Edit failed, invalid index");
        }
        
            task = editSpecifiedField(source, task); 
            
            dataHandler.editTask(source, task);
            undoHandler.addUndo(this);
        
    }
    
    
    /**
     * Edit the source task based on specified field that the user enter
     * @param source the task that the user wanted to edit
     * @param toEditTask the task that the user enter
     * @return editedTask based on what user had specified
     */
    private Task editSpecifiedField(Task source, Task toEditTask)
    {
        Task editedTask = new Task();
        
        setDescriptionBasedOnSpecified(source, toEditTask, editedTask);
        
        setPriorityBasedOnSpecified(source, toEditTask, editedTask);

        setDateTimeBasedOnSpecified(source, toEditTask, editedTask);
        
        
        return editedTask;
    }

    private void setDateTimeBasedOnSpecified(Task source, Task toEditTask,
            Task editedTask) {
        if(toEditTask.getEndDate() == null || toEditTask.getEndDate() == Task.DATE_NOT_SET)
        {
            editedTask.setEndDate(source.getEndDate());
        }
        else
        {
            editedTask.setEndDate(toEditTask.getEndDate());
        }
        
        if(toEditTask.getStartDate() == null || toEditTask.getStartDate() == Task.DATE_NOT_SET)
        {
            editedTask.setStartDate(source.getStartDate());
        }
        else
        {
            editedTask.setStartDate(toEditTask.getStartDate());
        }
        
        if(toEditTask.getStartTime() == null || toEditTask.getStartTime() == Task.TIME_NOT_SET)
        {
            editedTask.setStartTime(source.getStartTime());
        }
        else
        {
            editedTask.setStartTime(toEditTask.getStartTime());
        }
        
        if(toEditTask.getEndTime() == null || toEditTask.getEndTime() == Task.TIME_NOT_SET)
        {
            editedTask.setEndTime(source.getEndTime());
        }
        else
        {
            editedTask.setEndTime(toEditTask.getEndTime());
        }
    }

    private void setPriorityBasedOnSpecified(Task source, Task toEditTask,
            Task editedTask) {
        if(toEditTask.getPriority() == null || toEditTask.getPriority() == Task.PRIORITY_NOT_SET)
        {
            editedTask.setPriority(source.getPriority());
        }
        else
        {
            editedTask.setPriority(toEditTask.getPriority());
        }
    }

    private void setDescriptionBasedOnSpecified(Task source, Task toEditTask,
            Task editedTask) {
        if(toEditTask.getDescription() == null || toEditTask.getDescription().isEmpty())
        {
            editedTask.setDescription(source.getDescription());
        }
        else
        {
            editedTask.setDescription(toEditTask.getDescription());
        }
    }

    private int getIndex(String indexString) {
        final int ARRAY_OFFSET = -1;
        return StringHandler.parseStringToInteger(indexString) + ARRAY_OFFSET;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        dataHandler.editTask(task, source);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#validate(java.util.EnumSet)
     */
    @Override
    public boolean validate(EnumSet<ParserFlags> parseFlags) {
        final int MAX_VALID_FLAG = 1;
        return parseFlags.size() > MAX_VALID_FLAG;
    }
//
//    
//    /**
//     * <p>
//     * Determine whether the parse occurred was valid by matching it with
//     * VALID_PARSE which contains compulsory parse result(s) for add command required
//     * <p>
//     * 
//     * @param parseFlags the set of ParserFlag to be tested
//     * @return if it contains all of the VALID_PARSE flag
//     */
//    public  boolean isCommandValid(EnumSet<ParserFlags> parseFlags) {
//        
//        final EnumSet<ParserFlags> VALID_PARSE = EnumSet.of(
//                ParserFlags.DESCRIPTION_FLAG, ParserFlags.COMMAND_FLAG);
//   
//        
//        if (parseFlags.containsAll(VALID_PARSE)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    
    /* (non-Javadoc)
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
            return "Edit";
    }
}
