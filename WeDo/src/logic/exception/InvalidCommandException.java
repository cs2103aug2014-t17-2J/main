/**
 * 
 */
package logic.exception;

//@author A0112887X
/**
 * Exception for invalid command
 */
@SuppressWarnings("serial")
public class InvalidCommandException extends Exception 
{

        public InvalidCommandException() { super(); }
        public InvalidCommandException(String message) { super(message); }
        public InvalidCommandException(String message, Throwable cause) { super(message, cause); }
        public InvalidCommandException(Throwable cause) { super(cause); }
      
}
