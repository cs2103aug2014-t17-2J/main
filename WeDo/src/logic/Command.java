/**
 * 
 */
package logic;

import definedEnumeration.TaskFeedBack;

public interface Command {
    /**
     * This method execute the commands such as add, display, clear etc.
     * @param task TODO
     * @return TaskFeedBack to continue or exit
     */
    TaskFeedBack execute(Task task);
}

