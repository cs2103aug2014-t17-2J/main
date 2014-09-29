/**
 * 
 */
package logic;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kuan Tien Long
 *
 */
public class TaskParserPlus implements TaskParser {

    private String replaceDateKeyWords(String source) {
        source = source.toLowerCase();
        return source.replaceAll("in$|on$|from$|at$|by$|date$|^in|^on|^from|^at|^by|^date",
                "");
    }

    private Task createTask(String userInput) throws InvalidCommandException {
        String wordsUsed;
        Task task = new Task();
        userInput = StringHandler.convertImplicitFormalDate(userInput);
        userInput = StringHandler.convertFormalDate(userInput);

        wordsUsed = parseDate(task, userInput);
        userInput = userInput.replaceFirst(wordsUsed, "");
        userInput = replaceDateKeyWords(userInput.trim());
        wordsUsed = parsePriority(userInput, task);
        userInput = userInput.replaceFirst(wordsUsed, "");
        userInput = replaceDateKeyWords(userInput.trim());
        wordsUsed = parseDescription(userInput, task);
        userInput = userInput.replaceFirst(wordsUsed, "");
        
        System.out.println(task);
        return task;
    }

    /**
     * @param token
     * @param task
     * @return words used to parse the priority if valid, if not valid return
     *         empty string ""
     */
    private String parsePriority(String userInput, Task task) {
        String firstTwoWords = StringHandler.getFirstTwoWords(userInput);
        String lastTwoWords = StringHandler.getLastTwoWords(userInput);

        if (tryParsePriority(firstTwoWords, task)) {
            return firstTwoWords;
        }
        if (tryParsePriority(lastTwoWords, task)) {
            return lastTwoWords;
        }

        return "";
    }

    private boolean tryParsePriority(String input, Task task) {
        if (input == null) {
            return false;
        }

        TaskAttribute taskAttribute = determineAttribute(input);

        if (taskAttribute == null) {
            return false;
        }
        
        taskAttribute.set(task, input);
        return true;

    }

    private String parseDescription(String input, Task task) 
    {
        input = removeCommandWord(input);
        input = input.trim();    
        TaskAttribute taskAttribute = new TaskDescriptionAttribute();
        taskAttribute.set(task, input);
        return input;
    }

    private String removeCommandWord(String input) 
    {
        input = StringHandler.removeFirstWord(input);
        return input;
    }

    /**
     * @param task
     * @param arguments
     * @return the wordUsed for setting the date
     */
    private String parseDate(Task task, String arguments) {
        TaskAttribute taskAttribute = new TaskDateAttribute();
        String wordsUsed = taskAttribute.set(task, arguments);
        return wordsUsed;
    }

    public Task buildTask(String userInput) throws InvalidCommandException 
    {
        Task task = createTask(userInput);
        return task;
    }

    private TaskAttribute determineAttribute(String operation) {
        return KeyMatcher.matchKey(createFakeMultiMapNow(), operation);

    }
}
