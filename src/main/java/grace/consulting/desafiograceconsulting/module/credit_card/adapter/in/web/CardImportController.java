package grace.consulting.desafiograceconsulting.module.credit_card.adapter.in.web;

import grace.consulting.desafiograceconsulting.module.credit_card.use_case.ImportCreditCardsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/card/import")
@RequiredArgsConstructor
public class CardImportController {

    private final ImportCreditCardsUseCase importUseCase;

    @PostMapping(value = "/txt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadTxt(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(Map.of("processed", importUseCase.importTxt(file)));
    }
}