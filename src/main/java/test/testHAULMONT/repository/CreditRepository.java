package test.testHAULMONT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.testHAULMONT.model.Client;
import test.testHAULMONT.model.Credit;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    Optional<Credit> findByPercent(int percent);
}
