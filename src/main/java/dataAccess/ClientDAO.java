package dataAccess;

import model.Client;

import java.util.List;

public class ClientDAO extends AbstractDAO<Client>{

    public boolean checkIdExists(int id){

        List<Client> list = findAll();
        for(Client client: list){
            if(client.getId() == id)
                return true;
        }
        return false;
    }
}
