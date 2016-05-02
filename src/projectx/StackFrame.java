/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

/**
 *
 * @author DELL
 */
public class StackFrame extends JPanel{
    
    Stack choiceStack;
    boolean isRandom;
    StackDisplay stackDisplay;
    JPanel stackDisplayPanel;
    StackMenu stackMenu;
    JPanel stackMenuPanel;
    JPanel stackPanel;
    JLabel stackMessage;
    JPanel stackMessagePanel;
    
    StackFrame(int numberOfStacks,boolean isDynamicStack){
        
        choiceStack = new Stack(3);
        isRandom = false;

        JScrollPane scrollPane = new JScrollPane();
        
        stackPanel = new JPanel(new BorderLayout());
        
        //Stack title and styling
        JLabel stackTitle = new JLabel("DATA STRUCTURES : DEMONSTRATION OF THE STACK");
        stackTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
        stackTitle.setForeground(Color.yellow);
        stackTitle.setPreferredSize(new Dimension(800, 80));
        
        JPanel stackTitlePanel = new JPanel();
        stackTitlePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 350, 0));
        stackTitlePanel.setBackground(Color.darkGray);
        stackTitlePanel.add(stackTitle);
        
        //Stack message panel and styling
        stackMessage = new JLabel(">>>Stack setup has been made as requested. Perform operations from the menu");
        stackMessage.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        stackMessage.setForeground(Color.white);
        
        stackMessagePanel = new JPanel();
        stackMessagePanel.setBackground(Color.BLACK);
        stackMessagePanel.add(stackMessage);
        
        //Stack Menu and styling
        stackMenu = new StackMenu();
        stackMenu.setBackground(Color.DARK_GRAY);
        
        stackMenuPanel = new JPanel();
        stackMenuPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));
        stackMenuPanel.setBackground(Color.darkGray);
        stackMenu.setPreferredSize(new Dimension(1350, 100));
        stackMenuPanel.add(stackMenu);
        stackMenuPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        
        Box topDisplay = Box.createVerticalBox();
        topDisplay.add(stackTitlePanel);
        topDisplay.add(stackMessagePanel);
        topDisplay.add(stackMenuPanel);
        
        stackPanel.add(topDisplay, BorderLayout.NORTH);

        //Stack display and styling
        stackDisplay = new StackDisplay();
        stackDisplayPanel = new JPanel();
        stackDisplayPanel.add(Box.createRigidArea(new Dimension(0, 425)));
        stackDisplayPanel.add(stackDisplay);
        stackDisplayPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));

        Box stackDisplayBox = Box.createHorizontalBox();
        stackDisplayBox.add(stackDisplayPanel);
        /*StackDisplay s1 = new StackDisplay();
        JPanel stackDisplayPanel1 = new JPanel();
        stackDisplayPanel1.add(Box.createRigidArea(new Dimension(0, 425)));
        stackDisplayPanel1.add(s1);
        stackDisplayPanel1.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));
        stackDisplayBox.add(stackDisplayPanel1);*/
        scrollPane.setViewportView(stackDisplayBox);
        scrollPane.setVisible(true);
        
        stackPanel.add(scrollPane, BorderLayout.CENTER);
        
        super.add(stackPanel);

        //Interface to set the text in the Message Panel
        stackDisplay.textSetter = new TextInterface() {
            public void setText(String str) {
                stackMessage.setText(">>>" + str);
            }
        };

        addStackActionListeners(); //Function to add the actionlisteners to the buttons
    }
    
     private void addStackActionListeners() {

        //Push Button on click actionlistener
        stackMenu.pushButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String str = stackMenu.text.getText();
                stackMenu.text.setValue(null);
                if (isNum(str)) {
                    str = str.replace(",", "");
                    int num = Integer.parseInt(str);
                    stackDisplay.update_push(num, choiceStack);
                    stackMenu.resetButton.setEnabled(true);
                    stackMenu.resetEnabled = true;
                } else {
                    stackDisplay.textSetter.setText("Invalid number");
                }
                if (choiceStack.top != -1) {
                    stackMenu.undoButton.setEnabled(true);
                    stackMenu.undoEnabled = true;
                }
                stackPanel.updateUI();
            }
        });

        //Pop Button on click actionlistener
        stackMenu.popButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                stackDisplay.update_pop(choiceStack);
                if (choiceStack.top != -1) {
                    stackMenu.undoButton.setEnabled(true);
                }
                if (stackDisplay.stack.top == -1 && choiceStack.top == -1) {
                    stackMenu.resetButton.setEnabled(false);
                }
                stackPanel.updateUI();
            }
        });

        //Undo Button on click actionlistener
        stackMenu.undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int choice = choiceStack.pop();
                if (choice == 0) {
                    //Recent operation was push
                    stackDisplay.undoPush();
                    if (stackDisplay.stack.top == -1 && choiceStack.top == -1) {
                        stackMenu.resetButton.setEnabled(false);
                    }
                } else {
                    //Recent operation was pop
                    stackDisplay.undoPop();
                    stackMenu.resetButton.setEnabled(true);

                }
                if (choiceStack.top == -1) {
                    stackMenu.undoButton.setEnabled(false);
                    stackMenu.undoEnabled = false;
                }
                stackPanel.updateUI();
            }
        });

        //Random operations Button on click actionlistener
        stackMenu.randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!isRandom) {
                    isRandom = true;
                    stackMenu.randomButton.setText("Stop");
                    stackMenu.text.setEnabled(false);
                    stackMenu.pushButton.setEnabled(false);
                    stackMenu.popButton.setEnabled(false);
                    stackMenu.undoButton.setEnabled(false);
                    stackMenu.resetButton.setEnabled(false);
                    startStackRandom();
                } else {
                    isRandom = false;
                    stackMenu.randomButton.setText("Random");
                    stackMenu.text.setEnabled(true);
                    stackMenu.pushButton.setEnabled(true);
                    stackMenu.popButton.setEnabled(true);
                    stackMenu.undoButton.setEnabled(stackMenu.undoEnabled);
                    stackMenu.resetButton.setEnabled(stackMenu.resetEnabled);
                }
            }
        });

        //Reset Button on click actionlistener
        stackMenu.resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                stackDisplay.reset();
                choiceStack.top = -1;
                stackMenu.undoButton.setEnabled(false);
                stackMenu.undoEnabled = false;
                stackMenu.resetButton.setEnabled(false);
                stackMenu.resetEnabled = false;
                stackPanel.updateUI();
            }
        });
    }

    //Function to implement automatic random operations in Stack
    private void startStackRandom() {
        SwingWorker<Void, Void> stk_random = new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception { //Repeatedly performs random operations on a background thread
                Thread.sleep(100);
                stackDisplay.random(choiceStack);
                if (choiceStack.top != -1) {
                    stackMenu.undoEnabled = true;
                }
                stackMenu.resetEnabled = true;
                stackPanel.updateUI();
                Thread.sleep(750);
                return null;
            }

            protected void done() {
                if (isRandom) {
                    startStackRandom();
                }
            }
        };
        stk_random.execute();
    }
    
    //Function to check if the input string is a valid number or not
    private static boolean isNum(String str) {
        str = str.replace(",", "");
        try {
            Integer x = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
