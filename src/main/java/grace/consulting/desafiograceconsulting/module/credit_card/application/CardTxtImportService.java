package grace.consulting.desafiograceconsulting.module.credit_card.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import grace.consulting.desafiograceconsulting.module.credit_card.adapter.in.web.dto.RegisterCreditCardRequest;
import grace.consulting.desafiograceconsulting.module.credit_card.use_case.ImportCreditCardsUseCase;
import grace.consulting.desafiograceconsulting.module.credit_card.use_case.RegisterCreditCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CardTxtImportService implements ImportCreditCardsUseCase {

    private final ObjectMapper objectMapper;
    private final RegisterCreditCardUseCase registerUseCase;

    @Override
    public int importTxt(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            AtomicInteger processedCount = new AtomicInteger(0);

            reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isBlank())
                    .map(this::parseJsonLine)
                    .forEach(request -> {
                        registerUseCase.register(
                                request.getFullNumber(),
                                request.getFullName(),
                                request.getCvv(),
                                request.getCvv()
                        );
                        processedCount.incrementAndGet();
                    });

            return processedCount.get();
        }
    }

    private RegisterCreditCardRequest parseJsonLine(String line) {
        try {
            return objectMapper.readValue(line, RegisterCreditCardRequest.class);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid JSON line: " + line, exception);
        }
    }
}