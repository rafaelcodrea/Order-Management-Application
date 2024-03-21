package presentation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JPanel {
    private JTable jTable;
    private DefaultTableModel table;

    private JLabel jcomp2;
    private JTextField idTextInput;
    private JTextField nameTextInput;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextField ageTextInput;
    private JLabel ageLabel;
    private JTextField addressTextInput;
    private JLabel addressLabel;
    private JButton deleteBtn;
    private JButton insertBtn;
    private JButton updateBtn;
    private JFrame frameClient;

    public ClientView() {

        jTable = new JTable();
        table = new DefaultTableModel();

        jcomp2 = new JLabel ("_______________________________________________________________________________________________________________________");
        idTextInput = new JTextField (5);
        nameTextInput = new JTextField (5);
        idLabel = new JLabel ("ID:");
        nameLabel = new JLabel ("Name:");
        ageTextInput = new JTextField (5);
        ageLabel = new JLabel ("Age:");
        addressTextInput = new JTextField (5);
        addressLabel = new JLabel ("Address:");
        deleteBtn = new JButton ("Delete");
        insertBtn = new JButton ("Insert");
        updateBtn = new JButton ("Update");

        setPreferredSize (new Dimension(645, 454));
        setLayout (null);

        JScrollPane scrl = new JScrollPane(jTable);

        add (scrl);
        add (jcomp2);
        add (idTextInput);
        add (nameTextInput);
        add (idLabel);
        add (nameLabel);
        add (ageTextInput);
        add (ageLabel);
        add (addressTextInput);
        add (addressLabel);
        add (deleteBtn);
        add (insertBtn);
        add (updateBtn);

        idTextInput.setBounds (385, 20, 100, 25);
        nameTextInput.setBounds (385, 65, 100, 25);
        ageTextInput.setBounds (510, 20, 100, 25);
        addressTextInput.setBounds (510, 65, 100, 25);

        deleteBtn.setBounds (135, 20, 100, 25);
        insertBtn.setBounds (10, 20, 100, 25);
        updateBtn.setBounds (260, 20, 100, 25);

        scrl.setBounds (10, 115, 625, 330);
        jcomp2.setBounds (-120, 80, 830, 25);
        idLabel.setBounds (385, 0, 100, 25);
        nameLabel.setBounds (385, 45, 100, 25);
        ageLabel.setBounds (510, 0, 100, 25);
        addressLabel.setBounds (510, 45, 100, 25);

        frameClient = new JFrame ("Client Managment");
        frameClient.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frameClient.getContentPane().add (this);
        frameClient.pack();
    }

    public void makeVisible(){
        frameClient.setVisible(true);
    }

    public void setTableColumns(String[][] data, String[] column){
        jTable.setModel(new DefaultTableModel(data,column));
    }

    public String getIDInput(){
        return idTextInput.getText();
    }
    public String getNameInput(){
        return nameTextInput.getText();
    }
    public String getAgeInput(){
        return ageTextInput.getText();
    }
    public String getAddressInput(){
        return addressTextInput.getText();
    }

    public void clearFields(){
        idTextInput.setText("");
        nameTextInput.setText("");
        ageTextInput.setText("");
        addressTextInput.setText("");
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
