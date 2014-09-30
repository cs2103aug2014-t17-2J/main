/**
 * 
 */
package logic;


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
     * @param source
     * @return the wordUsed for setting the date if valid, or empty string "" if invalid.
     */
    private String parseDate(Task task, String source) 
    {
        if(invalidDateString(source))
            return "";
        
        TaskAttribute taskAttribute = new TaskDateAttribute();
        String wordsUsed = taskAttribute.set(task, source);
        return wordsUsed;
    }


    /**
     * @param source
     * @return true if source consist of less than minimalDateLength of words
     */
    private boolean invalidDateString(String source) {
        final int minimalDateLength = 3;
        return source.split("\\s+").length < minimalDateLength;
    }

    public Task buildTask(StringBuilder userInputBuilder) 
    {
        String userInput = userInputBuilder.toString();
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
        
        // Store back to StringBuilder
        userInputBuilder.setLength(0);
        userInputBuilder.append(userInput);
        return task;
    }

    private TaskAttribute determineAttribute(String operation) {
        return KeyMatcher.matchKey(createFakeMultiMapNow(), operation);

    }
}
