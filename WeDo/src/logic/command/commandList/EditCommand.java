/**
 * 
 */
package logic.command.commandList;

import java.util.EnumSet;

import logic.exception.InvalidCommandException;
import logic.parser.ParserFlags;
import logic.utility.StringHandler;
import logic.utility.Task;

//@author A0112887X
/**
 * TienLong This class makes use of the Command interface to implement execute
 * function for edit
 */
public class EditCommand extends Command {

    private static final int NOT_SET = -1;

    private int index = NOT_SET;
    private Task source;

    /* (non-Javadoc)
     * @see logic.command.commandList.Command#execute()
     */
    public void execute() throws InvalidCommandException {

        final String ERROR_MESSAGE = "Edit failed, invalid index";
        if (index == NOT_SET) {
            String indexString = StringHandler.getIntegerFromFirstSlot(task
                    .getDescription());

            if (indexString == null) {
            }

            index = getIndex(indexString);

            if (!dataHandler.indexValid(index)) {
                throw new InvalidCommandException(ERROR_MESSAGE);
            }

            task.setDescription(StringHandler.removeFirstMatched(
                    task.getDescription(), indexString));

            source = dataHandler.getTask(index);

        }

        if (!dataHandler.indexValid(index)) {
            throw new InvalidCommandException(ERROR_MESSAGE);
        }

        task = editSpecifiedField(source, task);

        dataHandler.editTask(source, task);
        undoHandler.addUndo(this);

    }

    /**
     * Edit the source task based on specified field that the user enter
     * 
     * @param source
     *            the task that the user wanted to edit
     * @param toEditTask
     *            the task that the user enter
     * @return editedTask based on what user had specified
     */
    private Task editSpecifiedField(Task source, Task toEditTask) {
        Task editedTask = new Task();

        setDescriptionBasedOnSpecified(source, toEditTask, editedTask);

        setPriorityBasedOnSpecified(source, toEditTask, editedTask);

        setDateTimeBasedOnSpecified(source, toEditTask, editedTask);

        setCompleteStatus(source, editedTask);

        return editedTask;
    }

    /**
     * Set complete status for the task
     * @param source from the task that will be edited
     * @param editedTask the task that contains what to edit
     */
    private void setCompleteStatus(Task source, Task editedTask) {
        editedTask.setCompleted(source.getCompleted());
    }

    /**
     * Set date for the task
     * @param source from the task that will be edited
     * @param editedTask the task that contains what to edit
     */
    private void setDateTimeBasedOnSpecified(Task source, Task toEditTask,
            Task editedTask) {
        if (toEditTask.getEndDate() == null
                || toEditTask.getEndDate() == Task.DATE_NOT_SET) {
            editedTask.setEndDate(source.getEndDate());
        } else {
            editedTask.setEndDate(toEditTask.getEndDate());
        }

        if (toEditTask.getStartDate() == null
                || toEditTask.getStartDate() == Task.DATE_NOT_SET) {
            editedTask.setStartDate(source.getStartDate());
        } else {
            editedTask.setStartDate(toEditTask.getStartDate());
        }

        if (toEditTask.getStartTime() == null
                || toEditTask.getStartTime() == Task.TIME_NOT_SET) {
            editedTask.setStartTime(source.getStartTime());
        } else {
            editedTask.setStartTime(toEditTask.getStartTime());
        }

        if (toEditTask.getEndTime() == null
                || toEditTask.getEndTime() == Task.TIME_NOT_SET) {
            editedTask.setEndTime(source.getEndTime());
        } else {
            editedTask.setEndTime(toEditTask.getEndTime());
        }
    }

    /**
     * Set priority status for the task
     * @param source from the task that will be edited
     * @param editedTask the task that contains what to edit
     */
    private void setPriorityBasedOnSpecified(Task source, Task toEditTask,
            Task editedTask) {
        if (toEditTask.getPriority() == null
                || toEditTask.getPriority() == Task.PRIORITY_NOT_SET) {
            editedTask.setPriority(source.getPriority());
        } else {
            editedTask.setPriority(toEditTask.getPriority());
        }
    }

    /**
     * Set description for the task
     * @param source from the task that will be edited
     * @param editedTask the task that contains what to edit
     */
    private void setDescriptionBasedOnSpecified(Task source, Task toEditTask,
            Task editedTask) {
        if (toEditTask.getDescription() == null
                || toEditTask.getDescription().isEmpty()) {
            editedTask.setDescription(source.getDescription());
        } else {
            editedTask.setDescription(toEditTask.getDescription());
        }
    }

    /**
     * Parse integer from the string
     * @param indexString the string which contains the index
     * @return the integer parsed from the string
     */
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


    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#toString()
     */
    @Override
    public String toString() {
        final String COMMAND_NAME = "Edit";
        return COMMAND_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.command.commandList.Command#getValidateErrorMessage()
     */
    @Override
    public String getValidateErrorMessage() {
        final String ERROR_MESSAGE = "Format of edit should consist at least 1 field to edit";
        return ERROR_MESSAGE;
    }
}
