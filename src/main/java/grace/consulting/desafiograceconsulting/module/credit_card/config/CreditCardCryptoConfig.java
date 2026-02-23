package grace.consulting.desafiograceconsulting.module.credit_card.config;

import grace.consulting.desafiograceconsulting.module.credit_card.service.AesGcmEncryptionService;
import grace.consulting.desafiograceconsulting.module.credit_card.service.CreditCardHashingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class CreditCardCryptoConfig {

    @Bean
    public CreditCardHashingService creditCardHashingService(@Value("${credit-card.hmac-secret-base64}") String secretBase64) {
        return new CreditCardHashingService(Base64.getDecoder().decode(secretBase64));
    }

    @Bean
    public AesGcmEncryptionService aesGcmEncryptionService(@Value("${credit-card.aes-key-base64}") String keyBase64) {
        return new AesGcmEncryptionService(Base64.getDecoder().decode(keyBase64));
    }
}