package test.testHAULMONT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.testHAULMONT.model.CreditOffer;

@Repository
public interface CreditOfferRepository extends JpaRepository<CreditOffer, Long> {
}
