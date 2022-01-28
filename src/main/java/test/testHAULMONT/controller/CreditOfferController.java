package test.testHAULMONT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import test.testHAULMONT.dto.CreditOfferShotDto;
import test.testHAULMONT.model.CreditOffer;
import test.testHAULMONT.model.Payment;
import test.testHAULMONT.service.ClientService;
import test.testHAULMONT.service.CreditOfferService;
import test.testHAULMONT.service.CreditService;
import test.testHAULMONT.service.PaymentService;


@Controller
public class CreditOfferController {
    private final CreditOfferService creditOfferService;
    private final ClientService clientService;
    private final CreditService creditService;
    private final PaymentService paymentService;
    private CreditOfferShotDto creditOffertemp;

    public CreditOfferShotDto getCreditOffertemp() {
        return creditOffertemp;
    }

    public void setCreditOffertemp(CreditOfferShotDto creditOffertemp) {
        this.creditOffertemp = creditOffertemp;
    }

    public CreditOfferController(CreditOfferService creditOfferService, ClientService clientService, CreditService creditService, PaymentService paymentService) {
        this.creditOfferService = creditOfferService;
        this.clientService = clientService;
        this.creditService = creditService;
        this.paymentService = paymentService;
        creditOffertemp = new CreditOfferShotDto();
    }

    @GetMapping("/credits/offer")
    public String getAllCredits(Model model){
        model.addAttribute("credits_offer", creditOfferService.getAll());

        return "creditOffer/credit_offer_list";
    }

    @GetMapping("/credit/offer/info/{id}")
    public String getCreditInfo(@PathVariable Long id, Model model){
        model.addAttribute("credit", creditOfferService.findId(id));
        return "creditOffer/credit_offer_info";
    }

    @GetMapping("/credit/offer/add")
    public String getCreditAddForm(Model model){
        model.addAttribute("creditOffer", new CreditOfferShotDto());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("credits", creditService.getAll());
        return "creditOffer/credit_offer_form";
    }

   @GetMapping("/credit/offer/calculate")
    public String calculate(Model model){
        creditOffertemp.setPayments(creditOfferService.calculate(creditOffertemp.getSumma(), creditOffertemp.getPercent()));
        model.addAttribute("creditOffer", creditOffertemp);
        return "creditOffer/credit_offer_calculate";
    }



    @PostMapping("/credit/offer/calculate")
    public String calc(){
        CreditOfferShotDto creditOfferShotDto = new CreditOfferShotDto();
        creditOfferShotDto.setClient(creditOffertemp.getClient());
        creditOfferShotDto.setPercent(creditOffertemp.getPercent());
        creditOfferShotDto.setSumma(creditOffertemp.getSumma());
        CreditOffer creditOffer = creditOfferService.creditOfferShotDtoToCreditOffer(creditOfferShotDto);
        creditOfferService.save(creditOffer);
        for (Payment payment:creditOffertemp.getPayments()) {
            payment.setCreditOffer(creditOfferService.findId(creditOffer.getId()));
        }
        paymentService.saveAll(creditOffertemp.getPayments());
        return "redirect:/credits/offer";
    }

    @PostMapping("/credit/offer/add")
    public String saveCredit(CreditOfferShotDto creditOfferShotDto){
        this.creditOffertemp = creditOfferShotDto;
        return "redirect:/credit/offer/calculate";
    }
//
    @PostMapping("/credit/offer/delete/{id}")
    public String deleteCredit(@PathVariable Long id){
        creditOfferService.deleteById(id);
        return "redirect:/credits/offer";
    }

    @GetMapping("/credit/offer/delete/{id}")
    public String delete2Credit(@PathVariable Long id){
        creditOfferService.deleteById(id);
        return "redirect:/credits/offer";
    }
}
