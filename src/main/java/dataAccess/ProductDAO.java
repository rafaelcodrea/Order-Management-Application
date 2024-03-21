package dataAccess;

import model.Product;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product>{

    public boolean checkIdExists(int id){

        List<Product> list = findAll();
        for(Product pr: list){
            if(pr.getId() == id)
                return true;
        }
        return false;
    }

    public int getStockForId(int id){
        Product pr = findById(id);
        return pr.getSupply();
    }
}
