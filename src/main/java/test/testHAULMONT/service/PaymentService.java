package test.testHAULMONT.service;


import org.springframework.stereotype.Service;
import test.testHAULMONT.model.Payment;
import test.testHAULMONT.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

    public void save(Payment payment){
        paymentRepository.save(payment);
    }

    public void saveAll(List<Payment> payments){
        paymentRepository.saveAll(payments);
    }

    public Payment findId(Long id){
        return paymentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        paymentRepository.deleteById(id);
    }

}
