package test.testHAULMONT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import test.testHAULMONT.model.Credit;
import test.testHAULMONT.service.CreditService;


@Controller
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping("/credits")
    public String getAllCredits(Model model){
        model.addAttribute("credits", creditService.getAll());
        return "credit/credit_list";
    }

    @GetMapping("/credit/info/{id}")
    public String getCreditInfo(@PathVariable Long id, Model model){
        model.addAttribute("credit", creditService.findId(id));
        return "credit/credit_info";
    }

    @PostMapping("/credit/info/{id}")
    public String updateCredit(Credit credit){
        creditService.save(credit);
        return "redirect:/credits";
    }

    @GetMapping("/credit/add")
    public String getCreditAddForm(){
        return "credit/credit_form";
    }

    @PostMapping("/credit/add")
    public String saveCredit(Credit credit){
        creditService.save(credit);
        return "redirect:/credits";
    }

    @PostMapping("/credit/delete/{id}")
    public String deleteCredit(@PathVariable Long id){
        creditService.deleteById(id);
        return "redirect:/credits";
    }
}
