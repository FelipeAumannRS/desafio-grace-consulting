package grace.consulting.desafiograceconsulting.module.authentication.adapter.web.dto;

import lombok.Getter;

@Getter
public class LoginRequestDTO {

    private String username;
    private String password;
}