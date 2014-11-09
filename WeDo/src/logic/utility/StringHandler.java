package logic.utility;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//@author A0112887X
/**
 *
 */
public class StringHandler {

    /**
     * Get the first word (pure alphabet word) from the String
     * 
     * @param source
     *            the string where the first word is to be extracted
     * @return the extracted first word or null if there is no first word
     */
    public static String getFirstWord(String source) {
        if (source == null) {
            return null;
        }

        source = source.trim();

        if (source.isEmpty()) {
            return null;
        }

        if (containsSpace(source)) {

            final int FIRST_WORD_GROUP = 1;
            String firstWordRegex = "^[^A-z]*([A-z]+)";
            Pattern pattern = Pattern.compile(firstWordRegex);
            Matcher matcher = pattern.matcher(source);
            if (matcher.find()) {
                return matcher.group(FIRST_WORD_GROUP);
            } else {
                return null;
            }
        } else {
            return source;
        }
    }

    /**
     * Check string if it contains space
     * 
     * @param source
     *            the string to check
     * @return if it contains space
     */
    private static boolean containsSpace(String source) {
        return source.contains(" ");
    }

    /**
     * Get the last word (pure alphabet word) from a String
     * 
     * @param source
     *            the string where the last word is to be extracted
     * @return the extracted last word or null if there is no last word
     */
    public static String getLastWord(String source) {
        if (source == null) {
            return null;
        }

        source = source.trim();

        if (source.isEmpty()) {
            return null;
        }

        if (containsSpace(source)) {
            final int LAST_WORD_GROUP = 1;
            String lastWordRegex = "\\s([A-z]+)[^A-z]*$";
            Pattern pattern = Pattern.compile(lastWordRegex);
            Matcher matcher = pattern.matcher(source);
            if (matcher.find()) {
                return matcher.group(LAST_WORD_GROUP);
            } else {
                return null;
            }

        } else {
            return source;
        }
    }

    public static String[] getSeparatedWord(String source, String word) {
        return source.split(word);
    }

    /**
     * @param source
     *            the source that require a particular word to be removed
     * @param toRemove
     *            the String that is to be removed
     * @return the new source without the toRemove word
     *         <p>
     *         This function remove the first word that matches with the
     *         toRemove word
     *         <p>
     */
    public static String removeFirstMatched(String source, String toRemove) {
        if (source != null && toRemove != null) {
            toRemove = toRemove.trim();
            String newWord = source.replaceFirst(toRemove, "");
            return newWord.trim();
        }

        return source;
    }
    
    /**
     * @param source
     *            the source that require a particular word to be removed
     * @param toRemove
     *            the String that is to be removed
     * @return the new source without the toRemove word
     *         <p>
     *         This function remove the first word that matches with the
     *         toRemove word
     *         <p>
     */
    public static String removeFirstMatchedWord(String source, String toRemove) {
        if (source != null && toRemove != null) {
            source = source.trim();
            toRemove = toRemove.trim();
            final String SPACE = " ";
            source = SPACE + source + SPACE;
            toRemove = SPACE + toRemove + SPACE;
            
            String newWord = source.replaceFirst(toRemove, "");
            return newWord.trim();
        }

        return source;
    }

    /**
     * @param source
     *            the source that require a particular word to be removed
     * @param toRemove
     *            the String that is to be removed
     * @return the new source without the toRemove word
     *         <p>
     *         This function remove the last word that matches with the toRemove
     *         word
     *         <p>
     */
    public static String removeLastMatch(String source, String toRemove) {
        if (source != null && toRemove != null) {
            toRemove = toRemove.trim() + "$";
            toRemove = toRemove.trim();
            String newWord = source.replaceFirst(toRemove, "");
            return newWord.trim();
        }

        return source;
    }

    /**
     * @param source
     *            the source that require a particular word to be removed
     * @param toRemove
     *            the String that is to be removed
     * @return the new source without the toRemove word
     *         <p>
     *         This function remove all matches with the toRemove word
     *         <p>
     */
    public static String removeAll(String source, String toRemove) {
        if (source != null && toRemove != null) {
            toRemove = toRemove.trim();
            String newWord = source.replaceAll(toRemove, "");
            return newWord.trim();
        }

        return source;
    }

    public static String[] splitString(final String source,
            final String delimiter) {
        return source.replaceFirst("^" + delimiter, "").split(delimiter);
    }

    /**
     * @param source
     *            which may consist of DD/MM/YYYY format
     * @return replaced string with YYYY/MM/DD format
     */
    public static String convertFormalDate(String source) {
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

    public static String convertImplicitFormalDate(String source) {
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
    public static String convertDateDDMMYY(String source) {
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
    public static String convertDateDDMM(String source) {
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
     * Get the digit after first pure alphabet word from the source
     * 
     * @param source
     *            the source which contains the digit
     * @return digit after first word, null if invalid
     */
    public static String getDigitAfterFirstWord(String source) {
        final String theRegex = "^[^A-z]*[A-z]+\\s+(\\d+)";
        return getMatchedRegex(source, theRegex);

    }

    /**
     * Get the digit after last pure alphabet word from the source
     * 
     * @param source
     *            the source which contains the digit
     * @return digit after last word, null if invalid
     */
    public static String getDigitAfterLastWord(String source) {
        final String theRegex = "\\s[A-z]+\\s+(\\d+)[^A-z]*$";
        return getMatchedRegex(source, theRegex);
    }

    /**
     * Remove the digit after first pure alphabet word from the source
     * 
     * @param source
     *            the source which contains the digit
     * @return source with removed digit if found, source if not found
     */
    public static String removeDigitAfterFirstWord(String source) {
        final String theRegex = "^[^A-z]*[A-z]+\\s+(\\d+)";
        return removeMatchedRegex(source, theRegex);

    }

    /**
     * Remove the digit after last pure alphabet word from the source
     * 
     * @param source
     *            the source which contains the digit
     * @return source with removed digit if found, source if not found
     */
    public static String removeDigitAfterLastWord(String source) {
        final String theRegex = "\\s[A-z]+\\s+(\\d+)[^A-z]*$";
        return removeMatchedRegex(source, theRegex);
    }

    /**
     * remove matched regex pattern
     * 
     * @param source
     * @param regexPattern
     * @return
     */
    private static String removeMatchedRegex(String source, String regexPattern) {

        final int MATCHED = 1;
        if (source == null) {
            return source;
        }

        source = source.trim();

        if (source.isEmpty()) {
            return source;
        }

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();

        if (matcher.find()) {
            matcher.appendReplacement(result, "");
            System.out.println(matcher.group(MATCHED));
        }

        matcher.appendTail(result).toString();
        System.out.println(result);

        return result.toString();

    }

    /**
     * return matched regex pattern or null is there is isn't
     * 
     * @param source
     * @param regexPattern
     * @return
     */
    private static String getMatchedRegex(String source, String regexPattern) {

        final int MATCHED = 1;
        if (source == null) {
            return null;
        }

        source = source.trim();

        if (source.isEmpty()) {
            return null;
        }

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(source);

        if (matcher.find()) {
            return matcher.group(MATCHED);
        } else {
            return null;
        }
    }

    /**
     * @param source
     *            the string where the first word is to be extracted
     * @return the extracted first word or null if there is no first word
     */
    public static String removeFirstWord(String source) {
        if (source == null) {
            return null;
        }

        source = source.trim();

        if (source.isEmpty()) {
            return null;
        }

        if (containsSpace(source)) {
            return source.replaceFirst("^.+?(?=\\s)", "");
        } else {
            return "";
        }

    }

    /**
     * This function search for words that matches with values at keyArrays
     * 
     * @param source
     *            the string that is to be searched
     * @param keyArrays
     *            the string arrays which have the keys to search for
     * @return if it contains word
     */
    public static boolean containsWord(String source, String[]... keyArrays) {
        source = " " + source.toLowerCase() + " ";

        for (String[] keyArray : keyArrays) {
            for (String key : keyArray) {
                if (!key.isEmpty()) {
                    key = " " + key.toLowerCase() + " ";
                    if (source.contains(key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    
    /**
     * This function search for words that matches with values at keyArrays
     * 
     * @param source
     *            the string that is to be searched
     * @param keyArrays
     *            the string arrays which have the keys to search for
     * @return the containing word
     */
    public static String getContainsWord(String source, String[]... keyArrays) {
        source = " " + source.toLowerCase() + " ";

        for (String[] keyArray : keyArrays) {
            for (String key : keyArray) {
                if (!key.isEmpty()) {
                    key = " " + key.toLowerCase() + " ";
                    if (source.contains(key)) {
                        return key;
                    }
                }
            }
        }
        return "";
    }
    

    /**
     * @param source
     *            the string that will be searched
     * @return the first integer if it contains, null if it does not.
     */
    public static String getIntegerFromFirstSlot(String source) {
        String regexPattern = "(^\\d+)";
        return getMatchedRegex(source, regexPattern);
    }

    /**
     * @param source
     *            the arguments passed in by the user
     * @return Integer that is parsed from the string, MAX_VALUE if it is not
     *         parsable
     */
    public static int parseStringToInteger(String source) {

        if (isValidString(source)) {
            source = source.trim();
            if (isInteger(source)) {
                return Integer.parseInt(source);
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * @param userInput
     *            the user input
     * @return whether the string could be parsed
     */
    private static boolean isInteger(String userInput) {

        try {
            Integer.parseInt(userInput);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param addedInput
     *            the input to be added
     * @return whether it is a valid string
     */
    private static boolean isValidString(String addedInput) {
        return addedInput != null && addedInput.length() > 0;
    }
}
