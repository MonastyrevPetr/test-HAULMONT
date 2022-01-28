package test.testHAULMONT.service;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.testHAULMONT.model.Client;
import test.testHAULMONT.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public void save(Client client){
        clientRepository.save(client);
    }

    public Client findId(Long id){
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        clientRepository.deleteById(id);
    }


    public Client getClientByFio(String fio){
        return clientRepository.findByFio(fio).orElse(null);
    }
}
