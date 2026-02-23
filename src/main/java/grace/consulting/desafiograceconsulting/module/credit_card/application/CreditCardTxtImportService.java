package grace.consulting.desafiograceconsulting.module.credit_card.application;

import grace.consulting.desafiograceconsulting.module.credit_card.use_case.ImportCreditCardsUseCase;
import grace.consulting.desafiograceconsulting.module.credit_card.use_case.RegisterCreditCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class CreditCardTxtImportService implements ImportCreditCardsUseCase {

    private final RegisterCreditCardUseCase registerUseCase;

    @Override
    public int importTxt(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            return (int) reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isBlank())
                    .map(this::parseLine)
                    .peek(parsed -> registerUseCase.register(
                            parsed.fullNumber(),
                            parsed.fullName(),
                            parsed.cvv(),
                            parsed.expiryMmYy()
                    ))
                    .count();
        }
    }

    private ParsedLine parseLine(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length != 6) throw new IllegalArgumentException("Invalid line format: " + line);

        return new ParsedLine(
                parts[0].trim(),
                parts[3].trim(),
                parts[4].trim(),
                parts[5].trim()
        );
    }

    private record ParsedLine(String fullNumber, String fullName, String cvv, String expiryMmYy) {}
}