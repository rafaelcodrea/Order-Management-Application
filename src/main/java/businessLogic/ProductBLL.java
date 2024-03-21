package businessLogic;

import dataAccess.ProductDAO;
import model.Client;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {

    private ProductDAO productDAO;

    public ProductBLL() {
        this.productDAO = new ProductDAO();
    }

    public Product findProductById(int id) {
        Product pr = productDAO.findById(id);
        if (pr == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return pr;
    }

    public List<Product> findAllProducts(){
        List<Product> arr = productDAO.findAll();
        if (arr.size() == 0)
            throw new NoSuchElementException("There are no customers in the DB");
        return arr;
    }

    public void insertProduct(Product product){
        if (validateProduct(product)) {
            productDAO.insert(product);
        }
    }

    public void updateProduct(Product product){
        if (validateProduct(product))
            productDAO.update(product);
    }

    public void deleteProduct(int ID){
        productDAO.delete(ID);
    }

    /**
     * Checks if the Product is valid
     * @param p
     * @return boolean
     */

    public boolean validateProduct(Product p){
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        sb.append("|");

        if(p == null){
            sb.append(" Product is null | ");
            valid = false;
        }

        if(p.getSupply() < 0){
            sb.append(" Supply cannot be a negative number |");
            valid = false;
        }

        if(!valid){
            System.out.println(sb);
        }

        return valid;
    }
}
