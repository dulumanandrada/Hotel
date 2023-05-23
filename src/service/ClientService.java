package service;

import model.Client;
import repository.ClientRepository;

import java.util.List;

public class ClientService {
    private ClientRepository clientRepository;
    public ClientService() {
        this.clientRepository = new ClientRepository();
    }
    public Client build(String clientDetails) {
        String[] attributes = clientDetails.split("/");
        String name = attributes[0];
        int age = Integer.valueOf(attributes[1]);
        String email = attributes[2];
        return new Client(name, age, email);
    }
    public void printClients(List<Client> clients) {
        for(Client c : clients)
            System.out.println(c);
    }
    public void createClient(Client client) {
        clientRepository.createClient(client);
    }
    public void deleteClientById(long id) {
        clientRepository.deleteClientById(id);
    }
    public void updateClient(long id, Client client) {
        clientRepository.updateClient(id, client);
    }
    public List<Client> readAllClients() {
        return clientRepository.readAllClients();
    }

}
