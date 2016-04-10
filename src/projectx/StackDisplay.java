/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author DELL
 */
public class StackDisplay extends JPanel {

    public Stack stack;
    private JPanel eltPanel[];
    private JLabel[] dispSquareArray;
    private JLabel topLabel;
    private JPanel topPanel[];
    public TextInterface textSetter;
    public Stack popStack;

    StackDisplay() {
        super();
        super.setLayout(new BorderLayout());

        stack = new Stack(8); //Object of type Stack class used to simulate the stack
        popStack = new Stack(3); //stack used to store recently popped elements, for undo

        dispSquareArray = new JLabel[stack.size + 1]; //Label array to display elements
        eltPanel = new JPanel[stack.size + 1]; //Panel array to hold labels
        
        topLabel = new JLabel("TOP-->"); //Label to point to top element in gui
        topLabel.setFont(new Font("Serif", Font.BOLD, 12)); 
        topPanel = new JPanel[stack.size + 1]; //Panel to hold topLabel
        
        Box columnBox, rowBox[]; 
        columnBox = Box.createVerticalBox(); //columnBox to hold all rows
        rowBox = new Box[stack.size + 1]; //rowBox array to hold topPanel and eltPanel

        //Borders used on elements in GUI
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border cmpnd = BorderFactory.createCompoundBorder(border, empty);

        for (int i = 0; i < stack.size; i++) {
            dispSquareArray[i] = new JLabel(""); //Empty label
            
            topPanel[i] = new JPanel();
            topPanel[i].setPreferredSize(new Dimension(40, 30));
            
            eltPanel[i] = new JPanel();
            eltPanel[i].setBorder(cmpnd);
            eltPanel[i].setPreferredSize(new Dimension(55, 45));
            eltPanel[i].add(dispSquareArray[i]);
            
            rowBox[i] = Box.createHorizontalBox();
            rowBox[i].add(topPanel[i]);
            rowBox[i].add(eltPanel[i]);
            
            columnBox.add(rowBox[i]);
        }
        
        dispSquareArray[stack.size] = new JLabel("");
        eltPanel[stack.size] = new JPanel();
        eltPanel[stack.size].setPreferredSize(new Dimension(55, 45));
        
        topPanel[stack.size] = new JPanel();
        topPanel[stack.size].add(topLabel); //Top pointing to -1 location
        topPanel[stack.size].setPreferredSize(new Dimension(40, 30));
        
        rowBox[stack.size] = Box.createHorizontalBox();
        rowBox[stack.size].add(topPanel[stack.size]);
        rowBox[stack.size].add(eltPanel[stack.size]);
        
        columnBox.add(rowBox[stack.size]);

        super.add(columnBox, BorderLayout.CENTER);
    }

    //Function to update GUI when push button is pressed
    public void update_push(int num, Stack undo) {
        
        //Check if stack is full
        if (stack.top == stack.size - 1) {
            //Stack full message
            textSetter.setText("The stack is full! Cannot push more elements. Top = " + Integer.toString(stack.top));
            return;
        }
        
        if (undo.top == undo.size - 1) {
            undo.forgetEarlierChoice(); //Deleting earlier options - push or pop
        }
        
        undo.push(0); // 0 for push
        topPanel[stack.size - stack.nElts].remove(topLabel); //Remove topLabel from current position
        stack.push(num); //Push new element in actual stack
        topPanel[stack.size - stack.nElts].add(topLabel); //topLabel points to new top
        dispSquareArray[stack.size - stack.nElts].setText(Integer.toString(num)); //Display new element
        //Display message of successful push
        textSetter.setText(Integer.toString(num) + " has been pushed on to the stack. Top = " + Integer.toString(stack.top));
    }

    public void update_pop(Stack undo) {
        
        //Check if stack is empty
        if (stack.top == -1) {
            //Stack empty message
            textSetter.setText("The stack is empty! Cannot pop. Top = " + Integer.toString(stack.top));
            return;
        }
        
        if (undo.top == undo.size - 1) {
            undo.forgetEarlierChoice(); //Deleting earlier options - push or pop
        }
        if (popStack.top == popStack.size - 1) {
            popStack.forgetEarlierChoice(); //Deleting elements popped earlier from temporary stack
        }
        
        undo.push(1);//1 for pop
        dispSquareArray[stack.size - stack.top - 1].setText(""); //Remove top element from display
        topPanel[stack.size - stack.nElts].remove(topLabel); //Remove topLabel from current position
        int num = stack.pop(); //Popping element from actual stack
        popStack.push(num); //Storing recently popped element for undo purpose
        topPanel[stack.size - stack.nElts].add(topLabel); //Updating topLabel to point to new top
        //Display message of successful pop
        textSetter.setText(Integer.toString(num) + " has been popped from the stack. Top = " + Integer.toString(stack.top));
    }

    public void undoPush() {
        dispSquareArray[stack.size - stack.top - 1].setText(""); //Remove recently pushed element
        topPanel[stack.size - stack.nElts].remove(topLabel); //Remove topLabel from current top
        stack.pop(); //Remove element from actual stack
        topPanel[stack.size - stack.nElts].add(topLabel); //topLabel points to new top
        //Display message of successful undo
        textSetter.setText("Undo successful. Top = " + Integer.toString(stack.top));
    }

    public void undoPop() {
        topPanel[stack.size - stack.nElts].remove(topLabel); //Remove topLabel from current top
        stack.push(popStack.pop()); //Push back recently popped element
        topPanel[stack.size - stack.nElts].add(topLabel); //topLabel points to new top
        //Display the element again
        dispSquareArray[stack.size - stack.top - 1].setText(Integer.toString(stack.getVal(stack.top)));
        //Display message of successful undo
        textSetter.setText("Undo successful. Top = " + Integer.toString(stack.top));
    }

    public void random(Stack undo) throws InterruptedException {
        int choice;
        Random getRandom = new Random(); //object which gets random numbers
        if (stack.nElts == stack.size) {
            choice = 1; //if stack full, then pop
        } else if (stack.nElts == 0) {
            choice = 0; //if stack empty, then push
        } else {
            int r = getRandom.nextInt(10 + this.stack.nElts); //random number
            if (r < 10) {
                choice = 0; //random operation is push
            } else {
                choice = 1; //random operation is pop
            }
        }
        
        if (choice == 1) {
            update_pop(undo);
        } else {
            update_push(getRandom.nextInt(100), undo);
        }
    }

    public void reset() {
        topPanel[stack.size - stack.nElts].remove(topLabel); //remove topLabel from current position
        
        //Clear actual stack
        stack.nElts = 0; 
        stack.top = -1;
        
        for (int i = 0; i < stack.size; i++) {
            dispSquareArray[i].setText(""); //Remove all elements from display
        }
        
        topPanel[stack.size - stack.nElts].add(topLabel); //topLabel points to -1
        //Display reset message
        textSetter.setText("Stack has been reset. Top = " + Integer.toString(stack.top));
    }
}
