// application that act as simple markup

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

public class Editor {
		JTextPane pane;
		SimpleAttributeSet attributeSet;

		void fun(){

		pane = new JTextPane();
		JFrame frame = new JFrame("Editor");

		attributeSet = new SimpleAttributeSet();
      	StyleConstants.setItalic(attributeSet, true);
      	StyleConstants.setForeground(attributeSet, Color.red);

      	pane.setCharacterAttributes(attributeSet, true);

		pane.addKeyListener(new K_Listener());

		frame.add(new JScrollPane(pane),BorderLayout.CENTER);
		frame.setSize(new Dimension(300,400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	class K_Listener implements KeyListener{
		
		boolean newline = true;
		char previous_char='a';
		
		// returns start cursor position of newline
		int start_cursor_position(int position){
			position-=1;
			while(position>=0){
				try{
					if(pane.getDocument().getText(position,1).equals("\n"))
						return position;	
				}catch(Exception e){
					System.err.println("Error");
				}
				position = position - 1;
			}
			return -1;
		} 

		// returns end postion of newline
		int end_cursor_position(int position){
			while(true){
				try{
					if(pane.getDocument().getText(position,1).equals("\n"))
						return position;	
				}catch(Exception e){
					System.err.println("Error");
				}
				position = position + 1;
			}
		}

		// markup function for both global and local 
		void markup(String command){
			if(command.startsWith(".")){
				System.out.println("local function");
				
			}
		}
		public void keyPressed(KeyEvent event ){
			int position = pane.getCaretPosition();
			// System.out.println(System.getProperty("line.separator")+"");
			int start = start_cursor_position(position);
			int end = end_cursor_position(position);
			try{
				String command = pane.getDocument().getText(start+1,2);
				markup(command);
				}catch(Exception e){
					System.err.println("error in keypressed function: "+e);
				}
			System.out.println();
			// try{
			// 	System.out.println();
			// }
			// catch(Exception e){
			// 	System.out.println("Error");
			// }
			// System.out.println(position);

			// String text;
			// try{
			// 	text = pane.getDocument().getText(position,3);
			// }catch(Exception e){
			// 	text = "error";
			// }
			// System.out.println(text);
			// char ch = event.getKeyChar();
			
			// if(ch=='.' && newline){
			// 	System.out.println("Hello");
			// 	pane.setCharacterAttributes(new SimpleAttributeSet(), true);
			// }
			
			// else if(ch=='i' && newline && previous_char=='.'){

			// }

			// // should be last segment
			// else if(ch=='\n'){
			// 	System.out.println("Newline typed");
			// 	newline = true;
			// 	System.out.println(newline);
				
			// }

			// else{
			// 	newline = false;
			// }
		}
			
		// unwanted
		public void keyReleased(KeyEvent event){

		}
		public void keyTyped(KeyEvent event){

		}

	}
	
	public static void main(String[] args){	
		Editor e = new Editor();
		e.fun();
	}
}