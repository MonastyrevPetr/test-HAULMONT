package test.testHAULMONT.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "credit")
@Data
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "limit_credit")
    private int limit;

    @Column(name = "percent")
    private int percent;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<CreditOffer> creditOffers;
}
