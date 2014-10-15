/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.util.Scanner;

import logic.taskParser.ParserManager;

import org.junit.Test;

/**
 * @author Kuan Tien Long
 *
 */
public class TaskParserUltiTest {

    @Test
    public void test() {
      Scanner sc = new Scanner(System.in);
      ParserManager ulti = new ParserManager();
      while(true)
      {
          String input = sc.nextLine();
        //  ulti.buildTask(new StringBuilder(input));

      }
    }

}
