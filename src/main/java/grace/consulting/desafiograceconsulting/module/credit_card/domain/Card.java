package grace.consulting.desafiograceconsulting.module.credit_card.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "credit_card", indexes = {
        @Index(name = "idx_credit_card_number_hash", columnList = "numberHash", unique = true)
})
public class Card {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 64)
    private String numberHash;

    @Column(nullable = false, length = 5)
    private String expirationDate;

    @Lob
    @Column(nullable = false)
    private String encryptedFullName;

    @Lob
    @Column(nullable = false)
    private String encryptedCvv;

    public Card(String numberHash,
                String expirationDate,
                String encryptedFullName,
                String encryptedCvv) {
        this.numberHash = numberHash;
        this.expirationDate = expirationDate;
        this.encryptedFullName = encryptedFullName;
        this.encryptedCvv = encryptedCvv;
    }
}