/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import logic.command.commandList.AddCommand;
import logic.command.commandList.Command;
import logic.command.commandList.DeleteCommand;
import logic.parser.ParseResult;
import logic.parser.ParserManager;
import logic.utility.Task;

import org.junit.Test;

import definedEnumeration.Priority;

//@author A0112887X 
//@formatter:off
/**
 * For Flexible command testing:
 * 
 * Testing for all permutation for flexible command (4 words)
 * +-------------+-------------+-------------+-------------+
 * | command     | description | date        | priority    |
 * +-------------+-------------+-------------+-------------+
 * | command     | description | priority    | date        |
 * +-------------+-------------+-------------+-------------+
 * | command     | date        | description | priority    |
 * +-------------+-------------+-------------+-------------+
 * | command     | date        | priority    | description |
 * +-------------+-------------+-------------+-------------+
 * | command     | priority    | description | date        |
 * +-------------+-------------+-------------+-------------+
 * | command     | priority    | date        | description |
 * +-------------+-------------+-------------+-------------+
 * | description | command     | priority    | date        |
 * +-------------+-------------+-------------+-------------+
 * | description | command     | date        | priority    |
 * +-------------+-------------+-------------+-------------+
 * | description | date        | priority    | command     |
 * +-------------+-------------+-------------+-------------+
 * | description | date        | command     | priority    |
 * +-------------+-------------+-------------+-------------+
 * | description | priority    | date        | command     |
 * +-------------+-------------+-------------+-------------+
 * | description | priority    | command     | date        |
 * +-------------+-------------+-------------+-------------+
 * | date        | command     | description | priority    |
 * +-------------+-------------+-------------+-------------+
 * | date        | command     | priority    | description |
 * +-------------+-------------+-------------+-------------+
 * | date        | description | command     | priority    |
 * +-------------+-------------+-------------+-------------+
 * | date        | description | priority    | command     |
 * +-------------+-------------+-------------+-------------+
 * | date        | priority    | command     | description |
 * +-------------+-------------+-------------+-------------+
 * | date        | priority    | description | command     |
 * +-------------+-------------+-------------+-------------+
 * | priority    | command     | date        | description |
 * +-------------+-------------+-------------+-------------+
 * | priority    | command     | description | date        |
 * +-------------+-------------+-------------+-------------+
 * | priority    | description | date        | command     |
 * +-------------+-------------+-------------+-------------+
 * | priority    | description | command     | date        |
 * +-------------+-------------+-------------+-------------+
 * | priority    | date        | description | command     |
 * +-------------+-------------+-------------+-------------+
 * | priority    | date        | command     | description |
 * +-------------+-------------+-------------+-------------+
 
 * Testing for all permutation for flexible command (3 words)
 * +-------------+-------------+-------------+
 * | command     | date        | description |
 * +-------------+-------------+-------------+
 * | command     | description | date        |
 * +-------------+-------------+-------------+
 * | description | date        | command     |
 * +-------------+-------------+-------------+
 * | description | command     | date        |
 * +-------------+-------------+-------------+
 * | date        | description | command     |
 * +-------------+-------------+-------------+
 * | date        | command     | description |
 * +-------------+-------------+-------------+
 * | command     | priority    | description |
 * +-------------+-------------+-------------+
 * | command     | description | priority    |
 * +-------------+-------------+-------------+
 * | description | priority    | command     |
 * +-------------+-------------+-------------+
 * | description | command     | priority    |
 * +-------------+-------------+-------------+
 * | priority    | description | command     |
 * +-------------+-------------+-------------+
 * | priority    | command     | description |
 * +-------------+-------------+-------------+
 * 
 * Testing for all permutation for flexible command (2 words)
 * 
 * +-------------+-------------+
 * | command     | description |
 * +-------------+-------------+
 * | description | command     |
 * +-------------+-------------+
 */
//@formatter:on

public class ParserManagerTest {

    @Test
    public void test() {

        parseAddCommand();
        parseDeleteCommand();
        parseFlexibleOrdering();

    }

    private void parseFlexibleOrdering() {
        String expectedDescription = "eat rice";
        LocalDate expectedEndDate = LocalDate.now();
        LocalTime expectedEndTime = LocalTime.of(15, 0);
        Priority expectedPriority = Priority.PRIORITY_HIGH;

        Task expectedTaskfor4Words = new Task(expectedDescription,
                expectedPriority, expectedEndDate, expectedEndTime);
        Task expectedTaskfor3WordsWithDate = new Task(expectedDescription,
                Task.PRIORITY_NOT_SET, expectedEndDate, expectedEndTime);
        Task expectedTaskfor3WordsWithPriority = new Task(expectedDescription,
                expectedPriority);
        Task expectedTaskfor2Words = new Task(expectedDescription,
                Task.PRIORITY_NOT_SET);

        String command = "add";
        String description = "eat rice";
        String date = "today 3pm";
        String priority = "pri high";

        // Testing for permutation of flexible command (refer to initial
        // comments)
        parseFlexibleAdd(expectedTaskfor4Words, command, description, date,
                priority);
        parseFlexibleAdd(expectedTaskfor4Words, command, description, priority,
                date);
        parseFlexibleAdd(expectedTaskfor4Words, command, date, description,
                priority);
        parseFlexibleAdd(expectedTaskfor4Words, command, date, priority,
                description);
        parseFlexibleAdd(expectedTaskfor4Words, command, priority, description,
                date);
        parseFlexibleAdd(expectedTaskfor4Words, command, priority, date,
                description);
        parseFlexibleAdd(expectedTaskfor4Words, description, command, priority,
                date);
        parseFlexibleAdd(expectedTaskfor4Words, description, command, date,
                priority);
        parseFlexibleAdd(expectedTaskfor4Words, description, date, priority,
                command);
        parseFlexibleAdd(expectedTaskfor4Words, description, date, command,
                priority);
        parseFlexibleAdd(expectedTaskfor4Words, description, priority, date,
                command);
        parseFlexibleAdd(expectedTaskfor4Words, description, priority, command,
                date);
        parseFlexibleAdd(expectedTaskfor4Words, date, command, description,
                priority);
        parseFlexibleAdd(expectedTaskfor4Words, date, command, priority,
                description);
        parseFlexibleAdd(expectedTaskfor4Words, date, description, command,
                priority);
        parseFlexibleAdd(expectedTaskfor4Words, date, description, priority,
                command);
        parseFlexibleAdd(expectedTaskfor4Words, date, priority, command,
                description);
        parseFlexibleAdd(expectedTaskfor4Words, date, priority, description,
                command);
        parseFlexibleAdd(expectedTaskfor4Words, priority, command, date,
                description);
        parseFlexibleAdd(expectedTaskfor4Words, priority, command, description,
                date);
        parseFlexibleAdd(expectedTaskfor4Words, priority, description, date,
                command);
        parseFlexibleAdd(expectedTaskfor4Words, priority, description, command,
                date);
        parseFlexibleAdd(expectedTaskfor4Words, priority, date, description,
                command);
        parseFlexibleAdd(expectedTaskfor4Words, priority, date, command,
                description);

        parseFlexibleAdd(expectedTaskfor3WordsWithDate, command, date,
                description);
        parseFlexibleAdd(expectedTaskfor3WordsWithDate, command, description,
                date);
        parseFlexibleAdd(expectedTaskfor3WordsWithDate, description, date,
                command);
        parseFlexibleAdd(expectedTaskfor3WordsWithDate, description, command,
                date);
        parseFlexibleAdd(expectedTaskfor3WordsWithDate, date, description,
                command);
        parseFlexibleAdd(expectedTaskfor3WordsWithDate, date, command,
                description);

        parseFlexibleAdd(expectedTaskfor3WordsWithPriority, command, priority,
                description);
        parseFlexibleAdd(expectedTaskfor3WordsWithPriority, command,
                description, priority);
        parseFlexibleAdd(expectedTaskfor3WordsWithPriority, description,
                priority, command);
        parseFlexibleAdd(expectedTaskfor3WordsWithPriority, description,
                command, priority);
        parseFlexibleAdd(expectedTaskfor3WordsWithPriority, priority,
                description, command);
        parseFlexibleAdd(expectedTaskfor3WordsWithPriority, priority, command,
                description);

        parseFlexibleAdd(expectedTaskfor2Words, command, description);
        parseFlexibleAdd(expectedTaskfor2Words, description, command);
        

    }
    
    private void parseSplitDescription(String... flexibleOrdering) {
//        parseSplitDescription(command, description, date ,description);

        String input = "";

        for (String set : flexibleOrdering) {
            input += (" " + set);
        }

       
        assertFailedMessage(input);

    }

    private void assertFailedMessage(String input) {
        final String FAILED_MESSAGE = "Description Should not be separeted";
        ParserManager parserManager = new ParserManager();
        assertEquals(FAILED_MESSAGE, parserManager.interpret(input).getFailedMessage());
    }

    private void parseFlexibleAdd(Task expectedTask, String... flexibleOrdering) {
        String input = "";

        for (String set : flexibleOrdering) {
            input += (" " + set);
        }

        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse(input, expectedResult);

    }

    private void parseDeleteCommand() {
        parseValidDeleteWithIntegerRange();
        parseValidDeleteWithMultipleInteger();
        parseValidDeleteWithInteger();
        parseValidDeleteWithNegativeIntegerRange();
        parseValidDeleteWithMultipleNegativeInteger();
        parseValidDeleteWithNegativeInteger();
    }

    private void parseAddCommand() {
        parseValidAddWithNoDate();
        parseValidAddWith1DateAndPriority();
        parseValidAddWith2DateAndPriority();
        parseValidAddWithPriorityAndDate();
        parseValidAddWith2Date2TimeAndPriority();
        parseValidAddWithPriority2Date2Time();
        parseValidAddWith_Priority2Date2Time();
        parseValidPriorityWith2Date2TimeAndAdd();
        parseValidFormalDateWithPriorityAndAdd();
        parseValidFormalDateWithAddAndPriority();
        parseValidAddWithNumberDescriptionAndTime();
        parseValidAddBakeCookieBug();
        parseValidAddWithNumberEndingDescription();
        parseValidAddWithNumberFollowedByDate();
    }

    private void parseValidDeleteWithNegativeInteger() {
        Task expectedTask = new Task("-10", Task.PRIORITY_NOT_SET);
        Command expectedCommand = new DeleteCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("delete -10", expectedResult);
    }

    private void parseValidDeleteWithMultipleNegativeInteger() {
        Task expectedTask = new Task("-2,-3,-10", Task.PRIORITY_NOT_SET);
        Command expectedCommand = new DeleteCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("delete -2,-3,-10", expectedResult);
    }

    private void parseValidDeleteWithNegativeIntegerRange() {
        Task expectedTask = new Task("-2 to 10", Task.PRIORITY_NOT_SET);
        Command expectedCommand = new DeleteCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("delete -2 to 10", expectedResult);
    }

    private void parseValidDeleteWithIntegerRange() {
        Task expectedTask = new Task("2 to 10", Task.PRIORITY_NOT_SET);
        Command expectedCommand = new DeleteCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("delete 2 to 10", expectedResult);
    }

    private void parseValidDeleteWithMultipleInteger() {
        Task expectedTask = new Task("2,3,4,5 and 1,9", Task.PRIORITY_NOT_SET);
        Command expectedCommand = new DeleteCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("delete 2,3,4,5 and 1,9", expectedResult);
    }

    private void parseValidDeleteWithInteger() {
        Task expectedTask = new Task("10", Task.PRIORITY_NOT_SET);
        Command expectedCommand = new DeleteCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("delete 10", expectedResult);
    }

    private void parseValidAddWithNumberFollowedByDate() {
        Task expectedTask = new Task("meeting", Task.PRIORITY_NOT_SET,
                LocalDate.now(), LocalTime.of(3, 0));
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("add meeting 3 today", expectedResult);
    }

    private void parseValidAddWithNumberEndingDescription() {
        Task expectedTask = new Task("meeting3", Task.PRIORITY_NOT_SET,
                LocalDate.now(), Task.TIME_NOT_SET);
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("add meeting3 today", expectedResult);
    }

    private void parseValidAddBakeCookieBug() {
        Task expectedTask = new Task("bake cookies", Task.PRIORITY_NOT_SET,
                LocalDate.now(), Task.TIME_NOT_SET);
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("add bake cookies today", expectedResult);
    }

    private void parseValidAddWithNumberDescriptionAndTime() {
        Task expectedTask = new Task(
                "hello page 321 to 232 and pg 23123 to 21321",
                Task.PRIORITY_NOT_SET, LocalDate.now(), LocalTime.of(20, 0),
                LocalDate.now(), LocalTime.of(22, 0));
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse(
                "add hello page 321 to 232 and pg 23123 to 21321 at 8pm to 10 pm",
                expectedResult);

    }

    private void parseValidFormalDateWithAddAndPriority() {
        Task expectedTask = new Task("buy for me something",
                Priority.PRIORITY_MEDIUM, LocalDate.of(2014, 9, 18),
                LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2,
                        0));

        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse(
                "date 18/09 2pm to 22/9 2am add buy for me something priority med",
                expectedResult);

    }

    private void parseValidFormalDateWithPriorityAndAdd() {
        Task expectedTask = new Task("buy for me something",
                Priority.PRIORITY_MEDIUM, LocalDate.of(2014, 9, 18),
                LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2,
                        0));
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse(
                "18/09/14 14:00 to 22/9/14 2:00 med priority add buy for me something",
                expectedResult);
    }

    private void parseValidPriorityWith2Date2TimeAndAdd() {
        Task expectedTask = new Task("buy for me something",
                Priority.PRIORITY_MEDIUM, LocalDate.of(2014, 9, 18),
                LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2,
                        0));
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse(
                "med priority date 18/09/14 2pm to 22/9/14 2am add buy for me something",
                expectedResult);
    }

    private void parseValidAddWith_Priority2Date2Time() {
        Task expectedTask = new Task("buy for me something",
                Priority.PRIORITY_MEDIUM, LocalDate.of(2014, 9, 18),
                LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2,
                        0));
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse(
                "add buy for me something med priority date 18/09/14 2pm to 22/9/14 2am ",
                expectedResult);

    }

    private void parseValidAddWithPriority2Date2Time() {
        Task expectedTask = new Task("buy for me something",
                Priority.PRIORITY_MEDIUM, LocalDate.of(2014, 9, 18),
                LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2,
                        0));
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse(
                "add buy for me something priority med date 18 sept 2pm to 22 sept 2am ",
                expectedResult);
    }

    private void parseValidAddWith2Date2TimeAndPriority() {
        Task expectedTask = new Task("buy for me something",
                Priority.PRIORITY_MEDIUM, LocalDate.of(2014, 9, 18),
                LocalTime.of(14, 0), LocalDate.of(2014, 9, 22), LocalTime.of(2,
                        0));
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse(
                "add buy for me something date 18 sept 2pm to 22 sept 2am med priority",
                expectedResult);

    }

    private void parseValidAddWithPriorityAndDate() {
        Task expectedTask = new Task("i wan to eat this",
                Priority.PRIORITY_LOW, LocalDate.of(2014, 12, 12),
                Task.TIME_NOT_SET);
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("add i wan to eat this priority low on 12 dec",
                expectedResult);
    }

    private void parseValidAddWith2DateAndPriority() {
        Task expectedTask = new Task("buy for me something",
                Priority.PRIORITY_LOW, LocalDate.of(2014, 9, 12),
                Task.TIME_NOT_SET, LocalDate.of(2014, 9, 18), Task.TIME_NOT_SET);
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse(
                "add buy for me something date 12 sept to 18 sept priority low",
                expectedResult);
    }

    /**
     * 
     */
    private void parseValidAddWith1DateAndPriority() {

        Task expectedTask = new Task("momo", Priority.PRIORITY_HIGH,
                LocalDate.of(2014, 9, 12), Task.TIME_NOT_SET);
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);

        assertValidParse("add momo by 12 sept priority high", expectedResult);
    }

    private void parseValidAddWithNoDate() {
        Task expectedTask = new Task("floating task", Task.PRIORITY_NOT_SET);
        Command expectedCommand = new AddCommand();
        ParseResult expectedResult = new ParseResult(expectedCommand,
                expectedTask);
        assertValidParse("add floating task", expectedResult);
    }

    private void assertValidParse(String input, ParseResult expectedResult) {
        ParserManager parserManager = new ParserManager();
        getNextUniqueID(expectedResult);
        assertEquals(expectedResult, parserManager.interpret(input));

    }

    private void getNextUniqueID(ParseResult expectedResult) {
        expectedResult.getTask().setUniqueID(Task.getCreateID());
    }
}
