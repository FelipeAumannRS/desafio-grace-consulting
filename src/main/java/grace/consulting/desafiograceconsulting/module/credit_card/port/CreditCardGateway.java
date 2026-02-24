package grace.consulting.desafiograceconsulting.module.credit_card.port;

import grace.consulting.desafiograceconsulting.module.credit_card.domain.CreditCard;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardGateway {

    Optional<UUID> findIdByNumberHash(String numberHash);
    CreditCard save(CreditCard creditCard);
}