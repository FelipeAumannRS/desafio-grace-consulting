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
public class CreditCard {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 64)
    private String numberHash;

    @Column(nullable = false, length = 4)
    private String last4;

    @Column(nullable = false, length = 5)
    private String expiryMmYy;

    @Lob
    @Column(nullable = false)
    private String encryptedFullName;

    @Lob
    @Column(nullable = false)
    private String encryptedCvv;

    @Column(nullable = false)
    private Instant createdAt;

    public CreditCard(String numberHash,
                      String last4,
                      String expiryMmYy,
                      String encryptedFullName,
                      String encryptedCvv,
                      Instant createdAt) {
        this.numberHash = numberHash;
        this.last4 = last4;
        this.expiryMmYy = expiryMmYy;
        this.encryptedFullName = encryptedFullName;
        this.encryptedCvv = encryptedCvv;
        this.createdAt = createdAt;
    }
}