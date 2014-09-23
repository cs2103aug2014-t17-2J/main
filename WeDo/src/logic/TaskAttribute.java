/**
 * 
 */
package logic;

/**
 * @author Kuan Tien Long
 *
 */
public interface TaskAttribute 
{
    void set(Task task, String arguments);
}



class TaskPriorityAttribute implements TaskAttribute
{

    /* (non-Javadoc)
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {
        // TODO Auto-generated method stub
        task.setTestPriority(arguments);
        
    }
    
}

class TaskDateAttribute implements TaskAttribute
{

    /* (non-Javadoc)
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) 
    {
        task.setTestDate(arguments);
        // TODO Auto-generated method stub
        
    }
    
}

class TaskDescriptionAttribute implements TaskAttribute
{

    /* (non-Javadoc)
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {
        // TODO Auto-generated method stub
        task.setDescription(arguments);
    }
    
}

class TaskInvalidAttribute implements TaskAttribute
{

    /* (non-Javadoc)
     * @see logic.TaskAttribute#set()
     */
    @Override
    public void set(Task task, String arguments) {
        // TODO Auto-generated method stub
        
    }
    
}