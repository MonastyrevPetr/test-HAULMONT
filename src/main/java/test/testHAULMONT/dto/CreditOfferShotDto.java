package test.testHAULMONT.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import test.testHAULMONT.model.Payment;

import java.util.List;


@Data
@NoArgsConstructor
public class CreditOfferShotDto {

    private int summa;

    private String client;

    private int percent;

    List<Payment> payments;

}
