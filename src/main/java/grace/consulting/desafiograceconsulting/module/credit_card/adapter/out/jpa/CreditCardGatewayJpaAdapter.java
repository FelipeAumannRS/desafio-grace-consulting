package grace.consulting.desafiograceconsulting.module.credit_card.adapter.out.jpa;

import grace.consulting.desafiograceconsulting.module.credit_card.domain.Card;
import grace.consulting.desafiograceconsulting.module.credit_card.port.CardGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreditCardGatewayJpaAdapter implements CardGateway {

    private final CreditCardRepository repository;

    @Override
    public Optional<UUID> findIdByNumberHash(String numberHash) {
        return repository.findByNumberHash(numberHash).map(Card::getId);
    }

    @Override
    public Card save(Card card) {
        return repository.save(card);
    }
}