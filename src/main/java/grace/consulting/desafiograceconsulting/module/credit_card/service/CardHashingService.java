package grace.consulting.desafiograceconsulting.module.credit_card.service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HexFormat;

public class CardHashingService {

    private static final String HMAC_SHA_256 = "HmacSHA256";
    private final byte[] secret;

    public CardHashingService() {
        byte[] bytes = new byte[32];
        new SecureRandom().nextBytes(bytes);
        this.secret = bytes;
    }

    public String hash(String normalizedCardNumber) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA_256);
            mac.init(new SecretKeySpec(secret, HMAC_SHA_256));
            byte[] digest = mac.doFinal(normalizedCardNumber.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to hash card number", exception);
        }
    }
}