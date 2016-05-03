/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class StackDisplay extends JPanel {

    public Stack stack;
    private JLabel topLabel;
    public TextInterface textSetter;
    public Stack popStack;
    public StackElement[] rowElement;

    StackDisplay() {
        super();
        super.setLayout(new BorderLayout());

        stack = new Stack(8); //Object of type Stack class used to simulate the stack
        popStack = new Stack(3); //stack used to store recently popped elements, for undo
      
        topLabel = new JLabel("TOP-->"); //Label to point to top element in gui
        topLabel.setFont(new Font("Serif", Font.BOLD, 12)); 
        
        Box columnBox; 
        columnBox = Box.createVerticalBox(); //columnBox to hold all rows
        columnBox.add(Box.createRigidArea(new Dimension(0,30)));
        
        rowElement = new StackElement[stack.size+1];

        for (int i = 0; i < stack.size; i++) {
            rowElement[i] = new StackElement();         
            columnBox.add(rowElement[i]);
        }
      
        rowElement[stack.size] = new StackElement();
        rowElement[stack.size].topPanel.add(topLabel);
        rowElement[stack.size].eltPanel.setBorder(null);
        
        columnBox.add(rowElement[stack.size]);

        super.add(columnBox, BorderLayout.CENTER);
    }

    //Function to update GUI when push button is pressed
    public void update_push(int num, Stack undo,int stackNumber) {
        
        //Check if stack is full
        if (stack.top == stack.size - 1) {
            //Stack full message
            textSetter.setText("Stack "+ Integer.toString(stackNumber + 1) +
                    " is full! Cannot push more elements. Top = " + Integer.toString(stack.top));
            return;
        }
        
        if (undo.top == undo.size - 1) {
            undo.forgetEarlierChoice(); //Deleting earlier options - push or pop
        }
        
        undo.push(0); // 0 for push
        rowElement[stack.size - stack.nElts].topPanel.remove(topLabel); //Remove topLabel from current position
        stack.push(num); //Push new element in actual stack
        rowElement[stack.size - stack.nElts].topPanel.add(topLabel); //topLabel points to new top
        rowElement[stack.size - stack.nElts].elt.setText(Integer.toString(num)); //Display new element
        //Display message of successful push
        textSetter.setText(Integer.toString(num) + " has been pushed on to Stack "+ Integer.toString(stackNumber + 1) +
                ". Top = " + Integer.toString(stack.top));
    }

    public void update_pop(Stack undo,int stackNumber) {
        
        //Check if stack is empty
        if (stack.top == -1) {
            //Stack empty message
            textSetter.setText("Stack "+ Integer.toString(stackNumber + 1) +
                    " is empty! Cannot pop any element. Top = " + Integer.toString(stack.top));
            return;
        }
        
        if (undo.top == undo.size - 1) {
            undo.forgetEarlierChoice(); //Deleting earlier options - push or pop
        }
        if (popStack.top == popStack.size - 1) {
            popStack.forgetEarlierChoice(); //Deleting elements popped earlier from temporary stack
        }
        
        undo.push(1);//1 for pop
        rowElement[stack.size - stack.top - 1].elt.setText(""); //Remove top element from display
        rowElement[stack.size - stack.nElts].topPanel.remove(topLabel); //Remove topLabel from current position
        int num = stack.pop(); //Popping element from actual stack
        popStack.push(num); //Storing recently popped element for undo purpose
        rowElement[stack.size - stack.nElts].topPanel.add(topLabel); //Updating topLabel to point to new top
        //Display message of successful pop
        textSetter.setText(Integer.toString(num) + " has been popped from Stack "+ Integer.toString(stackNumber + 1)+
                ". Top = " + Integer.toString(stack.top));
    }

    public void undoPush(int stackNumber) {
        rowElement[stack.size - stack.top - 1].elt.setText(""); //Remove recently pushed element
        rowElement[stack.size - stack.nElts].topPanel.remove(topLabel); //Remove topLabel from current top
        stack.pop(); //Remove element from actual stack
        rowElement[stack.size - stack.nElts].topPanel.add(topLabel); //topLabel points to new top
        //Display message of successful undo
        textSetter.setText("Undo on Stack " + Integer.toString(stackNumber + 1)+
                " successful. Top = " + Integer.toString(stack.top));
    }

    public void undoPop(int stackNumber) {
        rowElement[stack.size - stack.nElts].topPanel.remove(topLabel); //Remove topLabel from current top
        stack.push(popStack.pop()); //Push back recently popped element
        rowElement[stack.size - stack.nElts].topPanel.add(topLabel); //topLabel points to new top
        //Display the element again
        rowElement[stack.size - stack.top - 1].elt.setText(Integer.toString(stack.getVal(stack.top)));
        //Display message of successful undo
        textSetter.setText("Undo on Stack " + Integer.toString(stackNumber + 1)+
                " successful. Top = " + Integer.toString(stack.top));
    }

    public void random(Stack undo,int stackNumber) throws InterruptedException {
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
            update_pop(undo,stackNumber);
        } else {
            update_push(getRandom.nextInt(100), undo,stackNumber);
        }
    }

    public void reset(int stackNumber) {
        rowElement[stack.size - stack.nElts].topPanel.remove(topLabel); //remove topLabel from current position
        
        //Clear actual stack
        stack.nElts = 0; 
        stack.top = -1;
        
        for (int i = 0; i < stack.size; i++) {
            rowElement[i].elt.setText(""); //Remove all elements from display
        }
        
        rowElement[stack.size - stack.nElts].topPanel.add(topLabel); //topLabel points to -1
        //Display reset message
        textSetter.setText("Stack "+ Integer.toString(stackNumber + 1)+
                " has been reset. Top = " + Integer.toString(stack.top));
    }
}
