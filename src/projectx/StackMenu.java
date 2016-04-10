/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author DELL
 */
public class StackMenu extends JPanel {

    public JButton pushButton;
    public JButton popButton;
    public JButton resetButton;
    public JButton undoButton;
    public JButton randomButton;
    public JFormattedTextField text;
    public boolean undoEnabled, resetEnabled;

    public StackMenu() {

        super();

        //Border for Stack Menu Panel
        Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border titled = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        titled = BorderFactory.createTitledBorder(titled, "Stack Menu", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION,
                new Font("Serif", Font.PLAIN, 15), Color.BLACK);
        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY);
        Border compound = BorderFactory.createCompoundBorder(titled, empty);

        super.setBorder(BorderFactory.createCompoundBorder(compound, line));

        Box stackBox = Box.createHorizontalBox();

        JPanel pushPanel = new JPanel();
        pushPanel.setLayout(new FlowLayout()); //Layout for textfield and push button

        //Text field to enter a number to push
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(Integer.MIN_VALUE);
        formatter.setMaximum(Integer.MAX_VALUE);
        text = new JFormattedTextField(formatter); //TextField which only accepts valid integers
        text.setPreferredSize(new Dimension(80, 30));
        text.setToolTipText("enter a number to be pushed");

        //Push button properties
        pushButton = new JButton("Push");
        pushButton.setPreferredSize(new Dimension(70, 30));
        pushPanel.add(text);
        pushPanel.add(pushButton);
        pushPanel.setBackground(Color.lightGray);
        stackBox.add(pushPanel);

        //Pop button properties
        popButton = new JButton("Pop");
        popButton.setMinimumSize(new Dimension(150, 30));
        popButton.setPreferredSize(popButton.getMinimumSize());

        JPanel popPanel = new JPanel();
        popPanel.setLayout(new FlowLayout());
        popPanel.add(popButton);
        popPanel.setBackground(Color.lightGray);
        stackBox.add(popPanel);

        //Random button properties
        randomButton = new JButton("Random");
        randomButton.setMinimumSize(new Dimension(150, 30));
        randomButton.setPreferredSize(randomButton.getMinimumSize());

        JPanel randomPanel = new JPanel();
        randomPanel.setLayout(new FlowLayout());
        randomPanel.add(randomButton);
        randomPanel.setBackground(Color.lightGray);
        stackBox.add(randomPanel);

        //Undo button properties
        undoButton = new JButton("Undo");
        undoButton.setMinimumSize(new Dimension(150, 30));
        undoButton.setPreferredSize(undoButton.getMinimumSize());

        JPanel undoPanel = new JPanel();
        undoPanel.setLayout(new FlowLayout());
        undoButton.setEnabled(false);
        undoPanel.add(undoButton);
        undoPanel.setBackground(Color.lightGray);
        stackBox.add(undoPanel);

        //Reset button properties
        resetButton = new JButton("Reset");
        resetButton.setMinimumSize(new Dimension(150, 30));
        resetButton.setPreferredSize(resetButton.getMinimumSize());

        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(new FlowLayout());
        resetButton.setEnabled(false);
        resetPanel.add(resetButton);
        resetPanel.setBackground(Color.lightGray);
        stackBox.add(resetPanel);

        super.add(stackBox);
    }
}
