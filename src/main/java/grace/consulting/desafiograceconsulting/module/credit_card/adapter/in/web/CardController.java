package grace.consulting.desafiograceconsulting.module.credit_card.adapter.in.web;

import grace.consulting.desafiograceconsulting.module.credit_card.adapter.in.web.dto.RegisterCreditCardRequest;
import grace.consulting.desafiograceconsulting.module.credit_card.use_case.LookupCreditCardUseCase;
import grace.consulting.desafiograceconsulting.module.credit_card.use_case.RegisterCreditCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {

    private final RegisterCreditCardUseCase registerUseCase;
    private final LookupCreditCardUseCase lookupUseCase;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterCreditCardRequest request) {
        return ResponseEntity.ok(Map.of("id", registerUseCase.register(
                request.getFullNumber(),
                request.getFullName(),
                request.getCvv(),
                request.getExpirationDate()
        )));
    }

    @GetMapping("/lookup")
    public ResponseEntity<?> lookup(@RequestParam String fullNumber) {
        return lookupUseCase.findIdByFullNumber(fullNumber)
                .<ResponseEntity<?>>map(id -> ResponseEntity.ok(Map.of("id", id)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}