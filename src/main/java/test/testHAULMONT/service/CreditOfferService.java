package test.testHAULMONT.service;


import org.springframework.stereotype.Service;
import test.testHAULMONT.dto.CreditOfferShotDto;
import test.testHAULMONT.model.CreditOffer;
import test.testHAULMONT.model.Payment;
import test.testHAULMONT.repository.ClientRepository;
import test.testHAULMONT.repository.CreditOfferRepository;
import test.testHAULMONT.repository.CreditRepository;
import test.testHAULMONT.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditOfferService {
    private final CreditOfferRepository creditOfferRepository;
    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final PaymentRepository paymentRepository;

    public CreditOfferService(CreditOfferRepository creditOfferRepository, ClientRepository clientRepository, CreditRepository creditRepository, PaymentRepository paymentRepository) {
        this.creditOfferRepository = creditOfferRepository;
        this.clientRepository = clientRepository;
        this.creditRepository = creditRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<CreditOffer> getAll(){

        return creditOfferRepository.findAll();
    }

    public void save(CreditOffer creditOffer){
        creditOfferRepository.save(creditOffer);
    }

    public CreditOffer findId(Long id){
        return creditOfferRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        creditOfferRepository.deleteById(id);
    }

    public CreditOffer creditOfferShotDtoToCreditOffer(CreditOfferShotDto creditOfferShotDto){
        if (creditOfferShotDto == null){
            return null;
        }
        CreditOffer creditOffer = new CreditOffer();
        creditOffer.setClient(clientRepository.findByFio(creditOfferShotDto.getClient()).orElse(null));
        creditOffer.setSumma(creditOfferShotDto.getSumma());
        creditOffer.setCredit(creditRepository.findByPercent(creditOfferShotDto.getPercent()).orElse(null));
        creditOffer.setPayments(creditOfferShotDto.getPayments());
        return creditOffer;
    }

    public List<Payment> calculate(int summa, int percent) {

        double mIns = (double) (percent) / 100 / 12; // Ежемесячная процентная ставка
        int months = 12;
        double remains = summa;
        double sum = 0; // Общая сумма погашения
        List<Payment> payments = new ArrayList<Payment>();
        for (int i = 0; i < months; i++)
        {
            Payment payment = new Payment();
            double temp[] = new double[3];
            temp[1] = remains * mIns;
            temp[1] = Math.floor(temp[1] * 100 + 0.5) / 100;
            temp[2] = summa / months;
            temp[2] = Math.floor(temp[2] * 100 + 0.5) / 100;
            temp[0] = temp[2] - temp[1];
            temp[0] = Math.floor(temp[0] * 100 + 0.5) / 100;
            remains -= temp[0];
            payment.setPayment_date(i+1);
            payment.setSumma((int) temp[2]);
            payment.setSumma_percent((int) temp[1]);
            payment.setSumma_credit((int) temp[0]);
            payments.add(payment);
        }
        // temp [] [0] - это ежемесячный основной платеж; temp [] [1] - это ежемесячный процент погашения; temp [] [2] - это общий ежемесячный платеж
        return payments;

    }
}
