package grace.consulting.desafiograceconsulting.config.kafka;

import grace.consulting.desafiograceconsulting.module.credit_card.domain.CreditCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, CreditCard> kafkaTemplate;

    public void sendNotification(CreditCard conta) {
        kafkaTemplate.send("conta-notification", conta);
    }
}