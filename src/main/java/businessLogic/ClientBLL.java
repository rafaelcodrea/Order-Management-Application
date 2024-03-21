package businessLogic;

import dataAccess.ClientDAO;
import model.Client;

import javax.lang.model.type.NullType;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {

    private ClientDAO clientDAO;

    public ClientBLL() {
        this.clientDAO = new ClientDAO();
    }

    public List<Client> findAllClients(){
        List<Client> arr = clientDAO.findAll();
        if (arr.size() == 0)
            throw new NoSuchElementException("There are no customers in the DB");
        return arr;
    }

    public Client findCustomerById(int id) {
        Client cus = clientDAO.findById(id);
        if (cus == null) {
            throw new NoSuchElementException("The customer with id =" + id + " was not found!");
        }
        return cus;
    }

    public void insertClient(Client client){
        if (validateClient(client)) {
            clientDAO.insert(client);
        }
    }

    public void updateClient(Client client){
        if (validateClient(client))
            clientDAO.update(client);
    }

    public void deleteClient(int ID){
        clientDAO.delete(ID);
    }

    /**
     * Checks if the Client is valid
     * @param c
     * @return boolean
     */

    public boolean validateClient(Client c){
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        sb.append("|");

        if(c == null){
            sb.append(" Customer is null | ");
            valid = false;
        }

        if(c.getAge() < 16){
            sb.append(" Customer is not over 16 yr old |");
            valid = false;
        }

        if(!valid){
            System.out.println(sb);
        }

        return valid;
    }
}
