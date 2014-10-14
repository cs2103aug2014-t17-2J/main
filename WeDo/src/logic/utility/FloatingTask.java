/**
 * 
 */
package logic.utility;

import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
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
}
