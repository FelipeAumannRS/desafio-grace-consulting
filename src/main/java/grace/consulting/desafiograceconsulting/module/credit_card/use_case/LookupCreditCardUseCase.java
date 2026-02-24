package grace.consulting.desafiograceconsulting.module.credit_card.use_case;

import java.util.Optional;
import java.util.UUID;

public interface LookupCreditCardUseCase {

    Optional<UUID> findIdByFullNumber(String fullNumber);
}