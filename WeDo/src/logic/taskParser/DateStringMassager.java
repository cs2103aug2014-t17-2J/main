/**
 * 
 */
package logic.taskParser;

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

import logic.utility.KeyMatcher;
import logic.utility.StringHandler;

/**
 * @author Kuan Tien Long
 *
 */
public class DateStringMassager {

    private final String startDelimiter = "{[";
    private final String endDelimiter = "]}";

    public String massageData(String source) 
    {
        source = convertImplicitFormalDate(source);
        source = convertFormalDate(source);
        
        source = replaceNonDateDigitWithDelimiter(source);
    
        source = KeyMatcher.replaceMatchedWithKey(
                createFakeMultiMapForShortForm(), source);
        
        return source;
    }
    

    private String replaceNonDateDigitWithDelimiter(String source) {
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
        digit = StringHandler.removeAll(digit, Pattern.quote(startDelimiter));
        digit = StringHandler.removeAll(digit, Pattern.quote(endDelimiter));
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

        if (StringHandler.contains(source, shortWeekdays, longWeekdays,
                shortMonths, longMonths, timeUnit)) {
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
    private String convertFormalDate(String source) {
        final int yearGroup = 3;
        final int monthGroup = 2;
        final int dayGroup = 1;
        final String ddmmyyyyRegex = "([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])[/-]((18|19|20|21)\\d\\d)";
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

    private String convertImplicitFormalDate(String source) {
        source = convertDateDDMM(source);
        source = convertDateDDMMYY(source);
        return source;
    }

    /**
     * This function convert date in DD/MM/YY or DD-MM-YY format to DD/MM/20YY
     * format
     * 
     * @param source
     *            which consist of DD/MM/YY format
     * @return replaced string with DD/MM/20YY format
     */
    private String convertDateDDMMYY(String source) {
        final int inferredYear = 20;

        final int startGroup = 1;
        final int dayGroup = 2;
        final int monthGroup = 3;
        final int yearGroup = 4;
        final int endGroup = 5;

        final String ddmmyyRegex = "([^\\w]|^)+([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])[/-](\\d\\d)([^\\w]|$)+";
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
     * This function convert date in DD/MM or DD-MM format to DD/MM/YYYY format
     * 
     * @param source
     *            which consist of DD/MM format
     * @return replaced string with DD/MM/YYYY format
     */
    private String convertDateDDMM(String source) {
        final int inferredYear = LocalDateTime.now().getYear();
        final int startGroup = 1;
        final int dayGroup = 2;
        final int monthGroup = 3;
        final int endGroup = 4;

        final String ddmmyyRegex = "([^\\w]|^)+([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])([^\\w]|$)";
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
     * This function create the short form that will be replace 
     * This is a temp solution as the real data will be from a file.
     * @return
     */
    private Multimap<String, String> createFakeMultiMapForShortForm() {

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
