package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import userInterface.UserIntSwing;
import edu.emory.mathcs.backport.java.util.Collections;

public class AutoCompleteMenu extends KeyAdapter{
	 AutoSuggestor autoSuggestor = new AutoSuggestor(UserIntSwing.textField, UserIntSwing.frame, 
			 null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
         @Override
         boolean wordTyped(String typedWord) {

             //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
             ArrayList<String> words = new ArrayList<>();
             words.add("hello");
             words.add("heritage");
             words.add("happiness");
             words.add("goodbye");
             words.add("cruel");
             words.add("car");
             words.add("war");
             words.add("will");
             words.add("world");
             words.add("wall");

             setDictionary(words);
             //addToDictionary("bye");//adds a single word

             return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
         }
     };
}
