package api.reqres.models;

import lombok.Data;

@Data
public class UserUpdateResponseModel {
    String name,
            job,
            updatedAt;
}
