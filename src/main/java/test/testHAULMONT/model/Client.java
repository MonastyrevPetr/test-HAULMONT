package test.testHAULMONT.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "passport")
    private String passport;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<CreditOffer> creditOffers;


}
