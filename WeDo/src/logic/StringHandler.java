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
       
        String ddmmyyyyRegex = "([012]?[0-9]|3[01])[/-](0?[1-9]|1[012])[/-]((18|19|20|21)\\d\\d)";
        Pattern pattern = Pattern.compile(ddmmyyyyRegex);
        Matcher matcher = pattern.matcher(input);
        StringBuffer result = new StringBuffer();
        while(matcher.find())
        {
            matcher.appendReplacement(result, matcher.group(3) + "/" + matcher.group(2) + "/" + matcher.group(1));
            matcher.appendTail(result);
        }   
        return result.toString();
        
    }
    
}
