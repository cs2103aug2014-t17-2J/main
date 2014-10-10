/**
 * 
 */
package logic.commandList;

import logic.Task;
import logic.utility.StringHandler;
import definedEnumeration.TaskFeedBack;

/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for edit
 */
public class EditCommand extends Command {

    private static final int NOT_SET = -1;

    private int index = NOT_SET;
    private Task source;

    public TaskFeedBack execute() {

        System.out.println("Editing");

        if (index == NOT_SET) {
            String indexString = StringHandler.getIntegerFromFirstSlot(task
                    .getDescription());

            if (indexString == null) {
                return TaskFeedBack.FEEDBACK_INVALID;
            }

            index = getIndex(indexString);

            if (!dataHandler.indexValid(index)) {
                return TaskFeedBack.FEEDBACK_INVALID;
            }

            task.setDescription(StringHandler.removeFirstMatched(
                    task.getDescription(), indexString));

            source = dataHandler.getTask(index);

        }

        if (!dataHandler.indexValid(index)) {
            return TaskFeedBack.FEEDBACK_INVALID;
        }
            dataHandler.editTask(source, task);
            undoHandler.add(this);
        
        return TaskFeedBack.FEEDBACK_VALID;
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
}
