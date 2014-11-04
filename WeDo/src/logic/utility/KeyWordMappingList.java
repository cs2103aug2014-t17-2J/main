/**
 * 
 */
package logic.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import logic.command.commandList.AddCommand;
import logic.command.commandList.Command;
import logic.command.commandList.CompleteCommand;
import logic.command.commandList.DeleteCommand;
import logic.command.commandList.EditCommand;
import logic.command.commandList.ExitCommand;
import logic.command.commandList.RedoCommand;
import logic.command.commandList.SearchCommand;
import logic.command.commandList.UncompleteCommand;
import logic.command.commandList.UndoCommand;
import logic.command.commandList.ViewCommand;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class KeyWordMappingList {

    @SafeVarargs
    private static <T> Multimap<T, String> createMultiMap(
            final Map<T, Collection<String>>... commandMaps) {
        Multimap<T, String> multiMap = ArrayListMultimap.create();

        for (Map<T, Collection<String>> commandMap : commandMaps) {
            for (T key : commandMap.keySet()) {
                multiMap.putAll(key, commandMap.get(key));
            }

        }
        return multiMap;
    }

    private static ArrayList<String> getPermuntation(String priorityWords[],
            String keyWords[]) {
        ArrayList<String> list = new ArrayList<String>();

        for (String priorityWord : priorityWords) {
            for (String keyWord : keyWords) {
                list.add(priorityWord + " " + keyWord);
            }
        }

        for (String keyWord : keyWords) {
            for (String priorityWord : priorityWords) {
                list.add(keyWord + " " + priorityWord);
            }
        }

        return list;

    }

    public static ImmutableMap<Command, Collection<String>> getAddCommandMap() {
        Command addCommand = new AddCommand();
        return ImmutableMap.<Command, Collection<String>> of(addCommand,
                Arrays.asList("add", "new", "create", "cre8"));
    }

    public static ImmutableMap<Command, Collection<String>> getCompleteCommandMap() {
        Command completeCommand = new CompleteCommand();
        return ImmutableMap.<Command, Collection<String>> of(completeCommand,
                Arrays.asList("complete", "done", "finish", "tick"));
    }

    public static ImmutableMap<Command, Collection<String>> getUncompleteCommandMap() {
        Command uncompleteCommand = new UncompleteCommand();
        return ImmutableMap.<Command, Collection<String>> of(uncompleteCommand,
                Arrays.asList("incomplete", "undone", "cancel", "uncomplete",
                        "decomplete", "untick"));
    }

    public static ImmutableMap<Command, Collection<String>> getDeleteCommandMap() {
        Command deleteCommand = new DeleteCommand();
        return ImmutableMap.<Command, Collection<String>> of(deleteCommand,
                Arrays.asList("delete", "d", "del", "delete", "remove", "r",
                        "rm", "remove"));
    }

    public static ImmutableMap<Command, Collection<String>> getExitCommandMap() {
        Command exitCommand = new ExitCommand();
        return ImmutableMap.<Command, Collection<String>> of(exitCommand,
                Arrays.asList("exit", "quit", "quit", "alt qq", "leave",
                        "leave", "bye"));
    }

    public static ImmutableMap<Command, Collection<String>> getSearchCommandMap() {
        Command searchCommand = new SearchCommand();
        return ImmutableMap.<Command, Collection<String>> of(searchCommand,
                Arrays.asList("search", "searching", "s", "find", "f", "found",
                        "finding"));
    }

    public static ImmutableMap<Command, Collection<String>> getEditCommandMap() {
        Command editCommand = new EditCommand();
        return ImmutableMap.<Command, Collection<String>> of(editCommand,
                Arrays.asList("edit", "editing", "e", "modify", "m",
                        "modifying"));
    }

    public static ImmutableMap<Command, Collection<String>> getViewCommandMap() {
        Command viewCommand = new ViewCommand();
        return ImmutableMap.<Command, Collection<String>> of(viewCommand,
                Arrays.asList("view", "viewed", "viewing", "read", "reading",
                        "see", "saw", "get"));
    }

    public static ImmutableMap<Command, Collection<String>> getUndoCommandMap() {
        Command undoCommand = new UndoCommand();
        return ImmutableMap.<Command, Collection<String>> of(undoCommand,
                Arrays.asList("undo"));
    }

    public static ImmutableMap<Command, Collection<String>> getRedoCommandMap() {
        Command redoCommand = new RedoCommand();
        return ImmutableMap.<Command, Collection<String>> of(redoCommand,
                Arrays.asList("redo"));
    }

    /**
     * This function create a map of commands and add them to the multi map. It
     * contains the Command and String pair, where the string is the keyword for
     * command. In future implementation, the multi map will be generated by
     * reading from a file
     */
    public static Multimap<Command, String> getCommandMultiMap() {
        ImmutableMap<Command, Collection<String>> addCommandMap = getAddCommandMap();
        ImmutableMap<Command, Collection<String>> completedCommandMap = getCompleteCommandMap();
        ImmutableMap<Command, Collection<String>> uncompletedCommandMap = getUncompleteCommandMap();
        ImmutableMap<Command, Collection<String>> deleteCommandMap = getDeleteCommandMap();
        ImmutableMap<Command, Collection<String>> exitCommandMap = getExitCommandMap();
        ImmutableMap<Command, Collection<String>> searchCommandMap = getSearchCommandMap();
        ImmutableMap<Command, Collection<String>> editCommandMap = getEditCommandMap();
        ImmutableMap<Command, Collection<String>> viewCommandMap = getViewCommandMap();
        ImmutableMap<Command, Collection<String>> undoCommandMap = getUndoCommandMap();
        ImmutableMap<Command, Collection<String>> redoCommandMap = getRedoCommandMap();

        return createMultiMap(addCommandMap, completedCommandMap,
                uncompletedCommandMap, deleteCommandMap, exitCommandMap,
                searchCommandMap, editCommandMap, viewCommandMap,
                undoCommandMap, redoCommandMap);
    }

    public static ImmutableMap<Priority, Collection<String>> getHighPriority() {
        final String PRIORITY_WORD[] = { "priority", "pri" };
        final String KEYWORDS_FOR_HIGH[] = { "high", "urgent", "top",
                "crucial", "important" };

        return ImmutableMap.<Priority, Collection<String>> of(
                Priority.PRIORITY_HIGH,
                getPermuntation(PRIORITY_WORD, KEYWORDS_FOR_HIGH));
    }

    public static ImmutableMap<Priority, Collection<String>> getMediumPriority() {
        final String PRIORITY_WORD[] = { "priority", "pri" };
        final String KEYWORDS_FOR_MEDIUM[] = { "medium", "med", "normal",
                "neutral", "moderate" };

        return ImmutableMap.<Priority, Collection<String>> of(
                Priority.PRIORITY_MEDIUM,
                getPermuntation(PRIORITY_WORD, KEYWORDS_FOR_MEDIUM));
    }

    public static ImmutableMap<Priority, Collection<String>> getLowPriority() {
        final String PRIORITY_WORD[] = { "priority", "pri" };
        final String KEYWORDS_FOR_LOW[] = { "low", "none", "never", "no",
                "ignore" };

        return ImmutableMap.<Priority, Collection<String>> of(
                Priority.PRIORITY_LOW,
                getPermuntation(PRIORITY_WORD, KEYWORDS_FOR_LOW));
    }

    /**
     * This function create a map of commands and add them to the multi map. It
     * contains the Priority and String pair, where the string is the keyword for
     * command. In future implementation, the multi map will be generated by
     * reading from a file
     */
    public static Multimap<Priority, String> getPriorityMultiMap() {
        ImmutableMap<Priority, Collection<String>> highPriorityMap = getHighPriority();
        ImmutableMap<Priority, Collection<String>> mediumPriorityMap = getMediumPriority();
        ImmutableMap<Priority, Collection<String>> lowPriorityMap = getLowPriority();
        return createMultiMap(highPriorityMap, mediumPriorityMap, lowPriorityMap);
    }

}
