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
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class StackSetup extends JPanel{
    
    boolean isDynamicStack;
    int numberOfStacks;
    JLabel stackMessage;
    JPanel stackMessagePanel;
    JButton createStackButton;
    JCheckBox dynamicStackYes;
    JComboBox stackNumberComboBox;
    
    public StackSetup(){
        
        isDynamicStack = false; 
        numberOfStacks = 1;
        
        JPanel stackPanel = new JPanel();
        stackPanel.setLayout(new BorderLayout());
        
        JLabel stackTitle = new JLabel("DATA STRUCTURES : DEMONSTRATION OF THE STACK");
        stackTitle.setFont(new Font("Baskerville Old Face", Font.PLAIN, 25));
        stackTitle.setForeground(Color.yellow);
        stackTitle.setPreferredSize(new Dimension(800, 80));
        JPanel stackTitlePanel = new JPanel();
        stackTitlePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 350, 0));
        stackTitlePanel.setBackground(Color.darkGray);
        stackTitlePanel.add(stackTitle);
        
        //stackMessage = new JLabel(">>>Welcome to Stack Demo. An empty stack has been made. Stack Top = -1");
        stackMessage = new JLabel(">>>Please select the settings for the stack and press the create stack button");
        stackMessage.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
        stackMessage.setForeground(Color.white);
        stackMessagePanel = new JPanel();
        
        stackMessagePanel.setBackground(Color.BLACK);
        stackMessagePanel.add(stackMessage);
        
        Box topDisplay = Box.createVerticalBox();
        topDisplay.add(stackTitlePanel);
        topDisplay.add(stackMessagePanel);
        
        stackPanel.add(topDisplay,BorderLayout.NORTH);
        
        JPanel selectStackPanel = new JPanel();
        
        JLabel selectStackLabel = new JLabel("Select number of stacks :   ");
        String numbers[]={"1","2","3","4","5"};
        stackNumberComboBox = new JComboBox(numbers);
        stackNumberComboBox.setSelectedIndex(0);
        
        selectStackPanel.add(selectStackLabel);
        selectStackPanel.add(stackNumberComboBox);
        
        Box verticalBox=Box.createVerticalBox();  
        verticalBox.add(Box.createRigidArea(new Dimension(0,200)));
        verticalBox.add(selectStackPanel);
        verticalBox.add(Box.createRigidArea(new Dimension(0,10)));
        
        Box horizontalBox = Box.createHorizontalBox();
        
        JLabel dynamicStackLabel = new JLabel("Dynamic Stack :  ");
        
        dynamicStackYes = new JCheckBox("Yes");
        JCheckBox dynamicStackNo = new JCheckBox("No");
        
        ButtonGroup dynamicStackBtnGroup = new ButtonGroup();
        dynamicStackBtnGroup.add(dynamicStackYes);
        dynamicStackBtnGroup.add(dynamicStackNo);
        
        dynamicStackYes.setSelected(true);
        
        horizontalBox.add(dynamicStackLabel);
        horizontalBox.add(dynamicStackYes);
        horizontalBox.add(dynamicStackNo);
        
        verticalBox.add(horizontalBox);
        
        verticalBox.add(Box.createRigidArea(new Dimension(0,10)));
        
        createStackButton = new JButton("Create Stack");
        JPanel buttonPanel= new JPanel();
        buttonPanel.add(createStackButton);
        
        verticalBox.add(buttonPanel);
        
        JPanel alignPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,650,0));
        alignPanel.add(verticalBox);
        
        stackPanel.add(alignPanel);
        
        
        super.add(stackPanel);
       
    }
      
}
