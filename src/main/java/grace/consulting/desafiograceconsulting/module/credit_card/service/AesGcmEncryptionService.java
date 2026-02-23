package grace.consulting.desafiograceconsulting.module.credit_card.service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AesGcmEncryptionService {

    private static final String AES = "AES";
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int GCM_TAG_BITS = 128;
    private static final int IV_BYTES = 12;

    private final SecretKeySpec secretKeySpec;
    private final SecureRandom secureRandom = new SecureRandom();

    public AesGcmEncryptionService(byte[] keyBytes32) {
        this.secretKeySpec = new SecretKeySpec(keyBytes32, AES);
    }

    public String encryptToBase64(String plaintext) {
        try {
            byte[] iv = new byte[IV_BYTES];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new GCMParameterSpec(GCM_TAG_BITS, iv));

            byte[] ciphertext = cipher.doFinal((plaintext == null ? "" : plaintext).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            byte[] packed = new byte[iv.length + ciphertext.length];

            System.arraycopy(iv, 0, packed, 0, iv.length);
            System.arraycopy(ciphertext, 0, packed, iv.length, ciphertext.length);

            return Base64.getEncoder().encodeToString(packed);
        } catch (Exception exception) {
            throw new IllegalStateException("Encryption failed", exception);
        }
    }

    public String decryptFromBase64(String base64) {
        try {
            byte[] packed = Base64.getDecoder().decode(base64);
            byte[] iv = new byte[IV_BYTES];
            byte[] ciphertext = new byte[packed.length - IV_BYTES];

            System.arraycopy(packed, 0, iv, 0, IV_BYTES);
            System.arraycopy(packed, IV_BYTES, ciphertext, 0, ciphertext.length);

            Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new GCMParameterSpec(GCM_TAG_BITS, iv));

            byte[] plaintext = cipher.doFinal(ciphertext);
            return new String(plaintext, java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception exception) {
            throw new IllegalStateException("Decryption failed", exception);
        }
    }
}