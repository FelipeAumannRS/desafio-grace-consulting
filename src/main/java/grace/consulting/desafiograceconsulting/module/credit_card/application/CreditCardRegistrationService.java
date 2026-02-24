package grace.consulting.desafiograceconsulting.module.credit_card.application;

import grace.consulting.desafiograceconsulting.module.credit_card.domain.CreditCard;
import grace.consulting.desafiograceconsulting.module.credit_card.port.CreditCardGateway;
import grace.consulting.desafiograceconsulting.module.credit_card.service.AesGcmEncryptionService;
import grace.consulting.desafiograceconsulting.module.credit_card.service.CreditCardHashingService;
import grace.consulting.desafiograceconsulting.module.credit_card.service.CreditCardNormalizer;
import grace.consulting.desafiograceconsulting.module.credit_card.use_case.RegisterCreditCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditCardRegistrationService implements RegisterCreditCardUseCase {

    private final CreditCardGateway gateway;
    private final CreditCardHashingService hashingService;
    private final AesGcmEncryptionService encryptionService;
    private final CreditCardNormalizer normalizer;

    @Override
    public UUID register(String fullNumber, String fullName, String cvv, String expiryMmYy) {
        String digits = normalizer.digitsOnly(fullNumber);
        String expiry = normalizer.normalizeExpiry(expiryMmYy);

        if (digits.isBlank()) throw new IllegalArgumentException("Invalid credit card number");
        if (!normalizer.isValidCvv(cvv)) throw new IllegalArgumentException("Invalid CVV");
        if (!normalizer.isValidExpiryMmYy(expiry)) throw new IllegalArgumentException("Invalid expiry format (MM/YY)");

        String numberHash = hashingService.hashToHex(digits);

        return gateway.findIdByNumberHash(numberHash)
                .orElseGet(() -> gateway.save(new CreditCard(
                        numberHash,
                        normalizer.last4(digits),
                        expiry,
                        encryptionService.encryptToBase64(fullName),
                        encryptionService.encryptToBase64(cvv),
                        Instant.now()
                )).getId());
    }
}