/**
 * 
 */
package logic.utility;

import java.time.LocalDate;
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
import logic.command.commandList.IncompleteCommand;
import logic.command.commandList.UndoCommand;
import logic.command.commandList.ViewCommand;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import definedEnumeration.Priority;

//@author A0112887X
/**
 *
 */
public class KeyWordMappingList {

    /**
     * Create multimap based on <T>
     * 
     * @param commandMaps
     *            which consist of keywords mapping
     * @return multimap with key = <T>, value = key word
     */
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

    /**
     * Get all permutation based on priority words and key words
     * 
     * @param priorityWords
     *            the words for priority
     * @param keyWords
     *            the words for keywords
     * @return ArrayList containing the permutation
     */
    private static ArrayList<String> getPermutation(String priorityWords[],
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

    /**
     * @return ImmutableMap containing keywords for add
     */
    public static ImmutableMap<Command, Collection<String>> getAddCommandMap() {
        Command addCommand = new AddCommand();
        return ImmutableMap.<Command, Collection<String>> of(addCommand,
                Arrays.asList("add", "new", "create", "cre8"));
    }

    /**
     * @return ImmutableMap containing keywords for complete
     */
    public static ImmutableMap<Command, Collection<String>> getCompleteCommandMap() {
        Command completeCommand = new CompleteCommand();
        return ImmutableMap.<Command, Collection<String>> of(completeCommand,
                Arrays.asList("complete", "done", "finish", "tick"));
    }

    /**
     * @return ImmutableMap containing keywords for incomplete
     */
    public static ImmutableMap<Command, Collection<String>> getIncompleteCommandMap() {
        Command uncompleteCommand = new IncompleteCommand();
        return ImmutableMap.<Command, Collection<String>> of(uncompleteCommand,
                Arrays.asList("incomplete", "undone", "cancel", "uncomplete",
                        "untick"));
    }

    /**
     * @return ImmutableMap containing keywords for delete
     */
    public static ImmutableMap<Command, Collection<String>> getDeleteCommandMap() {
        Command deleteCommand = new DeleteCommand();
        return ImmutableMap.<Command, Collection<String>> of(deleteCommand,
                Arrays.asList("delete", "del", "remove"));
    }

    /**
     * @return ImmutableMap containing keywords for exit
     */
    public static ImmutableMap<Command, Collection<String>> getExitCommandMap() {
        Command exitCommand = new ExitCommand();
        return ImmutableMap.<Command, Collection<String>> of(exitCommand,
                Arrays.asList("exit", "quit"));
    }

    /**
     * @return ImmutableMap containing keywords for search
     */
    public static ImmutableMap<Command, Collection<String>> getSearchCommandMap() {
        Command searchCommand = new SearchCommand();
        return ImmutableMap.<Command, Collection<String>> of(searchCommand,
                Arrays.asList("search", "find", "locate"));
    }

    /**
     * @return ImmutableMap containing keywords for edit
     */
    public static ImmutableMap<Command, Collection<String>> getEditCommandMap() {
        Command editCommand = new EditCommand();
        return ImmutableMap.<Command, Collection<String>> of(editCommand,
                Arrays.asList("edit", "modify", "change"));
    }

    /**
     * @return ImmutableMap containing keywords for view
     */
    public static ImmutableMap<Command, Collection<String>> getViewCommandMap() {
        Command viewCommand = new ViewCommand();
        return ImmutableMap.<Command, Collection<String>> of(viewCommand,
                Arrays.asList("view", "viewing", "read", "reading", "see",
                        "get"));
    }

    /**
     * @return ImmutableMap containing keywords for undo
     */
    public static ImmutableMap<Command, Collection<String>> getUndoCommandMap() {
        Command undoCommand = new UndoCommand();
        return ImmutableMap.<Command, Collection<String>> of(undoCommand,
                Arrays.asList("undo"));
    }

    /**
     * @return ImmutableMap containing keywords for redo
     */
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
        ImmutableMap<Command, Collection<String>> uncompletedCommandMap = getIncompleteCommandMap();
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

    /**
     * @return ImmutableMap containing keywords for high priority
     */
    public static ImmutableMap<Priority, Collection<String>> getHighPriority() {
        final String PRIORITY_WORD[] = { "priority", "pri" };
        final String KEYWORDS_FOR_HIGH[] = { "high", "urgent", "top",
                "crucial", "important" };

        return ImmutableMap.<Priority, Collection<String>> of(
                Priority.PRIORITY_HIGH,
                getPermutation(PRIORITY_WORD, KEYWORDS_FOR_HIGH));
    }

    /**
     * @return ImmutableMap containing keywords for medium priority
     */
    public static ImmutableMap<Priority, Collection<String>> getMediumPriority() {
        final String PRIORITY_WORD[] = { "priority", "pri" };
        final String KEYWORDS_FOR_MEDIUM[] = { "medium", "med", "normal",
                "neutral", "moderate" };

        return ImmutableMap.<Priority, Collection<String>> of(
                Priority.PRIORITY_MEDIUM,
                getPermutation(PRIORITY_WORD, KEYWORDS_FOR_MEDIUM));
    }

    /**
     * @return ImmutableMap containing keywords for low priority
     */
    public static ImmutableMap<Priority, Collection<String>> getLowPriority() {
        final String PRIORITY_WORD[] = { "priority", "pri" };
        final String KEYWORDS_FOR_LOW[] = { "low", "none", "never", "no",
                "ignore" };

        return ImmutableMap.<Priority, Collection<String>> of(
                Priority.PRIORITY_LOW,
                getPermutation(PRIORITY_WORD, KEYWORDS_FOR_LOW));
    }

    /**
     * @return ImmutableMap containing keywords for someday
     */
    public static ImmutableMap<LocalDate, Collection<String>> getSomeDayKeyWord() {

        return ImmutableMap.<LocalDate, Collection<String>> of(
                Task.DATE_NOT_SET,
                Arrays.asList("someday", "no date", "no day", "some day"));
    }

    /**
     * This function create a map of commands and add them to the multi map. It
     * contains the Priority and String pair, where the string is the keyword
     * for command. In future implementation, the multi map will be generated by
     * reading from a file
     */
    public static Multimap<Priority, String> getPriorityMultiMap() {
        ImmutableMap<Priority, Collection<String>> lowPriorityMap = getLowPriority();
        ImmutableMap<Priority, Collection<String>> MediumPriorityMap = getMediumPriority();
        ImmutableMap<Priority, Collection<String>> HighPriorityMap = getHighPriority();

        return createMultiMap(lowPriorityMap, MediumPriorityMap,
                HighPriorityMap);
    }

    /**
     * This function create a map of commands and add them to the multi map. It
     * contains the Command and String pair, where the string is the keyword for
     * command. In future implementation, the multi map will be generated by
     * reading from a file
     */
    public static Multimap<Command, String> getCompletedUnCompleteMultiMap() {
        ImmutableMap<Command, Collection<String>> completedCommandMap = getCompleteCommandMap();
        ImmutableMap<Command, Collection<String>> uncompletedCommandMap = getIncompleteCommandMap();

        return createMultiMap(completedCommandMap, uncompletedCommandMap);
    }

}
