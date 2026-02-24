package grace.consulting.desafiograceconsulting.module.credit_card.adapter.out.jpa;

import grace.consulting.desafiograceconsulting.module.credit_card.domain.CreditCard;
import grace.consulting.desafiograceconsulting.module.credit_card.port.CreditCardGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreditCardGatewayJpaAdapter implements CreditCardGateway {

    private final CreditCardRepository repository;

    @Override
    public Optional<UUID> findIdByNumberHash(String numberHash) {
        return repository.findByNumberHash(numberHash).map(CreditCard::getId);
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return repository.save(creditCard);
    }
}