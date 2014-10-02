// @author A0112636M
package userInterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import userInterface.Action;

/**
 * The Keywords class contains all command identifier constants that the
 * application uses. Any processing of command identifier should go through this
 * class and make use of its methods.
 */
public final class Keywords {

	private static final String ADD_TASK_IDENTIFIER_1 = "add";

	private static final String UPDATE_TASK_IDENTIFIER_1 = "update";

	private static final String DELETE_TASK_IDENTIFIER_1 = "delete";
	
	private static final String READ_TASK_IDENTIFIER_1 = "read";

	private static final String DATE_IDENTIFIER = ".on";
	private static final String DUE_DATE_IDENTIFIER = ".by";
	private static final String TIME_IDENTIFIER = ".at";
	private static final String SEARCH_FINISHED_IDENTIFIER = ".done";
	private static final String UNDO_ACTION_IDENTIFIER = "undo";

	private static final Map<String, Action> actionMap = createMap();

	private static Map<String, Action> createMap() {

		Map<String, Action> map = new HashMap<String, Action>();

		map.put(ADD_TASK_IDENTIFIER_1, Action.ADD);
		//map.put(ADD_TASK_IDENTIFIER_2, Action.ADD);
		//map.put(ADD_TASK_IDENTIFIER_3, Action.ADD);

		map.put(UPDATE_TASK_IDENTIFIER_1, Action.UPDATE);
		//map.put(UPDATE_TASK_IDENTIFIER_2, Action.UPDATE);
		//map.put(UPDATE_TASK_IDENTIFIER_3, Action.UPDATE);

		map.put(DELETE_TASK_IDENTIFIER_1, Action.DELETE);
		//map.put(DELETE_TASK_IDENTIFIER_2, Action.DELETE);
		//map.put(DELETE_TASK_IDENTIFIER_3, Action.DELETE);
		
		map.put(READ_TASK_IDENTIFIER_1, Action.READ);
		//map.put(READ_TASK_IDENTIFIER_2, Action.READ);
		//map.put(READ_TASK_IDENTIFIER_3, Action.READ);


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

	public static String getUpdateTaskIdentifier() {
		return UPDATE_TASK_IDENTIFIER_1;
	}

	public static String getDeleteTaskIdentifier() {
		return DELETE_TASK_IDENTIFIER_1;
	}

	public static String getListTaskIdentifier() {
		return READ_TASK_IDENTIFIER_1;
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
	
	public static String getSearchForFinishedIdentifier() {
		return SEARCH_FINISHED_IDENTIFIER;
	}

}
