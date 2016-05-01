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
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;

/**
 *
 * @author DELL
 */
public class Frame extends JFrame {

    int stackChoice;
    Stack stk_undo;
    boolean stk_isRandom;
    StackDisplay stackDisplay;
    JPanel stackDisplayPanel;
    StackMenu stackMenu;
    JPanel stackMenuPanel;
    JPanel stackPanel;
    JLabel stackMessage;
    JPanel stackMessagePanel;

    int qChoice;
    Stack q_undo;
    boolean q_isRandom;
    QDisplay qDisplay;
    JPanel qDisplayPanel;
    QueueMenu qMenu;
    JPanel qMenuPanel;
    JPanel qPanel;
    JLabel qMessage;
    JPanel qMessagePanel;

    Frame() {
        super("Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JTabbedPane tab = new JTabbedPane(); //For the tabs at the top
        
        JScrollPane sp = new JScrollPane();
                
        stackPanel = new JPanel();
        stackPanel.setLayout(new BorderLayout());
        tab.addTab("Stack", stackPanel);
        tab.setMnemonicAt(0, KeyEvent.VK_1);

        //Stack heading and styling
        JLabel stackTitle = new JLabel("DATA STRUCTURES : DEMONSTRATION OF THE STACK");
        stackTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
        stackTitle.setForeground(Color.yellow);
        stackTitle.setPreferredSize(new Dimension(800, 80));
        JPanel stackTitlePanel = new JPanel();
        stackTitlePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 350, 0));
        stackTitlePanel.setBackground(Color.darkGray);
        stackTitlePanel.add(stackTitle);

        //stackPanel.add(stackTitlePanel, BorderLayout.NORTH);

        stk_undo = new Stack(3);
        stk_isRandom = false;

        //Stack Menu and styling
        stackMenu = new StackMenu();
        stackMenu.setBackground(Color.DARK_GRAY);
        stackMenuPanel = new JPanel();
        stackMenuPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));
        stackMenuPanel.setBackground(Color.darkGray);
        stackMenu.setPreferredSize(new Dimension(1350, 100));
        stackMenuPanel.add(stackMenu);
        stackMenuPanel.add(Box.createRigidArea(new Dimension(0, 100)));

        //Stack display and styling
        stackDisplay = new StackDisplay();
        stackDisplayPanel = new JPanel();
        stackDisplayPanel.add(Box.createRigidArea(new Dimension(0, 425)));
        stackDisplayPanel.add(stackDisplay);
        stackDisplayPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));

        //Message display panel and styling
        stackMessage = new JLabel(">>>Welcome to Stack Demo. An empty stack has been made. Stack Top = -1");
        stackMessage.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        stackMessage.setForeground(Color.white);
        stackMessagePanel = new JPanel();
        //stackMessagePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        
        stackMessagePanel.setBackground(Color.BLACK);
        stackMessagePanel.add(stackMessage);

        Box stackDisplayBox = Box.createHorizontalBox();
        stackDisplayBox.add(stackDisplayPanel);
        StackDisplay s1 = new StackDisplay();
        JPanel stackDisplayPanel1 = new JPanel();
        stackDisplayPanel1.add(Box.createRigidArea(new Dimension(0, 425)));
        stackDisplayPanel1.add(s1);
        stackDisplayPanel1.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));
        stackDisplayBox.add(stackDisplayPanel1);
        //stackDisplayBox.add(stackMessagePanel);
        sp.setViewportView(stackDisplayBox);
        sp.setVisible(true);

        stackPanel.add(sp, BorderLayout.CENTER);
        Box topDisplay = Box.createVerticalBox();
        topDisplay.add(stackTitlePanel);
        topDisplay.add(stackMessagePanel);
        topDisplay.add(stackMenuPanel);
        stackPanel.add(topDisplay, BorderLayout.NORTH);
        stackPanel.updateUI();

        //Interface to set the text in the Message Panel
        stackDisplay.textSetter = new TextInterface() {
            public void setText(String str) {
                stackMessage.setText(">>>" + str);
            }
        };

        addStackActionListeners(); //Function to add the actionlisteners to the buttons

        qPanel = new JPanel();
        qPanel.setLayout(new BorderLayout());
        tab.addTab("Queue", qPanel);
        tab.setMnemonicAt(1, KeyEvent.VK_2);

        //Queue title and styling
        JLabel qTitle = new JLabel("DATA STRUCTURES : DEMONSTRATION OF THE QUEUE");
        qTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
        qTitle.setForeground(Color.yellow);
        qTitle.setPreferredSize(new Dimension(800, 80));
        JPanel qTitlePanel = new JPanel();
        qTitlePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 450, 0));
        qTitlePanel.setBackground(Color.darkGray);
        qTitlePanel.add(qTitle);

        qPanel.add(qTitlePanel, BorderLayout.NORTH);

        q_undo = new Stack(3);
        q_isRandom = false;

        //Queue menu and styling
        qMenu = new QueueMenu();
        qMenu.setBackground(Color.DARK_GRAY);
        qMenuPanel = new JPanel();
        qMenuPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));
        qMenuPanel.setBackground(Color.darkGray);
        qMenu.setPreferredSize(new Dimension(1350, 100));
        qMenuPanel.add(qMenu);
        qMenuPanel.add(Box.createRigidArea(new Dimension(0, 100)));

        //Queue display and styling
        qDisplay = new QDisplay();
        qDisplayPanel = new JPanel();
        qDisplayPanel.add(Box.createRigidArea(new Dimension(0, 425)));
        qDisplayPanel.add(qDisplay);
        qDisplayPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));

        //Queue message panel and styling
        qMessage = new JLabel(">>>Welcome to Queue Demo. An empty queue has been made. Front = 0, Rear = -1");
        qMessage.setForeground(Color.white);
        qMessagePanel = new JPanel();
        qMessagePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        qMessagePanel.setBackground(Color.BLACK);
        qMessagePanel.add(qMessage);

        Box qDisplayBox = Box.createVerticalBox();
        qDisplayBox.add(qDisplayPanel);
        qDisplayBox.add(qMessagePanel);

        qPanel.add(qDisplayBox, BorderLayout.CENTER);
        qPanel.add(qMenuPanel, BorderLayout.SOUTH);
        qPanel.updateUI();

        //Interface to set the text in Queue Message Panel
        qDisplay.textSetter = new TextInterface() {
            public void setText(String str) {
                qMessage.setText(">>>" + str);
            }
        };

        addQueueActionListeners(); //Function to add actionlisteners to queue menu buttons

        add(tab);
        setVisible(true);
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
                    stackDisplay.update_push(num, stk_undo);
                    stackMenu.resetButton.setEnabled(true);
                    stackMenu.resetEnabled = true;
                } else {
                    stackDisplay.textSetter.setText("Invalid number");
                }
                if (stk_undo.top != -1) {
                    stackMenu.undoButton.setEnabled(true);
                    stackMenu.undoEnabled = true;
                }
                stackPanel.updateUI();
            }
        });

        //Pop Button on click actionlistener
        stackMenu.popButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                stackDisplay.update_pop(stk_undo);
                if (stk_undo.top != -1) {
                    stackMenu.undoButton.setEnabled(true);
                }
                if (stackDisplay.stack.top == -1 && stk_undo.top == -1) {
                    stackMenu.resetButton.setEnabled(false);
                }
                stackPanel.updateUI();
            }
        });

        //Undo Button on click actionlistener
        stackMenu.undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int choice = stk_undo.pop();
                if (choice == 0) {
                    //Recent operation was push
                    stackDisplay.undoPush();
                    if (stackDisplay.stack.top == -1 && stk_undo.top == -1) {
                        stackMenu.resetButton.setEnabled(false);
                    }
                } else {
                    //Recent operation was pop
                    stackDisplay.undoPop();
                    stackMenu.resetButton.setEnabled(true);

                }
                if (stk_undo.top == -1) {
                    stackMenu.undoButton.setEnabled(false);
                    stackMenu.undoEnabled = false;
                }
                stackPanel.updateUI();
            }
        });

        //Random operations Button on click actionlistener
        stackMenu.randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!stk_isRandom) {
                    stk_isRandom = true;
                    stackMenu.randomButton.setText("Stop");
                    stackMenu.text.setEnabled(false);
                    stackMenu.pushButton.setEnabled(false);
                    stackMenu.popButton.setEnabled(false);
                    stackMenu.undoButton.setEnabled(false);
                    stackMenu.resetButton.setEnabled(false);
                    startStackRandom();
                } else {
                    stk_isRandom = false;
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
                stk_undo.top = -1;
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
                stackDisplay.random(stk_undo);
                if (stk_undo.top != -1) {
                    stackMenu.undoEnabled = true;
                }
                stackMenu.resetEnabled = true;
                stackPanel.updateUI();
                Thread.sleep(750);
                return null;
            }

            protected void done() {
                if (stk_isRandom) {
                    startStackRandom();
                }
            }
        };
        stk_random.execute();
    }

    private void addQueueActionListeners() {

        //Queue Insert button on click actionlistener
        qMenu.insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String str = qMenu.text.getText();
                qMenu.text.setValue(null);
                if (isNum(str)) {
                    str = str.replace(",", "");
                    int num = Integer.parseInt(str);
                    qDisplay.update_insert(num, q_undo);
                    qMenu.resetButton.setEnabled(true);
                    qMenu.resetEnabled = true;
                } else {
                    qDisplay.textSetter.setText("Invalid number");
                }
                if (q_undo.top != -1) {
                    qMenu.undoButton.setEnabled(true);
                    qMenu.undoEnabled = true;
                }
                qPanel.updateUI();
            }
        });

        //Queue Delete button onclick actionlistener
        qMenu.deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                qDisplay.update_delete(q_undo);
                if (q_undo.top != -1) {
                    qMenu.undoButton.setEnabled(true);
                }
                if (qDisplay.q.rear == -1 && qDisplay.q.front == 0 && stk_undo.top == -1) {
                    qMenu.resetButton.setEnabled(false);
                }
                qPanel.updateUI();
            }
        });

        //Queue undo button onclick actionlistener
        qMenu.undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int choice = q_undo.pop();
                if (choice == 0) {
                    qDisplay.undoInsert();
                    if (qDisplay.q.rear == -1 && qDisplay.q.front == 0 && stk_undo.top == -1) {
                        qMenu.resetButton.setEnabled(false);
                    }
                } else {
                    qDisplay.undoDelete();
                    qMenu.resetButton.setEnabled(true);

                }
                if (q_undo.top == -1) {
                    qMenu.undoButton.setEnabled(false);
                    qMenu.undoEnabled = false;
                }
                qPanel.updateUI();
            }
        });

        //Queue random button onclick actionlistener
        qMenu.randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!q_isRandom) {
                    q_isRandom = true;
                    qMenu.randomButton.setText("Stop");
                    qMenu.text.setEnabled(false);
                    qMenu.insertButton.setEnabled(false);
                    qMenu.deleteButton.setEnabled(false);
                    qMenu.undoButton.setEnabled(false);
                    qMenu.resetButton.setEnabled(false);
                    startQueueRandom();
                } else {
                    q_isRandom = false;
                    qMenu.randomButton.setText("Random");
                    qMenu.text.setEnabled(true);
                    qMenu.insertButton.setEnabled(true);
                    qMenu.deleteButton.setEnabled(true);
                    qMenu.undoButton.setEnabled(qMenu.undoEnabled);
                    qMenu.resetButton.setEnabled(qMenu.resetEnabled);
                }
            }
        });

        //Queue reset button onclick actionlistener
        qMenu.resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                qDisplay.reset();
                q_undo.top = -1;
                qMenu.undoButton.setEnabled(false);
                qMenu.undoEnabled = false;
                qMenu.resetButton.setEnabled(false);
                qMenu.resetEnabled = false;
                qPanel.updateUI();
            }
        });
    }

    //Function to implement automatic random operations in Queue
    private void startQueueRandom() {
        SwingWorker<Void, Void> q_random;
        q_random = new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                if (qDisplay.q.front == qDisplay.q.size) {
                    qDisplay.textSetter.setText("Cannot perform any more operations on the queue. Please try using the undo or the reset buttons to continue. Front = " + Integer.toString(qDisplay.q.front) + " Rear = " + Integer.toString(qDisplay.q.rear));
                    qMenu.randomButton.doClick();
                    return null;
                }
                Thread.sleep(100);
                qDisplay.random(q_undo);
                if (q_undo.top != -1) {
                    qMenu.undoEnabled = true;
                }
                qMenu.resetEnabled = true;
                qPanel.updateUI();
                Thread.sleep(750);
                return null;
            }

            protected void done() {
                if (q_isRandom) {
                    startQueueRandom();
                }
            }
        };
        q_random.execute();
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
