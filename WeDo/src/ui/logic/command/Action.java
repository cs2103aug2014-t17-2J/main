package ui.logic.command;

/**
 * @author Andy Hsu Wei Qiang 
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
