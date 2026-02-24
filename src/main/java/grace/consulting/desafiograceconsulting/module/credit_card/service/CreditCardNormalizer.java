package grace.consulting.desafiograceconsulting.module.credit_card.service;

import org.springframework.stereotype.Component;

@Component
public class CreditCardNormalizer {

    public String digitsOnly(String input) {
        return input == null ? "" : input.replaceAll("\\D", "");
    }

    public String last4(String digitsOnly) {
        return digitsOnly.length() < 4 ? digitsOnly : digitsOnly.substring(digitsOnly.length() - 4);
    }

    public String normalizeExpiry(String expiryMmYy) {
        return expiryMmYy == null ? "" : expiryMmYy.trim();
    }

    public boolean isValidExpiryMmYy(String expiryMmYy) {
        return expiryMmYy != null && expiryMmYy.matches("^(0[1-9]|1[0-2])/\\d{2}$");
    }

    public boolean isValidCvv(String cvv) {
        return cvv != null && cvv.matches("^\\d{3}$");
    }
}