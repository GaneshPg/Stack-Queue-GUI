/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author DELL
 */
public class Frame extends JFrame {

    StackFrame stackFrame;
    StackSetup stackSetup;
    
    QueueFrame queueFrame;

    Frame() {
        super("Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JTabbedPane tab = new JTabbedPane(); //For the tabs at the top
        
        
        stackSetup = new StackSetup();
        stackSetup.createStackButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {              
                stackSetup.isDynamicStack = stackSetup.dynamicStackYes.isSelected();
                stackSetup.numberOfStacks = stackSetup.stackNumberComboBox.getSelectedIndex() + 1;
                stackFrame = new StackFrame(stackSetup.numberOfStacks,stackSetup.isDynamicStack);
                tab.setComponentAt(0,stackFrame);
            }
        });
        
        //stackPanel.setLayout(new BorderLayout());
        tab.addTab("Stack", stackSetup);
        tab.setMnemonicAt(0, KeyEvent.VK_1);
        
        /*
        //Stack heading and styling
        JLabel stackTitle = new JLabel("DATA STRUCTURES : DEMONSTRATION OF THE STACK");
        stackTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
        stackTitle.setForeground(Color.yellow);
        stackTitle.setPreferredSize(new Dimension(800, 80));
        JPanel stackTitlePanel = new JPanel();
        stackTitlePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 350, 0));
        stackTitlePanel.setBackground(Color.darkGray);
        stackTitlePanel.add(stackTitle);

        //Message display panel and styling
        //stackMessage = new JLabel(">>>Welcome to Stack Demo. An empty stack has been made. Stack Top = -1");
        stackMessage = new JLabel(">>>Please select the settings for the stack and press the create stack button");
        stackMessage.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        stackMessage.setForeground(Color.white);
        stackMessagePanel = new JPanel();
        
        stackMessagePanel.setBackground(Color.BLACK);
        stackMessagePanel.add(stackMessage);*/
        
        queueFrame = new QueueFrame();
        tab.addTab("Queue", queueFrame);
        tab.setMnemonicAt(1, KeyEvent.VK_2);
        
        add(tab);
        setVisible(true);
    }
}
