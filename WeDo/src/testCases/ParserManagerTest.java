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
/**
 *
 */
public class ParserManagerTest {

    @Test
    public void test() {

        parseAddCommand();
        parseDeleteCommand();

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
