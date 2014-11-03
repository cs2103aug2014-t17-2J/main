package ui;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class test extends JFrame{
     
    /**
     * 
     */
    private static final long serialVersionUID = -4626161399740482146L;

    test(){
         super();
         JTextArea text = new initialTextField();
         
         setTitle("Simple Text Application");
         
         getContentPane().add(text);          
         
    }
    
    class initialTextField extends JTextArea{
         /**
          * 
          */
         private static final long serialVersionUID = 1L;

         initialTextField(){
              super();
              
              initialShortcutKey(this);
              
              setColumns(40);
              setLineWrap(true);
              setRows(5);
              setEditable(true);
              
                             
         }
         
         void initialShortcutKey(JComponent component){
              
              /*
               * Never tried to use keyListener as it wouldn't work on component
               * For more detail on the reason of keyListener, please find the answer on your own
               * 
               * On swing, we have to specify which component is listen to key and which key
               * We have to use InputMap and ActionMap instead of KeyListener
               * 
               */
              
              InputMap im = component.getInputMap(JComponent.WHEN_FOCUSED);
              ActionMap am = component.getActionMap();
              
              /*
               * When the JTextArea is focused(Precisely JTextArea is focused all the times),
               * Component JTextArea is listened to space key by getKeyStroke( KeyEvent.VK_SPACE ,0 )  
              */
              im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0), "listenSpacebarKey");
              am.put("listenSpacebarKey", new AbstractAction(){

                   /**
                    * 
                    */
                   private static final long serialVersionUID = 1L;

                   @Override
                   public void actionPerformed(ActionEvent arg0) {
              }});
             
             im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK), 
                                               "listenCtrlzKey");
              am.put("listenCtrlzKey", new AbstractAction(){

                   /**
                    * 
                    */
                   private static final long serialVersionUID = 1L;

                   @Override
                   public void actionPerformed(ActionEvent arg0) {
                        
                        
                   }
              });
         }
    }
         
        
	public void main(String[] args){
        JFrame frame = new test();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.pack();
         frame.setLocation(250,250);
       frame.setVisible(true);
  }

}
