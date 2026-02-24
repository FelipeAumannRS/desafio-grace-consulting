package grace.consulting.desafiograceconsulting.module.credit_card.use_case;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImportCreditCardsUseCase {

    int importTxt(MultipartFile file) throws IOException;
}