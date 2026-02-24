package grace.consulting.desafiograceconsulting.module.credit_card.application;

import grace.consulting.desafiograceconsulting.module.credit_card.port.CreditCardGateway;
import grace.consulting.desafiograceconsulting.module.credit_card.service.CreditCardHashingService;
import grace.consulting.desafiograceconsulting.module.credit_card.service.CreditCardNormalizer;
import grace.consulting.desafiograceconsulting.module.credit_card.use_case.LookupCreditCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditCardLookupService implements LookupCreditCardUseCase {

    private final CreditCardGateway gateway;
    private final CreditCardHashingService hashingService;
    private final CreditCardNormalizer normalizer;

    @Override
    public Optional<UUID> findIdByFullNumber(String fullNumber) {
        String digits = normalizer.digitsOnly(fullNumber);
        return digits.isBlank() ? Optional.empty() : gateway.findIdByNumberHash(hashingService.hashToHex(digits));
    }
}