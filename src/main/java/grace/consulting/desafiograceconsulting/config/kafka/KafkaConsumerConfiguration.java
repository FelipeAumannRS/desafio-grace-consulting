package grace.consulting.desafiograceconsulting.config.kafka;

import grace.consulting.desafiograceconsulting.module.credit_card.adapter.out.jpa.CreditCardRepository;
import grace.consulting.desafiograceconsulting.module.credit_card.domain.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {

    private final CreditCardRepository creditCardRepository;

    @KafkaListener(topics = "credit-card-notification", groupId = "grace-consulting")
    public void receiveNotification(Card incomingCard) {
        if (incomingCard == null || incomingCard.getId() == null) return;

        creditCardRepository.findById(incomingCard.getId())
                .map(existing -> merge(existing, incomingCard))
                .map(creditCardRepository::save)
                .ifPresentOrElse(
                        saved -> log.info("CreditCard {} updated successfully", saved.getId()),
                        () -> {
                            creditCardRepository.save(incomingCard);
                            log.info("CreditCard {} created successfully!", incomingCard.getId());
                        }
                );
    }

    private Card merge(Card existing, Card incoming) {
        existing.setExpirationDate(incoming.getExpirationDate());
        existing.setEncryptedFullName(incoming.getEncryptedFullName());
        existing.setEncryptedCvv(incoming.getEncryptedCvv());
        return existing;
    }
}