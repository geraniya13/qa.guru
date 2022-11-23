package api.reqres;

import api.reqres.models.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static api.reqres.specs.RegisterSpecs.*;
import static api.reqres.specs.UserSpecs.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class ReqresTests {

    @Test
    public void singleUserFoundTest() {
        UserSearchResponseModel response = given()
                .spec(userCheckRequestSpec)
                .when()
                .get()
                .then()
                .spec(userFoundResponseSpec)
                .extract()
                .as(UserSearchResponseModel.class);

        assertThat(response.getData().getId()).isEqualTo(2);
    }

    @Test
    public void singleUserNotFoundTest() {
        given()
                .spec(userFailCheckRequestSpec)
                .when()
                .get()
                .then()
                .spec(userNotFoundResponseSpec);
    }

    @Test
    public void registerSuccessfulTest() {
        RegisterBodyModel body = new RegisterBodyModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");
        RegisterResponseModel response = given()
                .spec(registerRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(registerSuccessResponseSpec)
                .extract()
                .as(RegisterResponseModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    public void registerUnsuccessfulTest() {
        RegisterBodyModel body = new RegisterBodyModel();
        body.setEmail("sydney@fife");
        given()
                .spec(registerRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(registerUnsuccessResponseSpec);
    }

    @Test
    public void updateUserTest() {
        UserBodyModel body = new UserBodyModel();
        body.setName("morpheus");
        body.setJob("zion resident");
        UserUpdateResponseModel response = given()
                .spec(userUpdateRequestSpec)
                .body(body)
                .when()
                .put()
                .then()
                .spec(userUpdateResponseSpec)
                .extract()
                .as(UserUpdateResponseModel.class);

        assertThat(response.getUpdatedAt()).contains(LocalDate.now() + "");
    }
}
