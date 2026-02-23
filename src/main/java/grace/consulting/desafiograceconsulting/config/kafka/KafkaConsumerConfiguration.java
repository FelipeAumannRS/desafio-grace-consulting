package grace.consulting.desafiograceconsulting.config.kafka;

import grace.consulting.desafiograceconsulting.module.credit_card.adapter.out.jpa.CreditCardRepository;
import grace.consulting.desafiograceconsulting.module.credit_card.domain.CreditCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {

    private final CreditCardRepository creditCardRepository;

    @KafkaListener(topics = "credit-card-notification", groupId = "totvs")
    public void receiveNotification(CreditCard incomingCreditCard) {
        if (incomingCreditCard == null || incomingCreditCard.getId() == null) return;

        creditCardRepository.findById(incomingCreditCard.getId())
                .map(existing -> merge(existing, incomingCreditCard))
                .map(creditCardRepository::save)
                .ifPresentOrElse(
                        saved -> log.info("CreditCard {} updated successfully", saved.getId()),
                        () -> {
                            creditCardRepository.save(incomingCreditCard);
                            log.info("CreditCard {} created successfully!", incomingCreditCard.getId());
                        }
                );
    }

    private CreditCard merge(CreditCard existing, CreditCard incoming) {
        existing.setExpiryMmYy(incoming.getExpiryMmYy());
        existing.setEncryptedFullName(incoming.getEncryptedFullName());
        existing.setEncryptedCvv(incoming.getEncryptedCvv());
        return existing;
    }
}