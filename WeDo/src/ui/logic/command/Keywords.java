package ui.logic.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import logic.command.commandList.Command;
import logic.utility.KeyWordMappingList;

import com.google.common.collect.ImmutableMap;

/**
 // @author A0112636M 
  * The Keywords class contains all command identifier 
  * constants that the application uses. Any processing of command identifier should 
  * go through this class and make use of its methods.
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
	
	private static final String SEARCH_TASK_IDENTIFIER_1 = "search";
	private static final String SEARCH_TASK_IDENTIFIER_2 = "locate";
	private static final String SEARCH_TASK_IDENTIFIER_3 = "find";

	private static final String DATE_IDENTIFIER = "on";
	private static final String DUE_DATE_IDENTIFIER = "by";
	private static final String TIME_IDENTIFIER = "at";

	private static final String UNDO_ACTION_IDENTIFIER = "undo";
	private static final String REDO_ACTION_IDENTIFIER = "redo";

	private static final Map<String, Action> actionMap = createMap();

	private static Map<String, Action> createMap() {

		Map<String, Action> map = new HashMap<String, Action>();
		ImmutableMap<Command, Collection<String>> addCommandMap = KeyWordMappingList.getAddCommandMap();
        ImmutableMap<Command, Collection<String>> viewCommandMap = KeyWordMappingList.getViewCommandMap();
        ImmutableMap<Command, Collection<String>> editCommandMap = KeyWordMappingList.getEditCommandMap();
        ImmutableMap<Command, Collection<String>> deleteCommandMap = KeyWordMappingList.getDeleteCommandMap();
        ImmutableMap<Command, Collection<String>> searchCommandMap = KeyWordMappingList.getSearchCommandMap();
        ImmutableMap<Command, Collection<String>> undoCommandMap = KeyWordMappingList.getUndoCommandMap();
        ImmutableMap<Command, Collection<String>> redoCommandMap = KeyWordMappingList.getRedoCommandMap();
        ImmutableMap<Command, Collection<String>> completedCommandMap = KeyWordMappingList.getCompleteCommandMap();
        ImmutableMap<Command, Collection<String>> uncompletedCommandMap = KeyWordMappingList.getUncompleteCommandMap();
        ImmutableMap<Command, Collection<String>> exitCommandMap =KeyWordMappingList. getExitCommandMap();
        
		addToMapCommandAdd(map, addCommandMap, Action.ADD);
		addToMapCommandView(map, viewCommandMap, Action.VIEW);
		addToMapCommandEdit(map, editCommandMap, Action.EDIT);
		addToMapCommandDelete(map, deleteCommandMap, Action.DELETE);
		addToMapCommandSearch(map, searchCommandMap, Action.SEARCH);
		addToMapCommandUndo(map, undoCommandMap, Action.UNDO);
		addToMapCommandRedo(map, redoCommandMap, Action.REDO);
		addToMapCommandCompleted(map, completedCommandMap, Action.COMPLETED);
		addToMapCommandUncompleted(map, uncompletedCommandMap, Action.UNCOMPLETED);
		addToMapCommandExit(map, exitCommandMap, Action.EXIT);

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
		
		map.put(SEARCH_TASK_IDENTIFIER_1, Action.SEARCH);
		map.put(SEARCH_TASK_IDENTIFIER_2, Action.SEARCH);
		map.put(SEARCH_TASK_IDENTIFIER_3, Action.SEARCH);

		map.put(UNDO_ACTION_IDENTIFIER, Action.UNDO);
		map.put(REDO_ACTION_IDENTIFIER, Action.REDO);

		return Collections.unmodifiableMap(map);
	}

	/**
	 * @param map Current Map
	 * @param addCommandMap Additional map for Add Command 
	 * @param action Add command action
	 */
	private static void addToMapCommandAdd(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> addCommandMap, Action action) {
		for(Command key : addCommandMap.keySet()) {
			for (String keyWord : addCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
	}
	
	private static void addToMapCommandView(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> viewCommandMap, Action action) {
		for(Command key : viewCommandMap.keySet()) {
			for (String keyWord : viewCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
	}
	
	private static void addToMapCommandEdit(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> editCommandMap, Action action) {
		for(Command key : editCommandMap.keySet()) {
			for (String keyWord : editCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
	}
	
	private static void addToMapCommandDelete(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> deleteCommandMap, Action action) {
		for(Command key : deleteCommandMap.keySet()) {
			for (String keyWord : deleteCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
	}
	
	private static void addToMapCommandCompleted(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> completedCommandMap, Action action) {
		for(Command key : completedCommandMap.keySet()) {
			for (String keyWord : completedCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
	}
	
	private static void addToMapCommandUncompleted(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> uncompletedCommandMap, Action action) {
		for(Command key : uncompletedCommandMap.keySet()) {
			for (String keyWord : uncompletedCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
	}
	
	private static void addToMapCommandRedo(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> redoCommandMap, Action action) {
		for(Command key : redoCommandMap.keySet()) {
			for (String keyWord : redoCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
	}
	
	private static void addToMapCommandExit(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> exitCommandMap, Action action) {
		for(Command key : exitCommandMap.keySet()) {
			for (String keyWord : exitCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
	}
	
	private static void addToMapCommandUndo(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> undoCommandMap, Action action) {
		for(Command key : undoCommandMap.keySet()) {
			for (String keyWord : undoCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
	}
	
	private static void addToMapCommandSearch(Map<String, Action> map,
			ImmutableMap<Command, Collection<String>> searchCommandMap, Action action) {
		for(Command key : searchCommandMap.keySet()) {
			for (String keyWord : searchCommandMap.get(key)) {
				map.put(keyWord, action);
			}
		}
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
	
	public static String getSearchTaskIdentifier() {
		return SEARCH_TASK_IDENTIFIER_1;
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
	
	public static String getUndoActionIdentifier() {
		return UNDO_ACTION_IDENTIFIER;
	}
	
	public static String getRedoActionIdentifier() {
		return REDO_ACTION_IDENTIFIER;
	}
}
