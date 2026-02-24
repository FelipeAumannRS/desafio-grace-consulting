package grace.consulting.desafiograceconsulting.module.credit_card.application;

import grace.consulting.desafiograceconsulting.module.credit_card.port.CardGateway;
import grace.consulting.desafiograceconsulting.module.credit_card.service.CardHashingService;
import grace.consulting.desafiograceconsulting.module.credit_card.service.CreditCardNormalizer;
import grace.consulting.desafiograceconsulting.module.credit_card.use_case.LookupCreditCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardLookupService implements LookupCreditCardUseCase {

    private final CardGateway gateway;
    private final CardHashingService hashingService;
    private final CreditCardNormalizer normalizer;

    @Override
    public Optional<UUID> findIdByFullNumber(String fullNumber) {
        String digits = normalizer.digitsOnly(fullNumber);
        return digits.isBlank() ? Optional.empty() : gateway.findIdByNumberHash(hashingService.hash(digits));
    }
}