package presentation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductView extends JPanel {
    private JTable jTable;
    private DefaultTableModel table;
    private JLabel jcomp2;
    private JTextField idTextInput;
    private JTextField nameTextInput;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextField priceTextInput;
    private JLabel priceLabel;
    private JTextField supplyTextInput;
    private JLabel supplyLabel;
    private JButton deleteBtn;
    private JButton insertBtn;
    private JButton updateBtn;
    private JFrame frameProduct;

    public ProductView() {
        //construct components
        jTable = new JTable();
        table = new DefaultTableModel();
        table = new DefaultTableModel();
        jcomp2 = new JLabel ("_______________________________________________________________________________________________________________________");
        idTextInput = new JTextField (5);
        nameTextInput = new JTextField (5);
        idLabel = new JLabel ("ID:");
        nameLabel = new JLabel ("Name:");
        priceTextInput = new JTextField (5);
        priceLabel = new JLabel ("Price:");
        supplyTextInput = new JTextField (5);
        supplyLabel = new JLabel ("Supply:");
        deleteBtn = new JButton ("Delete");
        insertBtn = new JButton ("Insert");
        updateBtn = new JButton ("Update");

        //adjust size and set layout
        setPreferredSize (new Dimension(645, 454));
        setLayout (null);

        JScrollPane scrl = new JScrollPane(jTable);

        //add components
        add (scrl);
        add (jcomp2);
        add (idTextInput);
        add (nameTextInput);
        add (idLabel);
        add (nameLabel);
        add (priceTextInput);
        add (priceLabel);
        add (supplyTextInput);
        add (supplyLabel);
        add (deleteBtn);
        add (insertBtn);
        add (updateBtn);

        //set component bounds (only needed by Absolute Positioning)
        scrl.setBounds (10, 115, 625, 330);
        jcomp2.setBounds (-120, 80, 830, 25);
        idLabel.setBounds (385, 0, 100, 25);
        nameLabel.setBounds (385, 45, 100, 25);
        priceLabel.setBounds (510, 0, 100, 25);
        supplyLabel.setBounds (510, 45, 100, 25);

        supplyTextInput.setBounds (510, 65, 100, 25);
        priceTextInput.setBounds (510, 20, 100, 25);
        idTextInput.setBounds (385, 20, 100, 25);
        nameTextInput.setBounds (385, 65, 100, 25);
        deleteBtn.setBounds (135, 20, 100, 25);
        insertBtn.setBounds (10, 20, 100, 25);
        updateBtn.setBounds (260, 20, 100, 25);

        frameProduct = new JFrame ("Product Managment");
        frameProduct.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frameProduct.getContentPane().add (this);
        frameProduct.pack();
    }

    public void makeVisible(){
        frameProduct.setVisible(true);
    }

    public void setTableColumns(String[][] data, String[] column){
        jTable.setModel(new DefaultTableModel(data,column));
    }

    public void clearFields(){
        idTextInput.setText("");
        nameTextInput.setText("");
        priceTextInput.setText("");
        supplyTextInput.setText("");
    }

    public String getIDInput(){
        return idTextInput.getText();
    }
    public String getNameInput(){
        return nameTextInput.getText();
    }
    public String getSupplyInput(){
        return supplyTextInput.getText();
    }
    public String getPriceInput(){
        return priceTextInput.getText();
    }

    public void addInsertListener(ActionListener actionListener){
        insertBtn.addActionListener(actionListener);
    }
    public void addUpdateListener(ActionListener actionListener){
        updateBtn.addActionListener(actionListener);
    }
    public void addDeleteListener(ActionListener actionListener){
        deleteBtn.addActionListener(actionListener);
    }
}
