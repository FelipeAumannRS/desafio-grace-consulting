package grace.consulting.desafiograceconsulting.module.credit_card.application;

import grace.consulting.desafiograceconsulting.module.credit_card.domain.Card;
import grace.consulting.desafiograceconsulting.module.credit_card.port.CardGateway;
import grace.consulting.desafiograceconsulting.module.credit_card.service.AesGcmEncryptionService;
import grace.consulting.desafiograceconsulting.module.credit_card.service.CardHashingService;
import grace.consulting.desafiograceconsulting.module.credit_card.use_case.RegisterCreditCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardRegistrationService implements RegisterCreditCardUseCase {

    private final CardGateway gateway;
    private final CardHashingService hashingService;
    private final AesGcmEncryptionService encryptionService;

    @Override
    public UUID register(String fullNumber, String fullName, String cvv, String expirationDate) {
        String numberHash = hashingService.hash(fullNumber);

        return gateway
                .findIdByNumberHash(numberHash)
                .orElseGet(() -> gateway.save(new Card(
                        numberHash,
                        expirationDate,
                        encryptionService.encrypt(fullName),
                        encryptionService.encrypt(cvv)
                )).getId());
    }
}