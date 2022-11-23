package api.reqres.models;

import lombok.Data;

@Data
public class RegisterBodyModel {
    private String email,
            password;
}
