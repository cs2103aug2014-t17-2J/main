/**
 * 
 */
package logic.utility;

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

/**
 * @author Kuan Tien Long
 *
 */
public class KeyWordMappingList {

    private static ImmutableMap<Command, Collection<String>>[] commandMaps;

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
                Arrays.asList("exit", "quit", "quit", "leave",
                        "leave", "bye"));
    }

    public static ImmutableMap<Command, Collection<String>> getSearchCommandMap() {
        Command searchCommand = new SearchCommand();
        return ImmutableMap.<Command, Collection<String>> of(searchCommand,
                Arrays.asList("search", "searching", "s", "find", "f", "found",
                        "finding" , "locate"));
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
    
    public static Multimap<Command, String> getCommandMultiMap() 
    {
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
        
        return createCommandMultiMap(addCommandMap,completedCommandMap,uncompletedCommandMap,deleteCommandMap,exitCommandMap,searchCommandMap,editCommandMap,viewCommandMap,undoCommandMap,redoCommandMap);
    }

    @SafeVarargs
    public static Multimap<Command, String> createCommandMultiMap(final Map<Command, Collection<String>> ... commandMaps) 
    {
        Multimap<Command, String> commandMultiMap = ArrayListMultimap.create();

        for(Map<Command, Collection<String>> commandMap : commandMaps)
        {
            for (Command key : commandMap.keySet()) {
                commandMultiMap.putAll(key, commandMap.get(key));
            }
        
        }
        return commandMultiMap;
    }

    
    

    public static Multimap<Command, String> createFakeMultiMapForCommand() {
        Multimap<Command, String> availableActions = ArrayListMultimap.create();

        Command addCommand = new AddCommand();
        Command completeCommand = new CompleteCommand();
        Command uncompleteCommand = new UncompleteCommand();
        Command deleteCommand = new DeleteCommand();
        Command exitCommand = new ExitCommand();
        Command searchCommand = new SearchCommand();
        Command editCommand = new EditCommand();
        Command viewCommand = new ViewCommand();
        Command undoCommand = new UndoCommand();
        Command redoCommand = new RedoCommand();

        final Map<Command, Collection<String>> addActions = ImmutableMap
                .<Command, Collection<String>> of(addCommand,
                        Arrays.asList("new"));
        final Map<Command, Collection<String>> completeActions = ImmutableMap
                .<Command, Collection<String>> of(completeCommand,
                        Arrays.asList("complete", "done", "finish", "tick"));
        final Map<Command, Collection<String>> uncompleteActions = ImmutableMap
                .<Command, Collection<String>> of(uncompleteCommand, Arrays
                        .asList("undone", "cancel", "uncomplete"
                        		, "untick"));

        final Map<Command, Collection<String>> deleteActions = ImmutableMap
                .<Command, Collection<String>> of(deleteCommand, Arrays.asList(
                        "delete", "d", "delete", "remove", "remove"));
        final Map<Command, Collection<String>> exitActions = ImmutableMap
                .<Command, Collection<String>> of(exitCommand, Arrays.asList(
                        "exit", "quit", "leave"));
        final Map<Command, Collection<String>> searchActions = ImmutableMap
                .<Command, Collection<String>> of(searchCommand, Arrays.asList(
                        "search", "s", "find", "f"));
        final Map<Command, Collection<String>> editActions = ImmutableMap
                .<Command, Collection<String>> of(editCommand, Arrays.asList(
                        "edit", "e", "modify", "m"));
        final Map<Command, Collection<String>> viewActions = ImmutableMap
                .<Command, Collection<String>> of(viewCommand, Arrays.asList(
                        "view", "read"));
        final Map<Command, Collection<String>> undoActions = ImmutableMap
                .<Command, Collection<String>> of(undoCommand,
                        Arrays.asList("undo", "undo"));
        final Map<Command, Collection<String>> redoActions = ImmutableMap
                .<Command, Collection<String>> of(redoCommand,
                        Arrays.asList("redo", "redo"));

        addMapToMultiMap(addActions, availableActions);
        addMapToMultiMap(completeActions, availableActions);
        addMapToMultiMap(uncompleteActions, availableActions);
        addMapToMultiMap(deleteActions, availableActions);
        addMapToMultiMap(exitActions, availableActions);
        addMapToMultiMap(searchActions, availableActions);
        addMapToMultiMap(editActions, availableActions);
        addMapToMultiMap(viewActions, availableActions);
        addMapToMultiMap(undoActions, availableActions);
        addMapToMultiMap(redoActions, availableActions);

        return availableActions;
    }

    private static void addMapToMultiMap(
            final Map<Command, Collection<String>> map,
            Multimap<Command, String> availableActions) {
        for (Command key : map.keySet()) {
            availableActions.putAll(key, map.get(key));
        }
    }

}
