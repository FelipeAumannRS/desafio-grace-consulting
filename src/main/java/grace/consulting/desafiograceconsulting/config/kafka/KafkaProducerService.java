package grace.consulting.desafiograceconsulting.config.kafka;

import grace.consulting.desafiograceconsulting.module.credit_card.domain.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Card> kafkaTemplate;

    public void sendNotification(Card conta) {
        kafkaTemplate.send("conta-notification", conta);
    }
}