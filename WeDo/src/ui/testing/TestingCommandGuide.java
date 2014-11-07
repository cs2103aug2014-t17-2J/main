package ui.testing;

import static org.junit.Assert.*;

import java.awt.AWTException;

import javax.swing.JButton;

import org.junit.Test;

import ui.UserInterfaceMain;
import ui.guide.CommandGuide;
import ui.guide.FeedbackGuide;
import ui.logic.command.FeedbackHandler;
import userInterface.UserIntSwing;

/**
 // @author A0112636M
 * Testing on the GUI 
 */
public class TestingCommandGuide {

	@Test //all cases
	public void test() throws AWTException {
		labelShouldDisplayCorrectCommandGeneralGuide();
		labelShouldDisplayCorrectCommandAddGuide();
		labelShouldDisplayCorrectCommandViewGuide();
		labelShouldDisplayCorrectCommandEditGuide();
		labelShouldDisplayCorrectCommandDeleteGuide();
	}
	
	//Junit testing on the General Command Guide
	@Test public void labelShouldDisplayCorrectCommandGeneralGuide() throws AWTException {
		String expectedResult = CommandGuide.buildGeneralGuideString();
		String testingResult;
		String command = "any other words";
		
		testingResult = CommandGuide.getGuideMessage(command);
		assertEquals(expectedResult, testingResult);
	}
	
	//Junit testing on the Add Command Guide
	@Test public void labelShouldDisplayCorrectCommandAddGuide() throws AWTException {
		String expectedResult = CommandGuide.buildGeneralGuideString();
		String testingResult;
		String command = "add";
		
		testingResult = CommandGuide.getGuideMessage(command);
		assertEquals(expectedResult, testingResult);
	}
	
	//Junit testing on the View Command Guide
	@Test public void labelShouldDisplayCorrectCommandViewGuide() throws AWTException {
		String expectedResult = CommandGuide.buildGeneralGuideString();
		String testingResult;
		String command = "view";
		
		testingResult = CommandGuide.getGuideMessage(command);
		assertEquals(expectedResult, testingResult);
	}
	
	//Junit testing on the Edit Command Guide
	@Test public void labelShouldDisplayCorrectCommandEditGuide() throws AWTException {
		String expectedResult = CommandGuide.buildEditGuideString();
		String testingResult;
		String command = "edit";
		
		testingResult = CommandGuide.getGuideMessage(command);
		assertEquals(expectedResult, testingResult);
	}
	
	//Junit testing on the Delete Command Guide
	@Test public void labelShouldDisplayCorrectCommandDeleteGuide() throws AWTException {
		String expectedResult = CommandGuide.buildGeneralGuideString();
		String testingResult;
		String command = "delete something";
		
		testingResult = CommandGuide.getGuideMessage(command);
		assertEquals(expectedResult, testingResult);
	}
	
}
