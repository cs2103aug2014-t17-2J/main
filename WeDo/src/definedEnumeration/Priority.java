package definedEnumeration;

//@author A0112887X
/**
 * Provides the priority level for task
 */
public enum Priority {
    PRIORITY_HIGH("High"), PRIORITY_MEDIUM("Medium"), PRIORITY_LOW("Low"), PRIORITY_UNDEFINED(
            "");

    private final String priorityLevel;

    /**
     * Set the string for the corresponding priority level
     * @param priorityLevel the priority level to set
     */
    private Priority(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String toString() {
        return priorityLevel;
    }

}
