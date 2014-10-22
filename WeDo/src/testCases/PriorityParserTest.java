/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import logic.parser.PriorityParser;

import org.junit.Test;

import definedEnumeration.Priority;

//@formatter:off
/**
 * @author 
 *
 * Priority Parser works by testing the first two words or the last two words of the String.
 * If either the first two words or the last two words contain the priority key words, tryParse function will return true. 
 * If both first and last two words are priority, the first two words will take precedence.
 * 
 * Listed below are the keywords for priority and the level of priority
 * They can be arranged in any permutation ( eg : priority high, high priorty, high pri, etc)
 * Additionally, the key words are case insensitive 
 * +------------+-------------+------------+----------+
 * | Priority   | High        | Medium     | Low      |
 * +------------+-------------+------------+----------+
 * | "priority" | "high"      | "medium"   | "low"    |
 * +------------+-------------+------------+----------+
 * | "pri"      | "urgent"    | "med"      | "none"   |
 * +------------+-------------+------------+----------+
 * |            | "top"       | "normal"   | "no"     |
 * +------------+-------------+------------+----------+
 * |            | "crucial"   | "neutral"  | "zero"   |
 * +------------+-------------+------------+----------+
 * |            | "important" | "moderate" | "ignore" |
 * +------------+-------------+------------+----------+
 * 
 * Please delete the generate table comment:
 * To generate the table use : http://www.tablesgenerator.com/text_tables#
 * 
 */
//@formatter:on

public class PriorityParserTest {

    @Test
    public void test() 
    {
        parseValidPriorityWithTwoWords("pri urgent");
        parseValidPriorityWithMoreThanTwoWords("hey priority ignore");
        parseInvalidPriorityWithTwoWords("sec high");
        parseInvalidPriorityWithLessThanTwoWords("high");
    }

    private void parseInvalidPriorityWithLessThanTwoWords(String source) 
    {
        PriorityParser priorityParser = new PriorityParser();
        assertFalse(priorityParser.tryParse(source));
    }
    
    private void parseInvalidPriorityWithTwoWords(String source) 
    {
        PriorityParser priorityParser = new PriorityParser();
        assertFalse(priorityParser.tryParse(source));
    }

    private void parseValidPriorityWithMoreThanTwoWords(String source) 
    {
        PriorityParser priorityParser = new PriorityParser();
        assertTrue(priorityParser.tryParse(source));
        assertEquals(priorityParser.getPriority(), Priority.PRIORITY_LOW);
    }
    
    private void parseValidPriorityWithTwoWords(String source) 
    {
        PriorityParser priorityParser = new PriorityParser();
        assertTrue(priorityParser.tryParse(source));
        assertEquals(priorityParser.getPriority(), Priority.PRIORITY_HIGH);
    }

}
