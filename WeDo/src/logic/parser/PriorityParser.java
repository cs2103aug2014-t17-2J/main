/**
 * 
 */
package logic.parser;

import logic.utility.KeyWordMappingList;
import logic.utility.MultiMapMatcher;
import logic.utility.StringHandler;

import definedEnumeration.Priority;

//@author A0112887X
/**
 *
 */
public class PriorityParser {

    private String wordRemaining;
    private String wordUsed;
    private Priority priority;

    /**
     * <p>
     * The source will be parsed to see if it contains date.
     * 
     * @param source
     *            the String to be parsed
     * @return if source contains valid priority
     */
    public boolean tryParse(String source) {

        priority = MultiMapMatcher.getMatchedKey(
                KeyWordMappingList.getPriorityMultiMap(), source);
        if (isPriorityParsed()) {
            wordUsed = MultiMapMatcher.getMatchedWord(
                    KeyWordMappingList.getPriorityMultiMap(), source);

            assert (wordUsed != null);
            assert (!wordUsed.isEmpty());

            wordRemaining = StringHandler.removeFirstMatched(source, wordUsed);

            return true;
        } else {
            return false;
        }
    }

    /**
     * @return if priority is successfully parsed
     */
    private boolean isPriorityParsed() {
        return priority != null;

    }

    /**
     * @return the priority parsed
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * @return the word used
     */
    public String getWordUsed() {
        return wordUsed;
    }

    /**
     * @return the word remaining
     */
    public String getWordRemaining() {
        return wordRemaining;
    }

}
