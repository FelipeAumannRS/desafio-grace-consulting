package grace.consulting.desafiograceconsulting.module.credit_card.service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class CreditCardHashingService {

    private static final String HMAC_SHA_256 = "HmacSHA256";
    private final byte[] secret;

    public CreditCardHashingService(byte[] secret) {
        this.secret = secret.clone();
    }

    public String hashToHex(String normalizedDigits) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA_256);
            mac.init(new SecretKeySpec(secret, HMAC_SHA_256));
            byte[] digest = mac.doFinal(normalizedDigits.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(digest.length * 2);
            for (byte value : digest) hex.append(String.format("%02x", value));
            return hex.toString();
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to hash credit card number", exception);
        }
    }
}