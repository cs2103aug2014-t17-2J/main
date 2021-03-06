/**
 * 
 */
package logic.deprecated;

import java.text.DateFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.deprecated.taskFieldSetter.TaskDateFieldSetter;
import logic.deprecated.taskFieldSetter.TaskDescriptionFieldSetter;
import logic.deprecated.taskFieldSetter.TaskFieldSetter;
import logic.utility.MultiMapMatcher;
import logic.utility.StringHandler;
import logic.utility.Task;

//@author A0112887X - unused
/**
 * @author Kuan Tien Long
 *
 */
public class TaskParserPlus implements TaskParser {

    private final String startDelimiter = "{[";
    private final String endDelimiter = "]}";

    public Task buildTask(StringBuilder userInputBuilder) {
        String userInput = userInputBuilder.toString();
        String wordsUsed;
        Task task = new Task();

        userInput = StringHandler.convertImplicitFormalDate(userInput);
        userInput = StringHandler.convertFormalDate(userInput);

        userInput = findDateFormat(userInput); // replace non date with
                                               // delimiter

        userInput = MultiMapMatcher.replaceMatchedWithKey(
                createFakeMultiMapForShortForm(), userInput);


        wordsUsed = parseDate(task, userInput);
        userInput = userInput.replaceFirst(wordsUsed, "");
        userInput = replaceDateKeyWords(userInput.trim());
        userInput = removeDelimiters(userInput);


        wordsUsed = parsePriority(userInput, task);
        userInput = userInput.replaceFirst(wordsUsed, "");
        userInput = replaceDateKeyWords(userInput.trim());
        wordsUsed = parseDescription(userInput, task);
        userInput = userInput.replaceFirst(wordsUsed, "");

        // Store back to StringBuilder
        userInputBuilder.setLength(0);
        userInputBuilder.append(userInput);
        System.out.println(task);
        return task;
    }

    private TaskFieldSetter determineAttribute(String operation) {
        return MultiMapMatcher.getMatchedKey(createFakeMultiMapForPriority(),
                operation);

    }

    private String replaceDateKeyWords(String source) {

        return source
                .replaceAll(
                        "(?i) in$| on$| from$| at$| by$| date$|^in |^on |^from |^at |^by |^date ",
                        "");
    }

    private String findDateFormat(String source) {
        source = replaceDigits(source);
        source = nextWordContainsDateFormat(source);
        source = previousWordContainsDateFormat(source);
        return source;
    }

    private String previousWordContainsDateFormat(String source) {
        final String numRegex = "(\\w+\\s+)(\\{\\[\\d+\\]\\})";
        final int WORD_GROUP = 1;
        final int DIGIT_GROUP = 2;
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String digit = matcher.group(DIGIT_GROUP);
            String word = matcher.group(WORD_GROUP);

            if (containsDateFormat(matcher.group(WORD_GROUP))) {
                digit = removeDelimiters(digit);
            }
            matcher.appendReplacement(result, word + digit);
        }

        return matcher.appendTail(result).toString();
    }

    private String removeDelimiters(String digit) {
        digit = StringHandler.removeAll(digit, "\\{\\[");
        digit = StringHandler.removeAll(digit, "\\]\\}");
        return digit;
    }

    private String nextWordContainsDateFormat(String source) {
        final String numRegex = "(\\{\\[\\d+\\]\\})(\\s+\\w+|$)";
        final int DIGIT_GROUP = 1;
        final int WORD_GROUP = 2;
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String digit = matcher.group(DIGIT_GROUP);
            String word = matcher.group(WORD_GROUP);

            if (containsDateFormat(matcher.group(WORD_GROUP))) {
                digit = removeDelimiters(digit);
            }
            matcher.appendReplacement(result, digit + word);
        }

        return matcher.appendTail(result).toString();
    }

    private String replaceDigits(String source) {
        final String numRegex = "((?<=^|\\s)\\d+(?=$|\\s))";
        final int digitGroup = 1;

        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result,
                    startDelimiter + matcher.group(digitGroup) + endDelimiter);
        }

        return matcher.appendTail(result).toString();

    }

    private boolean containsDateFormat(String source) {
        DateFormatSymbols dateFormat = new DateFormatSymbols();
        String[] shortWeekdays = dateFormat.getShortWeekdays();
        String[] longWeekdays = dateFormat.getWeekdays();
        String[] shortMonths = dateFormat.getShortMonths();
        String[] longMonths = dateFormat.getMonths();
        String[] timeUnit = { "hour", "hr", "minute", "min", "second", "sec",
                "am", "pm", "day", "week", "month" };

        if (StringHandler.containsWord(source, shortWeekdays, longWeekdays,
                shortMonths, longMonths, timeUnit)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param token
     * @param task
     * @return words used to parse the priority if valid, if not valid return
     *         empty string ""
     */
    private String parsePriority(String userInput, Task task) {
        String firstTwoWords = StringHandler.getDigitAfterFirstWord(userInput);
        String lastTwoWords = StringHandler.getDigitAfterLastWord(userInput);

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

        TaskFieldSetter taskAttribute = determineAttribute(input);

        if (taskAttribute == null) {
            return false;
        }

        taskAttribute.set(task, input);
        return true;

    }

    private String parseDescription(String input, Task task) {
        input = removeCommandWord(input);
        input = input.trim();
        TaskFieldSetter taskAttribute = new TaskDescriptionFieldSetter();
        taskAttribute.set(task, input);
        return input;
    }

    private String removeCommandWord(String input) {
        input = StringHandler.removeFirstWord(input);
        return input;
    }

    /**
     * @param task
     * @param source
     * @return the wordUsed for setting the date if valid, or empty string "" if
     *         invalid.
     */
    private String parseDate(Task task, String source) {

        TaskFieldSetter taskAttribute = new TaskDateFieldSetter();
        String wordsUsed = taskAttribute.set(task, source);
        return wordsUsed;
    }


}
