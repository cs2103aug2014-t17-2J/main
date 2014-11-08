/**
 * 
 */
package logic.deprecated.taskFieldSetter;

import logic.utility.Task;

//@author A0112887X - unused
/**
 * @author Kuan Tien Long
 *
 */
public class TaskDescriptionFieldSetter implements TaskFieldSetter {

    /*
     * (non-Javadoc)
     * 
     * @see logic.taskParser.taskFieldSetter.TaskFieldSetter#set(logic.Task,
     * java.lang.String)
     */
    @Override
    public String set(Task task, String arguments) {
        // TODO Auto-generated method stub
        task.setDescription(arguments);
        return arguments;
    }

}
