package test.testHAULMONT.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "credit_offer")
@Data
public class CreditOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Column(name = "summa")
    private int summa;

    @OneToMany(mappedBy = "creditOffer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Payment> payments;
}
