package grace.consulting.desafiograceconsulting.module.credit_card.adapter.out.jpa;

import grace.consulting.desafiograceconsulting.module.credit_card.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    Optional<CreditCard> findByNumberHash(String numberHash);
}