package businessLogic;

import dataAccess.InvoiceDAO;
import dataAccess.ProductDAO;
import fileWriter.FileWriter;
import model.Invoice;

import java.util.List;
import java.util.NoSuchElementException;


public class InvoiceBLL {

    private InvoiceDAO invoiceDAO ;
    private FileWriter fw;

    public InvoiceBLL(){
        invoiceDAO = new InvoiceDAO();
        fw = new FileWriter();
    }

    public Invoice findInvoiceById(int id) {
        Invoice pr = invoiceDAO.findById(id);
        if (pr == null) {
            throw new NoSuchElementException("The invoice with id =" + id + " was not found!");
        }
        return pr;
    }

    public List<Invoice> findAllInvoices(){
        List<Invoice> arr = invoiceDAO.findAll();
        if (arr.size() == 0)
            throw new NoSuchElementException("There are no invoices in the DB");
        return arr;
    }

    public void insertInvoice(Invoice invoice){
        if (validateInvoice(invoice)) {

            invoiceDAO.insert(invoice);
            Invoice inv = invoiceDAO.findAll().get(invoiceDAO.getRowCount() - 1);
            fw.writeLog(inv.toString());
        }
    }

    public void updateInvoice(Invoice invoice){
        if (validateInvoice(invoice))
            invoiceDAO.update(invoice);
    }

    public void deleteInvoice(Invoice invoice){
        Invoice inv = invoiceDAO.findById(invoice.getId());
        if (inv == null)
            throw new NoSuchElementException("The invoice with id =" + invoice.getId()+ " was not found!");
        invoiceDAO.delete(invoice.getId());
    }

    /**
     * Checks if the Invoice is valid
     * @param i
     * @return boolean
     */
    public boolean validateInvoice(Invoice i){
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        ProductDAO productDAO = new ProductDAO();
        sb.append("|");

        if(productDAO.getStockForId(i.getIdproduct()) < i.getQuantity()){
            sb.append(" Not enough supply for this Invoice |");
            valid = false;
        }

        if(!valid){
            System.out.println(sb);
        }

        return valid;
    }
}
