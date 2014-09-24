package logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kuan Tien Long
 *
 */
public class StringHandler {

    /**
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

        if (source.contains(" ")) {
            String firstWord = source.substring(0, source.indexOf(" "));
            return firstWord;
        } else {
            return source;
        }

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
    
    public static String[] splitString(final String source, final String delimiter)
    {   
        return source.replaceFirst("^" + delimiter, "").split(delimiter);
    }
    
    
    
    /**
     * @param input which may consist of DD/MM/YYYY format
     * @return replaced string with YYYY/MM/DD format
     */
    public static String convertFormalDate(String input)
    {
        final int yearGroup = 3;
        final int monthGroup = 2;
        final int dayGroup = 1;
        String ddmmyyyyRegex = "([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])[/-]((18|19|20|21)\\d\\d)";
        Pattern pattern = Pattern.compile(ddmmyyyyRegex);
        Matcher matcher = pattern.matcher(input);
        StringBuffer result = new StringBuffer();
        while(matcher.find())
        {
            matcher.appendReplacement(result, matcher.group(yearGroup) + "/" + matcher.group(monthGroup) + "/" + matcher.group(dayGroup));
        }   
        matcher.appendTail(result);

        return result.toString();
        
    }
    
    
    /**
     * @param input which consist of DD/MM/YY format
     * @return replaced string with DD/MM/20YY format
     */
    public static String convertImplicitFormalDate(String input)
    {
        final int inferredYear = 20;
        
        final int startGroup = 1;
        final int dayGroup = 2;
        final int monthGroup = 3;
        final int yearGroup = 4;
        final int endGroup = 5;
       
        String ddmmyyRegex = "([^\\w]|^)+([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])[/-](\\d\\d)([^\\w]|$)+";
        Pattern pattern = Pattern.compile(ddmmyyRegex);
        Matcher matcher = pattern.matcher(input);
        StringBuffer result = new StringBuffer();
        
        while(matcher.find())
        {
            matcher.appendReplacement(result, matcher.group(startGroup) + matcher.group(dayGroup) + "/" + matcher.group(monthGroup) + "/" + inferredYear + matcher.group(yearGroup) + matcher.group(endGroup));
        }
        
        matcher.appendTail(result);
        return result.toString();
    }
    
}
