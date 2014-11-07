package ui.logic.command;

/**
 // @author A0112636M
 * This class create the Action emueration which specify
 * the command action the user entered. 
 * 
 */
public enum Action {
	
	ADD                 (true, true),
	VIEW                (false, false),
	EDIT                (true, true),
	DELETE              (true, true),
	SEARCH				(false, false),
	UNDO                (false, true),
	REDO				(false, true),
	COMPLETED			(true, false),
	UNCOMPLETED         (false, true),
	EXIT   				(true, false),
	INVALID             (false, false);
	
	private final boolean isReversible;
	private final boolean isPersistable;
	
	private Action(boolean isReversible, boolean isPersistable) {
		this.isReversible = isReversible;
		this.isPersistable = isPersistable;
	}
	
	/**
	 * Returns true if the effect of this action can be reversed.
	 * 
	 * @return	true if the effect of this action can be reversed
	 */
	public boolean isReversible() {
		return isReversible;
	}
	
	/**
	 * Returns true if the effect of this action should be persisted.
	 * 
	 * @return	true if the effect of this action should be persisted
	 */
	public boolean isPersistable() {
		return isPersistable;
	}

}
