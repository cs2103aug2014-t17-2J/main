/**
 * 
 */
package logic.commandList;

import logic.utility.StringHandler;
import definedEnumeration.TaskFeedBack;



/**
 * @author TienLong This class makes use of the Command interface to implement
 *         execute function for edit
 */
public class EditCommand extends Command {

    public TaskFeedBack execute() {
        final int ARRAY_OFFSET = -1;

        System.out.println("Editing");
        String indexString = StringHandler.getIntegerFromFirstSlot(task
                .getDescription());
        if (indexString == null)
            return TaskFeedBack.FEEDBACK_INVALID;

        int index = StringHandler.parseStringToInteger(indexString)
                + ARRAY_OFFSET;
        task.setDescription(StringHandler.removeFirstMatched(
                task.getDescription(), indexString));
        dataHandler.editTask(index, task);

        return TaskFeedBack.FEEDBACK_VALID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.Command#undo()
     */
    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }
}
