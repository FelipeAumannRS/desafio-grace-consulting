package grace.consulting.desafiograceconsulting.module.credit_card.port;

import grace.consulting.desafiograceconsulting.module.credit_card.domain.Card;

import java.util.Optional;
import java.util.UUID;

public interface CardGateway {

    Optional<UUID> findIdByNumberHash(String numberHash);
    Card save(Card card);
}