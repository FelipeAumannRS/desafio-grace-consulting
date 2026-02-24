package grace.consulting.desafiograceconsulting.module.credit_card.use_case;

import java.util.UUID;

public interface RegisterCreditCardUseCase {

    UUID register(String fullNumber, String fullName, String cvv, String expiryMmYy);
}