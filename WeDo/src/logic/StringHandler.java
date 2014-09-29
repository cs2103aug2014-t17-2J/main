package logic;

import java.time.LocalDateTime;
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
     * @param source which may consist of DD/MM/YYYY format
     * @return replaced string with YYYY/MM/DD format
     */
    public static String convertFormalDate(String source)
    {
        final int yearGroup = 3;
        final int monthGroup = 2;
        final int dayGroup = 1;
        final String ddmmyyyyRegex = "([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])[/-]((18|19|20|21)\\d\\d)";
        Pattern pattern = Pattern.compile(ddmmyyyyRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();
        
        while(matcher.find())
        {
            matcher.appendReplacement(result, matcher.group(yearGroup) + "/" + matcher.group(monthGroup) + "/" + matcher.group(dayGroup));
        }   
        matcher.appendTail(result);

        return result.toString();
        
    }
    
    public static String convertImplicitFormalDate(String source)
    {
        source = convertDateDDMM(source);
        source = convertDateDDMMYY(source);
        return source;
    }
    
    
    /**
     * This function convert date in DD/MM/YY or DD-MM-YY format to DD/MM/20YY format
     * @param source which consist of DD/MM/YY format
     * @return replaced string with DD/MM/20YY format
     */
    public static String convertDateDDMMYY(String source)
    {
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
        
        while(matcher.find())
        {
            matcher.appendReplacement(result, matcher.group(startGroup) + matcher.group(dayGroup) + "/" + matcher.group(monthGroup) + "/" + inferredYear + matcher.group(yearGroup) + matcher.group(endGroup));
        }
        
        matcher.appendTail(result);
        return result.toString();
    }
    
    /**
     * This function convert date in DD/MM or DD-MM format to DD/MM/YYYY format
     * @param source which consist of DD/MM format
     * @return replaced string with DD/MM/YYYY format
     */
    public static String convertDateDDMM(String source)
    {
        final int inferredYear = LocalDateTime.now().getYear();
        final int startGroup = 1;
        final int dayGroup = 2;
        final int monthGroup = 3;
        final int endGroup = 4;
    
        final String ddmmyyRegex = "([^\\w]|^)+([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])([^\\w]|$)";
        Pattern pattern = Pattern.compile(ddmmyyRegex);
        Matcher matcher = pattern.matcher(source);
        StringBuffer result = new StringBuffer();
        
        
        while(matcher.find() && !matcher.group(endGroup).matches("/|-"))
        {
            matcher.appendReplacement(result, matcher.group(startGroup) + matcher.group(dayGroup) + "/" + matcher.group(monthGroup) + "/" + inferredYear + matcher.group(endGroup));
        }
       
        matcher.appendTail(result);
        return result.toString();    
    
    }
    
    /**
     * @param source
     * @return first two words if valid, null if invalid
     */
    public static String getFirstTwoWords(String source)
    {
        final String theRegex = "^(\\w+\\s+\\w+)";
        return getMatchedRegex(source, theRegex);
        
    }
    
    /**
     * @param source
     * @return last two words if valid, null if invalid
     */
    public static String getLastTwoWords(String source)
    {
        final String theRegex = "(\\w+\\s+\\w+)$";
        return getMatchedRegex(source, theRegex);   
    }

    private static String getMatchedRegex(String source, final String theRegex) {
        
        if (source == null) {
            return null;
        }

        source = source.trim();

        if (source.isEmpty()) {
            return null;
        }
        
        Pattern pattern = Pattern.compile(theRegex);
        Matcher matcher = pattern.matcher(source);
        
        if(matcher.find())
        {
            return matcher.group(1);
        }
        else
        {
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

        if (source.contains(" ")) {
            return source.replaceFirst("^.+?(?=\\s)", "");
        }
        else
        {
            return source;
        }

    }
    
   
}
