package grace.consulting.desafiograceconsulting.module.credit_card.adapter.in.web.dto;

import lombok.Getter;

@Getter
public class RegisterCreditCardRequest {

    private String fullNumber;
    private String fullName;
    private String cvv;
    private String expirationDate;
}