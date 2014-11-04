/**
 * 
 */
package logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import logic.command.commandList.Command;
import logic.utility.KeyWordMappingList;
import logic.utility.MultiMapMatcher;
import logic.utility.StringHandler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
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
        
        
        priority = MultiMapMatcher.getMatchedKey(KeyWordMappingList.getPriorityMultiMap(),
                source);
        if (isPriorityParsed()) {
            wordUsed = MultiMapMatcher.getMatchedWord(KeyWordMappingList.getPriorityMultiMap(),
                    source);
            
            assert(wordUsed!= null);
            assert(!wordUsed.isEmpty());
            
            wordRemaining = StringHandler.removeFirstMatched(source,
                    wordUsed);

            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * @return if priority is successfully parsed
     */
    private boolean isPriorityParsed() {
        return priority != null;

    }


    public Priority getPriority() {
        return priority;
    }

    public String getWordUsed() {
        return wordUsed;
    }

    public String getWordRemaining() {
        return wordRemaining;
    }


}
