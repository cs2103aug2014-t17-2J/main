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

import logic.utility.KeyMatcher;
import logic.utility.StringHandler;

/**
 * @author Kuan Tien Long
 *
 */
public class DateStringMassager {

    private static final String START_DIGIT_DELIMITER = "{[";
    private static final String END_DIGIT_DELIMITER = "]}";
    private static final String WORD_DELIMITER = ".";

    public static String massageData(String source) 
    {
        source = convertImplicitFormalDate(source);
        source = convertFormalDate(source);
        
        source = replaceNonDateDigitWithDelimiter(source);
        source = replaceWordWithDelimiter(source);
        
        source = KeyMatcher.replaceMatchedWithKey(
                createFakeMultiMapForShortForm(), source);
        
        System.out.println("massage date is " + source);
        
        return source;
    }
    
    /**
     * @param source the String that will be searched
     * @param dateWordUsed the words used for parsing the date
     * @return the dateConnector word ("by","at","from" etc) or "" if there is no dateConnector word
     */
    public static String getDateConnector(String source, String dateWordUsed)
    {
                
        final int WORD_GROUP = 1;
        String regexPattern = "(\\w+\\s+)(?=" + Pattern.quote(dateWordUsed) + ")";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(source);
       
        
        if(matcher.find())
        {
            String possibleWord = matcher.group(WORD_GROUP);
            
            if(matchAvailableDateConnector(possibleWord))
            {
                return possibleWord;
            }
            else
            {
                return "";
            }
        }
        else
        {
            return "";
        }
    }
    
    private static boolean matchAvailableDateConnector(String source)
    {
        String regexPattern =  "(?i)^in |^on |^from |^at |^by |^date ";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(source);
        
        return matcher.find();
       
    }
    

    private static String replaceNonDateDigitWithDelimiter(String source) {
        source = replaceAllDigitsWithDelimiter(source);
        source = replaceWordDigitAtEndWithDelimiter(source);
        source = removeDelimiterForDateDigitByNextWord(source);
        source = removeDelimiterForDateDigitByPreviousWord(source);
        return source;
    }

    /**
     * @param source
     * @return
     */
    private static String replaceWordDigitAtEndWithDelimiter(String source) 
    {
        final String numRegex = "(?<=[A-z])(\\d+)(?=\\s)";
        final int DIGIT_GROUP = 1;
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            matcher.appendReplacement(result,
                    START_DIGIT_DELIMITER + matcher.group(DIGIT_GROUP) + END_DIGIT_DELIMITER);
        }

        return matcher.appendTail(result).toString();
    }

    private static String removeDelimiterForDateDigitByPreviousWord(String source) {
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
                digit = removeDigitDelimiters(digit);
            }
            matcher.appendReplacement(result, word + digit);
        }

        return matcher.appendTail(result).toString();
    }

    public static String removeDigitDelimiters(String source) {
        source = StringHandler.removeAll(source, Pattern.quote(START_DIGIT_DELIMITER));
        source = StringHandler.removeAll(source, Pattern.quote(END_DIGIT_DELIMITER));
        return source;
    }


    
    private static String removeDelimiterForDateDigitByNextWord(String source) {
        final String numRegex = "(?<=\\s)(\\{\\[\\d+\\]\\})(\\s+\\w+|$)";
        final int DIGIT_GROUP = 1;
        final int WORD_GROUP = 2;
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String digit = matcher.group(DIGIT_GROUP);
            String word = matcher.group(WORD_GROUP);

            if (containsDateFormat(matcher.group(WORD_GROUP))) {
                digit = removeDigitDelimiters(digit);
            }
            matcher.appendReplacement(result, digit + word);
        }

        return matcher.appendTail(result).toString();
    }

    private static String replaceAllDigitsWithDelimiter(String source) {
        final String numRegex = "((?<=^|\\s)\\d+(?=$|\\s))";
        final int digitGroup = 1;

        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result,
                    START_DIGIT_DELIMITER + matcher.group(digitGroup) + END_DIGIT_DELIMITER);
        }

        return matcher.appendTail(result).toString();

    }
    
    private static String replaceWordWithDelimiter(String source) {
        final String numRegex = "([A-z]+(?=\\s))";
        
        final int WORD_GROUP = 1;

        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(WORD_GROUP) + WORD_DELIMITER);
        }

        return matcher.appendTail(result).toString().trim();

    }
    public static String removeWordDelimiter(String source)
    {
        return  StringHandler.removeAll(source, Pattern.quote(WORD_DELIMITER));
    }

    private static boolean containsDateFormat(String source) {
        DateFormatSymbols dateFormat = new DateFormatSymbols();
        String[] shortWeekdays = dateFormat.getShortWeekdays();
        String[] longWeekdays = dateFormat.getWeekdays();
        String[] shortMonths = dateFormat.getShortMonths();
        String[] longMonths = dateFormat.getMonths();
        String[] timeUnit = { "hour", "hr", "minute", "min", "second", "sec",
                "am", "pm"};
        String[] commonDateShortForm = { "sept", "day", "week", "month", "today", "tomorrow"};
        
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
        final String ddmmyyyyRegex = "(?<!\\d)([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])[/-](\\d+)";
        
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
     * This function convert date in DD/MM/YY or DD-MM-YY format to DD/MM/20YY
     * format
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
    private static String convertDateDDMM(String source) {
        final int inferredYear = LocalDateTime.now().getYear();
        final int startGroup = 1;
        final int dayGroup = 2;
        final int monthGroup = 3;
        final int endGroup = 4;

//        final String ddmmyyRegex = "([^\\w]|^)+([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])([^\\w]|$)";
        final String ddmmyyRegex = "(\\s+|^)+([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])(\\s+|$)";
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
