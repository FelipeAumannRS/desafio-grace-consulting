package grace.consulting.desafiograceconsulting.config.encryption;

import grace.consulting.desafiograceconsulting.module.credit_card.service.AesGcmEncryptionService;
import grace.consulting.desafiograceconsulting.module.credit_card.service.CardHashingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardEncryptionConfig {

    @Bean
    public CardHashingService creditCardHashingService() {
        return new CardHashingService();
    }

    @Bean
    public AesGcmEncryptionService aesGcmEncryptionService() {
        return new AesGcmEncryptionService();
    }
}