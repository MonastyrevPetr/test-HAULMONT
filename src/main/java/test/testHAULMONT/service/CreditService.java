package test.testHAULMONT.service;


import org.springframework.stereotype.Service;
import test.testHAULMONT.model.Credit;
import test.testHAULMONT.repository.CreditRepository;

import java.util.List;

@Service
public class CreditService {
    private final CreditRepository creditRepository;

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public List<Credit> getAll(){
        return creditRepository.findAll();
    }

    public void save(Credit credit){
        creditRepository.save(credit);
    }

    public Credit findId(Long id){
        return creditRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        creditRepository.deleteById(id);
    }


}
