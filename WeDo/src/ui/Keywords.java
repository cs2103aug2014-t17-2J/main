// @Andy Hsu Wei Qiang
package ui;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ui.Action;

/**
 * The Keywords class contains all command identifier constants that the
 * application uses. Any processing of command identifier should go through this
 * class and make use of its methods.
 */
public final class Keywords {

	private static final String ADD_TASK_IDENTIFIER_1 = "add";
	private static final String ADD_TASK_IDENTIFIER_2 = "create";
	private static final String ADD_TASK_IDENTIFIER_3 = "cre8";
	
	private static final String VIEW_TASK_IDENTIFIER_1 = "view";
	private static final String VIEW_TASK_IDENTIFIER_2 = "read";

	private static final String EDIT_TASK_IDENTIFIER_1 = "edit";
	private static final String EDIT_TASK_IDENTIFIER_2 = "modify";
	private static final String EDIT_TASK_IDENTIFIER_3 = "update";

	private static final String DELETE_TASK_IDENTIFIER_1 = "delete";
	private static final String DELETE_TASK_IDENTIFIER_2 = "remove";
	private static final String DELETE_TASK_IDENTIFIER_3 = "cancel";

	private static final String DATE_IDENTIFIER = "on";
	private static final String DUE_DATE_IDENTIFIER = "by";
	private static final String TIME_IDENTIFIER = "at";

	private static final String UNDO_ACTION_IDENTIFIER = "undo";

	private static final Map<String, Action> actionMap = createMap();

	private static Map<String, Action> createMap() {

		Map<String, Action> map = new HashMap<String, Action>();

		map.put(ADD_TASK_IDENTIFIER_1, Action.ADD);
		map.put(ADD_TASK_IDENTIFIER_2, Action.ADD);
		map.put(ADD_TASK_IDENTIFIER_3, Action.ADD);
		
		map.put(VIEW_TASK_IDENTIFIER_1, Action.VIEW);
		map.put(VIEW_TASK_IDENTIFIER_2, Action.VIEW);

		map.put(EDIT_TASK_IDENTIFIER_1, Action.EDIT);
		map.put(EDIT_TASK_IDENTIFIER_2, Action.EDIT);
		map.put(EDIT_TASK_IDENTIFIER_3, Action.EDIT);

		map.put(DELETE_TASK_IDENTIFIER_1, Action.DELETE);
		map.put(DELETE_TASK_IDENTIFIER_2, Action.DELETE);
		map.put(DELETE_TASK_IDENTIFIER_3, Action.DELETE);

		map.put(UNDO_ACTION_IDENTIFIER, Action.UNDO);

		return Collections.unmodifiableMap(map);
	}

	/**
	 * This is used when you need to resolve a string into an Action
	 * enumeration.
	 * 
	 * @param identifier - a String object
	 * @return an Action enumeration that corresponds with the specified
	 *         identifier string
	 */
	public static Action resolveActionIdentifier(String identifier) {

		identifier = identifier.toLowerCase();

		if (!actionMap.containsKey(identifier)) {
			return Action.INVALID;
		}

		return actionMap.get(identifier);
	}

	public static String getAddTaskIdentifier() {
		return ADD_TASK_IDENTIFIER_1;
	}
	
	public static String getViewTaskIdentifier() {
		return VIEW_TASK_IDENTIFIER_1;
	}

	public static String getEditTaskIdentifier() {
		return EDIT_TASK_IDENTIFIER_1;
	}

	public static String getDeleteTaskIdentifier() {
		return DELETE_TASK_IDENTIFIER_1;
	}
	
	public static String getDateIdentifier() {
		return DATE_IDENTIFIER;
	}

	public static String getDueDateIdentifier() {
		return DUE_DATE_IDENTIFIER;
	}

	public static String getTimeIdentifier() {
		return TIME_IDENTIFIER;
	}
}
