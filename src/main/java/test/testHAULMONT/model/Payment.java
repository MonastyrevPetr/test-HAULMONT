package test.testHAULMONT.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "payment_date")
    private int payment_date;

    @Column(name = "summa")
    private int summa;

    @Column(name = "summa_credit")
    private int summa_credit;

    @Column(name = "summa_percent")
    private int summa_percent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_offer_id")
    private CreditOffer creditOffer;
}
