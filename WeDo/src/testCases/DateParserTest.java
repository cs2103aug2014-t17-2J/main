/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.time.LocalDate;

import logic.parser.DateParser;

import org.junit.Test;

//@author A0112887X 
//@formatter:off
/**
 * 
 * Possible format for formal dates are
 * Date range [1..31]
 * Month Range [1..12]
 * Year Range [00..99] [0000...9999]
 *+-------------+--------------+-------------+
 *| Date Format | Month Format | Year Format |
 *+-------------+--------------+-------------+
 *| DD          | MM           | YYYY        |
 *+-------------+--------------+-------------+
 *| D           | M            | YY          |
 *+-------------+--------------+-------------+
 *
 * Invalid format for formal dates are
 *+---------------------+----------------------+---------------------+
 *| Invalid Date Format | Invalid Month Format | Invalid Year Format |
 *+---------------------+----------------------+---------------------+
 *| 0                   | 0                    | 100                 |
 *+---------------------+----------------------+---------------------+
 *| 32                  | 13                   | 10000               |
 *+---------------------+----------------------+---------------------+
 *
 * Possible arrangement for formal date are
 *+---------------------+---------------------+
 *| Date / Month / Year | Year / Month / Date |
 *+---------------------+---------------------+
 *| DD / MM / YYYY      | YYYY / MM / DD      |
 *+---------------------+---------------------+
 *| DD / MM / YY        |                     |
 *+---------------------+---------------------+
 *| D / MM / YYYY       | YYYY / M / DD       |
 *+---------------------+---------------------+
 *| D / MM / YY         |                     |
 *+---------------------+---------------------+
 *| DD / M / YYYY       | YYYY / MM / D       |
 *+---------------------+---------------------+
 *| DD / M / YY         |                     |
 *+---------------------+---------------------+
 *| D / M / YYYY        | YYYY / M / D        |
 *+---------------------+---------------------+
 *| D / M / YY          |                     |
 *+---------------------+---------------------+
 * 
 * Using heuristic evaluation, we can eliminate most of the test case leaving the following
 * for Date/Month/Year/format
 * +------+-------+-------+
 *| Date | Month | Year  |
 *+------+-------+-------+
 *| 1    | 1     | 00    |
 *+------+-------+-------+
 *| 31   | 12    | 9999  |
 *+------+-------+-------+
 *| 10   | 9     | 99    |
 *+------+-------+-------+
 *| 11   | 11    | 0000  |
 *+------+-------+-------+
 *| 0    | 5     | 88    |
 *+------+-------+-------+
 *| 32   | 8     | 1024  |
 *+------+-------+-------+
 *| 1    | 0     | 2048  |
 *+------+-------+-------+
 *| 30   | 13    | 74    |
 *+------+-------+-------+
 *| 11   | 11    | 100   |
 *+------+-------+-------+
 *| 1    | 1     | 10000 |
 *+------+-------+-------+
 *
 * Since month is still in the middle slot, we will only test Date and Year 
 * For Year/Month/Date format
 * +-------+-------+------+
 *| Year  | Month | Date |
 *+-------+-------+------+
 *| 0000  | 1     | 1    |
 *+-------+-------+------+
 *| 9999  | 12    | 31   |
 *+-------+-------+------+
 *| 999    | 9    | 10   |
 *+-------+-------+------+
 *| 10000 | 11    | 11   |
 *+-------+-------+------+
 *| 8888  | 5     | 0    |
 *+-------+-------+------+
 *| 1024  | 8     | 32   |
 *+-------+-------+------+
 */
//@formatter:on

public class DateParserTest {

    @Test
    public void test() {
        parseValidDateWithDMY("1/1/00");
        parseValidDateWithDDMMYY("31/12/9999");
        parseValidDateWithDDMYY("10/9/99");
        parseValidDateWithDDMMYYYY("12/11/0000");
        parseInvalidDateWithDMYY("0/5/88");
        parseInvalidDateWithDDMYYYY("32/8/1024");
        parseInvalidDateWithDMYYYY("1/0/2048");
        parseInvalidDateWithDDMMYY("30/13/74");
        parseInvalidDateWithDDMMYYY("11/11/100");
        parseInvalidDateWithDDMMYYYY("1/1/10000");
        parseValidDateWithYYYYMD("0000/1/1");
        parseValidDateWithYYYYMMDD("9999/12/31");
        parseInvalidDateWithYYYMDD("999/9/10");
        parseInvalidDateWithYYYYYMMDD("10000/11/11");
        parseInvalidDateWithYYYYMD("8888/5/0");
        parseInvalidDateWithYYYYMDD("1024/8/32");

    }

    private void parseInvalidDateWithYYYYMDD(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseInvalidDateWithYYYYMD(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseInvalidDateWithYYYYYMMDD(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseInvalidDateWithYYYMDD(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseValidDateWithYYYYMMDD(String source) {
        DateParser dateParser = new DateParser();
        assertTrue(dateParser.tryParse(source));

        assertEquals(dateParser.getEndDate(), LocalDate.of(9999, 12, 31));
    }

    private void parseValidDateWithYYYYMD(String source) {
        DateParser dateParser = new DateParser();
        assertTrue(dateParser.tryParse(source));

        assertEquals(dateParser.getEndDate(), LocalDate.of(0000, 1, 1));
    }

    private void parseInvalidDateWithDDMMYYYY(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseInvalidDateWithDDMMYYY(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseInvalidDateWithDDMMYY(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseInvalidDateWithDMYYYY(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseInvalidDateWithDDMYYYY(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseInvalidDateWithDMYY(String source) {
        DateParser dateParser = new DateParser();
        assertFalse(dateParser.tryParse(source));
    }

    private void parseValidDateWithDDMMYYYY(String source) {
        DateParser dateParser = new DateParser();
        assertTrue(dateParser.tryParse(source));
        assertEquals(dateParser.getEndDate(), LocalDate.of(0, 11, 12));
    }

    private void parseValidDateWithDDMYY(String source) {
        DateParser dateParser = new DateParser();
        assertTrue(dateParser.tryParse(source));

        assertEquals(dateParser.getEndDate(), LocalDate.of(2099, 9, 10));
    }

    private void parseValidDateWithDDMMYY(String source) {
        DateParser dateParser = new DateParser();
        assertTrue(dateParser.tryParse(source));

        assertEquals(dateParser.getEndDate(), LocalDate.of(9999, 12, 31));
    }

    private void parseValidDateWithDMY(String source) {
        DateParser dateParser = new DateParser();
        assertTrue(dateParser.tryParse(source));

        assertEquals(dateParser.getEndDate(), LocalDate.of(2000, 1, 1));
    }

}
