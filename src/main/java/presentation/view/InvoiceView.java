package presentation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class InvoiceView extends JPanel {
    private JTable jTable;
    private DefaultTableModel table;
    private JLabel jcomp2;
    private JTextField idTextInput;
    private JTextField idClientTextInput;
    private JLabel idLabel;
    private JLabel idClientLabel;
    private JTextField ageTextInput;
    private JLabel quantityLabel;
    private JTextField idProductTextInput;
    private JLabel idPorductLabel;
    private JButton insertBtn;
    private JFrame frameInvoice;

    public InvoiceView() {

        //construct components
        jTable = new JTable();
        table = new DefaultTableModel();
        jcomp2 = new JLabel ("_______________________________________________________________________________________________________________________");
        idTextInput = new JTextField (5);
        idClientTextInput = new JTextField (5);
        idLabel = new JLabel ("ID:");
        idClientLabel = new JLabel ("ID Client:");
        ageTextInput = new JTextField (5);
        quantityLabel = new JLabel ("Quantity:");
        idProductTextInput = new JTextField (5);
        idPorductLabel = new JLabel ("ID Product:");
        insertBtn = new JButton ("Insert");

        //adjust size and set layout
        setPreferredSize (new Dimension(645, 454));
        setLayout (null);

        JScrollPane scrl = new JScrollPane(jTable);

        //add components
        add (scrl);
        add (jcomp2);
        add (idTextInput);
        add (idClientTextInput);
        add (idLabel);
        add (idClientLabel);
        add (ageTextInput);
        add (quantityLabel);
        add (idProductTextInput);
        add (idPorductLabel);
        add (insertBtn);

        //set component bounds (only needed by Absolute Positioning)
        scrl.setBounds (10, 115, 625, 330);
        jcomp2.setBounds (-120, 80, 830, 25);

        idTextInput.setBounds (385, 20, 100, 25);
        idClientTextInput.setBounds (385, 65, 100, 25);

        idLabel.setBounds (385, 0, 100, 25);
        idClientLabel.setBounds (385, 45, 100, 25);

        ageTextInput.setBounds (510, 20, 100, 25);
        quantityLabel.setBounds (510, 0, 100, 25);

        idProductTextInput.setBounds (510, 65, 100, 25);
        idPorductLabel.setBounds (510, 45, 100, 25);

        insertBtn.setBounds (10, 20, 100, 25);

        frameInvoice = new JFrame ("Orders Managment");
        frameInvoice.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frameInvoice.getContentPane().add (this);
        frameInvoice.pack();
    }

    public void makeVisible(){
        frameInvoice.setVisible(true);
    }

    public void setTableColumns(String[][] data, String[] column){
        jTable.setModel(new DefaultTableModel(data,column));
    }

    public String getIDInput(){
        return idTextInput.getText();
    }
    public String getIDClientInput(){
        return idClientTextInput.getText();
    }
    public String getIDProductInput(){
        return idProductTextInput.getText();
    }
    public String getQuantityInput(){
        return ageTextInput.getText();
    }

    public void clearFields(){
        idTextInput.setText("");
        idClientTextInput.setText("");
        idProductTextInput.setText("");
        ageTextInput.setText("");
    }

    public void addInsertListener(ActionListener actionListener){
        insertBtn.addActionListener(actionListener);
    }
}
