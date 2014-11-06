package definedEnumeration;


/**
 * @author A0112887X
 *
 */
public enum Priority {
    PRIORITY_HIGH ("High"), 
    PRIORITY_MEDIUM ("Medium"), 
    PRIORITY_LOW ("Low"), 
    PRIORITY_UNDEFINED ("");
    
    private final String priorityLevel;
    
    private Priority(String priorityLevel)
    {
        this.priorityLevel = priorityLevel;
    }
    
    public String toString()
    {
         return priorityLevel;
    }
    
    
}


