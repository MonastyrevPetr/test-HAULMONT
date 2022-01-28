package test.testHAULMONT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import test.testHAULMONT.model.Client;
import test.testHAULMONT.service.ClientService;


@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String getAllClients(Model model){
        model.addAttribute("clients", clientService.getAll());
        return "client/client_list";
    }

    @GetMapping("/client/info/{id}")
    public String getClientInfo(@PathVariable Long id, Model model){
        model.addAttribute("client", clientService.findId(id));
        return "client/client_info";
    }

    @PostMapping("/client/info/{id}")
    public String updateClient(Client client){
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("client/add")
    public String getClientAddForm(){
        return "client/client_form";
    }

    @PostMapping("client/add")
    public String saveClient(Client client){
        clientService.save(client);
        return "redirect:/clients";
    }

    @PostMapping("client/delete/{id}")
    public String deleteClient(@PathVariable Long id){
        clientService.deleteById(id);
        return "redirect:/clients";
    }
}
