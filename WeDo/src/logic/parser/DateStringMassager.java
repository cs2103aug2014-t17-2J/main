/**
 * 
 */
package logic.parser;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import logic.command.commandList.Command;
import logic.utility.KeyWordMappingList;
import logic.utility.MultiMapMatcher;
import logic.utility.StringHandler;

/**
 * @author Kuan Tien Long
 *
 */
public class DateStringMassager {

    private static final String START_DIGIT_DELIMITER = "{[";
    private static final String END_DIGIT_DELIMITER = "]}";
    private static final String WORD_DELIMITER = ".";

    public static String massageData(String source) {
        source = convertImplicitFormalDate(source);
        source = convertFormalDate(source);

        source = replaceNonDateDigitWithDelimiter(source);

        // danger ( High risk )
        source = replacePossibleDateDescriptionWithDelimiter(source);
        // end danger

        source = MultiMapMatcher.replaceMatchedWithKey(
                createFakeMultiMapForShortForm(), source);


        source = addDelimiterForIndexSelection(source);
        
        System.out.println("before word... is " + source);

        source = replaceWordWithDelimiter(source);


        System.out.println("massage date is " + source);

        return source;
    }

    /**
     * @param source
     * @return
     */
    private static String addDelimiterForIndexSelection(String source) {
        final int COMMAND_GRPOUP = 1;
        final int INDEX_GROUP = 2;
        final int SPACING_GROUP = 3;

        String editKeyWordsRegex = getEditKeyWordsRegex();

        System.out.println("Original String @ index Selection" + source);
        String regexPattern = "(?i)(^\\s*(?:" + editKeyWordsRegex
                + ")\\s+)(\\d+)(\\s+|\\$)";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(COMMAND_GRPOUP)
                    + START_DIGIT_DELIMITER + matcher.group(INDEX_GROUP)
                    + END_DIGIT_DELIMITER + matcher.group(SPACING_GROUP));
        }

        matcher.appendTail(result);

        System.out.println("Modified String @ index Selection" + result.toString());

        return result.toString();

    }

    private static String getEditKeyWordsRegex() {
        String editKeyWordsRegex = "";
        ImmutableMap<Command, Collection<String>> editKeyWords = KeyWordMappingList
                .getEditCommandMap();
        for (Command key : editKeyWords.keySet()) {
            for (String keyWord : editKeyWords.get(key)) {
                editKeyWordsRegex += (keyWord + "|");

            }
        }
        assert (editKeyWordsRegex != null);
        assert (editKeyWordsRegex.length() > 0);
        editKeyWordsRegex = editKeyWordsRegex.substring(0,
                editKeyWordsRegex.length() - 1);
        return editKeyWordsRegex;
    }

    /**
     * @param source
     *            the String that will be searched
     * @param dateWordUsed
     *            the words used for parsing the date
     * @return the frontDateConnector word ("by","at","from" etc) or "" if there
     *         is no dateConnector word
     */
    public static String getFrontDateConnector(String source,
            String dateWordUsed) {

        final int WORD_GROUP = 1;
        String regexPattern = "(\\w+\\s+)(?=" + Pattern.quote(dateWordUsed)
                + ")";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(source);

        if (matcher.find()) {
            String possibleWord = matcher.group(WORD_GROUP);

            if (matchAvailableDateConnector(possibleWord)) {
                return possibleWord;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    private static boolean matchAvailableDateConnector(String source) {
        String regexPattern = "(?i)^in |^on |^from |^at |^by |^date ";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(source);

        return matcher.find();

    }

    private static String replaceNonDateDigitWithDelimiter(String source) {
        source = replaceAllDigitsWithDelimiter(source);
        source = replaceWordDigitAtEndWithDelimiter(source);

        source = removeDelimiterForDateDigitByWord(source);

        source = removeDelimiterForDateDigitByNextWord(source);
        source = removeDelimiterForDateDigitByPreviousWord(source);
        return source;
    }

    /**
     * @param source
     * @return
     */
    private static String replaceWordDigitAtEndWithDelimiter(String source) {
        final String numRegex = "(?<=[A-z])(\\d+)(?=\\s)";
        final int DIGIT_GROUP = 1;
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result,
                    START_DIGIT_DELIMITER + matcher.group(DIGIT_GROUP)
                            + END_DIGIT_DELIMITER);
        }

        return matcher.appendTail(result).toString();
    }

    private static String removeDelimiterForDateDigitByPreviousWord(
            String source) {
        final String numRegex = "(\\w+\\s+)(\\{\\[\\d+\\]\\})";
        final int WORD_GROUP = 1;
        final int DIGIT_GROUP = 2;
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String digit = matcher.group(DIGIT_GROUP);
            String word = matcher.group(WORD_GROUP);

            if (containsDateFormat(matcher.group(WORD_GROUP).trim())) {
                digit = removeDigitDelimiters(digit);
            }
            matcher.appendReplacement(result, word + digit);
        }

        return matcher.appendTail(result).toString();
    }

    public static String removeDigitDelimiters(String source) {
        source = StringHandler.removeAll(source,
                Pattern.quote(START_DIGIT_DELIMITER));
        source = StringHandler.removeAll(source,
                Pattern.quote(END_DIGIT_DELIMITER));
        return source;
    }

    private static String removeDelimiterForDateDigitByWord(String source) {
        final String numRegex = "(\\{\\[\\d+\\]\\})(\\w+)(?=$|\\s)";
        final int DIGIT_GROUP = 1;
        final int WORD_GROUP = 2;
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String digit = matcher.group(DIGIT_GROUP);
            String word = matcher.group(WORD_GROUP);

            if (containsDateFormat(matcher.group(WORD_GROUP).trim())) {
                digit = removeDigitDelimiters(digit);
            }
            matcher.appendReplacement(result, digit + word);
        }

        return matcher.appendTail(result).toString();
    }

    private static String removeDelimiterForDateDigitByNextWord(String source) {
        final String numRegex = "(?<=\\s|^)(\\{\\[\\d+\\]\\})(\\s+\\w+|$)";
        final int DIGIT_GROUP = 1;
        final int WORD_GROUP = 2;
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String digit = matcher.group(DIGIT_GROUP);
            String word = matcher.group(WORD_GROUP);

            if (containsDateFormat(matcher.group(WORD_GROUP).trim())) {
                digit = removeDigitDelimiters(digit);
            }
            matcher.appendReplacement(result, digit + word);
        }

        return matcher.appendTail(result).toString();
    }

    private static String replacePossibleDateDescriptionWithDelimiter(
            String source) {

        final String[] POSSIBLE_DATE_DESCRIPTION = { "day", "days", "week",
                "weeks", "month", "months", "year", "years" };
        String restrictedWordRegex = addRestrictedWordToRegex(POSSIBLE_DATE_DESCRIPTION);

        // String regex = "(?<=[A-z]\\s)("+ restrictedWordRegex
        // +")(?=$|\\s[A-z])";
        String regex = "(?i)(?<!\\d)\\s+(" + restrictedWordRegex
                + ")($|\\s+\\w+)($|\\s+\\w+){0,1}";

        final int POSSIBLE_DATE_GROUP = 1;
        final int NEXT_WORD_GROUP = 2;
        final int NEXT_NEXT_WORD_GROUP = 3;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            if (isDateDescription(matcher.group(POSSIBLE_DATE_GROUP),
                    matcher.group(NEXT_WORD_GROUP),
                    matcher.group(NEXT_NEXT_WORD_GROUP))) {
                matcher.appendReplacement(result, " " + START_DIGIT_DELIMITER
                        + matcher.group(POSSIBLE_DATE_GROUP)
                        + END_DIGIT_DELIMITER + matcher.group(NEXT_WORD_GROUP)
                        + matcher.group(NEXT_NEXT_WORD_GROUP));
            } else {
                matcher.appendReplacement(
                        result,
                        " " + matcher.group(POSSIBLE_DATE_GROUP)
                                + matcher.group(NEXT_WORD_GROUP)
                                + matcher.group(NEXT_NEXT_WORD_GROUP));
            }

            // matcher.appendReplacement(result, matcher.group(NEXT_WORD_GROUP)
            // + matcher.group(NEXT_NEXT_WORD_GROUP));
        }

        return matcher.appendTail(result).toString();

    }

    // What is a date?
    // Next word is end of string
    // Next word is a date already
    // Unsure
    // Next word is a date connector
    // Check next next word is date
    private static boolean isDateDescription(String possibleDateDescription,
            String nextWord, String nextNextWord) {

        if (isEndOfString(nextWord)) {
            return false;
        } else if (isDate(nextWord) || isPriority(nextWord + nextNextWord)) {
            return false;
        } else if (isIntermediateDateConnector(nextWord)
                && (!isEndOfString(nextNextWord)) && isDate(nextNextWord)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param string
     * @return
     */
    private static boolean isPriority(String source) {
        PriorityParser priorityParser = new PriorityParser();
        return priorityParser.tryParse(source);
    }

    /**
     * @param nextWord
     * @return
     */
    private static boolean isIntermediateDateConnector(String nextWord) {
        final String[] POSSIBLE_INTERMEDIATE_CONNECTOR = { "after", "before",
                "to" };
        return StringHandler.containsWord(nextWord.trim(),
                POSSIBLE_INTERMEDIATE_CONNECTOR);
    }

    private static boolean isDate(String nextWord) {
        DateParser dateParser = new DateParser();
        return dateParser.tryParse(nextWord);
    }

    private static boolean isEndOfString(String nextWord) {
        return nextWord == null || nextWord.trim().isEmpty();
    }

    private static String addRestrictedWordToRegex(
            final String[] RESTRICTED_DATE) {

        String restrictedWordRegex = "";
        for (String word : RESTRICTED_DATE) {
            restrictedWordRegex += (word + "|");
        }

        assert (restrictedWordRegex != null);
        assert (restrictedWordRegex.length() > 0);
        restrictedWordRegex = restrictedWordRegex.substring(0,
                restrictedWordRegex.length() - 1);
        return restrictedWordRegex;
    }

    private static String replaceAllDigitsWithDelimiter(String source) {

        final String numRegex = "((?<!/\\d{0,4}|:\\d{0,2})-*\\d+(?=$|\\s|a|p|z|,|-|\\Q.\\E))"; // ignore
                                                                                               // digit
                                                                                               // that
                                                                                               // start
                                                                                               // with
                                                                                               // /
                                                                                               // or
                                                                                               // :

        // final String numRegex = "((?<!/\\d{0,4}|:\\d{0,2})-*\\d+(?=$|\\s))";
        // // ignore digit that start with / or :
        // final String numRegex = "((?<=^|\\s)-*\\d+(?=$|\\s))"; // 1st working
        // regex

        final int digitGroup = 1;

        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result,
                    START_DIGIT_DELIMITER + matcher.group(digitGroup)
                            + END_DIGIT_DELIMITER);
        }

        return matcher.appendTail(result).toString();

    }

    private static String replaceWordWithDelimiter(String source) {
        final String numRegex = "([A-z" + Pattern.quote(WORD_DELIMITER)
                + "]+(?=\\s))";

        final int WORD_GROUP = 1;

        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(WORD_GROUP)
                    + WORD_DELIMITER);
        }

        return matcher.appendTail(result).toString().trim();

    }

    public static String removeWordDelimiter(String source) {
        return StringHandler.removeAll(source, Pattern.quote(WORD_DELIMITER)
                + "(?=\\s|$)");
    }

    private static boolean containsDateFormat(String source) {
        DateFormatSymbols dateFormat = new DateFormatSymbols();
        String[] shortWeekdays = dateFormat.getShortWeekdays();
        String[] longWeekdays = dateFormat.getWeekdays();
        String[] shortMonths = dateFormat.getShortMonths();
        String[] longMonths = dateFormat.getMonths();
        String[] timeUnit = { "hour", "hours", "hr", "hrs", "minute", "min",
                "second", "sec", "am", "pm" };
        String[] commonDateShortForm = { "sept", "day", "days", "week",
                "weeks", "month", "months", "year", "years", "today",
                "tomorrow" };
        // today, tomorrow

        if (StringHandler.containsWord(source, shortWeekdays, longWeekdays,
                shortMonths, longMonths, timeUnit, commonDateShortForm)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param source
     *            which may consist of DD/MM/YYYY format
     * @return replaced string with YYYY/MM/DD format
     */
    private static String convertFormalDate(String source) {
        final int yearGroup = 3;
        final int monthGroup = 2;
        final int dayGroup = 1;
        final String ddmmyyyyRegex = "(?<!\\d)([012]?[0-9]|3[01])[/](0?[1-9]|1[012])[/](\\d+)";

        Pattern pattern = Pattern.compile(ddmmyyyyRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(yearGroup) + "/"
                    + matcher.group(monthGroup) + "/" + matcher.group(dayGroup));
        }
        matcher.appendTail(result);

        return result.toString();

    }

    private static String convertImplicitFormalDate(String source) {
        source = convertDateDDMM(source);
        source = convertDateDDMMYY(source);
        return source;
    }

    /**
     * This function convert date in DD/MM/YY format to DD/MM/20YY format
     * 
     * @param source
     *            which consist of DD/MM/YY format
     * @return replaced string with DD/MM/20YY format
     */
    private static String convertDateDDMMYY(String source) {
        final int inferredYear = 20;

        final int startGroup = 1;
        final int dayGroup = 2;
        final int monthGroup = 3;
        final int yearGroup = 4;
        final int endGroup = 5;

        final String ddmmyyRegex = "([^\\w]|^)+([012]?[0-9]|3[01])[/](0?[1-9]|1[012])[/](\\d\\d)([^\\w]|$)+";

        Pattern pattern = Pattern.compile(ddmmyyRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(
                    result,
                    matcher.group(startGroup) + matcher.group(dayGroup) + "/"
                            + matcher.group(monthGroup) + "/" + inferredYear
                            + matcher.group(yearGroup)
                            + matcher.group(endGroup));
        }

        matcher.appendTail(result);
        return result.toString();
    }

    /**
     * This function convert date in DD/MM format to DD/MM/YYYY format
     * 
     * @param source
     *            which consist of DD/MM format
     * @return replaced string with DD/MM/YYYY format
     */
    private static String convertDateDDMM(String source) {
        final int inferredYear = LocalDateTime.now().getYear();
        final int startGroup = 1;
        final int dayGroup = 2;
        final int monthGroup = 3;
        final int endGroup = 4;

        // final String ddmmyyRegex =
        // "([^\\w]|^)+([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])([^\\w]|$)";
        final String ddmmyyRegex = "(\\s+|^)+([012]?[0-9]|3[01])[/](0?[1-9]|1[012])(\\s+|$)";
        Pattern pattern = Pattern.compile(ddmmyyRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find() && !matcher.group(endGroup).matches("/|-")) {
            matcher.appendReplacement(result, matcher.group(startGroup)
                    + matcher.group(dayGroup) + "/" + matcher.group(monthGroup)
                    + "/" + inferredYear + matcher.group(endGroup));
        }

        matcher.appendTail(result);
        return result.toString();

    }

    /**
     * This function create the short form that will be replace This is a temp
     * solution as the real data will be from a file.
     * 
     * @return
     */
    private static Multimap<String, String> createFakeMultiMapForShortForm() {

        Multimap<String, String> mappedWords = ArrayListMultimap.create();
        String tomorrow = "tomorrow";

        final Map<String, Collection<String>> tommorrowMap = ImmutableMap
                .<String, Collection<String>> of(tomorrow,
                        Arrays.asList("tml", "tmr", "nxt day"));

        for (String key : tommorrowMap.keySet()) {
            mappedWords.putAll(key, tommorrowMap.get(key));
        }

        return mappedWords;

    }

}
