/**
 * 
 */
package logic.deprecated;

import definedEnumeration.Priority;

//@author A0112887X - unused
/**
 *
 */
public class FloatingTask extends AbstractTask {

    /**
     * @param priority
     * @param description
     */
    public FloatingTask(Priority priority, String description) {
        super(priority, description);
        // TODO Auto-generated constructor stub
    }

    public FloatingTask(Priority priority, String description,
            boolean isComplete) {
        super(priority, description, isComplete);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FloatingTask [getPriority()=" + getPriority()
                + ", getDescription()=" + getDescription() + ", isCompleted()="
                + isCompleted() + "]";
    }
}