package presentation.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OptionView extends JPanel {
    private JButton clientButton;
    private JLabel label;
    private JButton productButton;
    private JButton invoiceButton;
    private JFrame frameOption;

    public OptionView() {
        //construct components
        clientButton = new JButton ("Manage Clients");
        label = new JLabel ("Order Managing System");
        productButton = new JButton ("Manage Products");
        invoiceButton = new JButton ("Manage Orders");

        //adjust size and set layout
        setPreferredSize (new Dimension (250, 208));
        setLayout (null);

        //add components
        add (clientButton);
        add (label);
        add (productButton);
        add (invoiceButton);

        //set component bounds (only needed by Absolute Positioning)
        clientButton.setBounds (25, 60, 200, 25);
        label.setBounds (55, 20, 140, 30);
        productButton.setBounds (25, 100, 200, 25);
        invoiceButton.setBounds (25, 140, 200, 25);

        frameOption = new JFrame ("Main Panel");
        frameOption.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frameOption.getContentPane().add (this);
        frameOption.pack();
        frameOption.setVisible (true);
    }

    public void addClientListener(ActionListener actionListener){
        clientButton.addActionListener(actionListener);
    }

    public void addProductListener(ActionListener actionListener){
        productButton.addActionListener(actionListener);
    }

    public void addInvoiceListener(ActionListener actionListener){
        invoiceButton.addActionListener(actionListener);
    }
}
