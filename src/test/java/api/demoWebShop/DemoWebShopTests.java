package api.demoWebShop;


import api.demoWebShop.helpers.AllureRestAssuredFilter;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class DemoWebShopTests extends TestBase {

    @Test
    @Tag("DemoWebShop")
    @DisplayName("DemoWebShop registration test")
    void registerAndChangeInfoTest() {
        step("Register by API and get cookies", () -> {
            step("Open registration page and save cookies", () -> {
                Response response = given().filter(AllureRestAssuredFilter.withCustomTemplates()).get("/register");
                Cookies allDetailedCookies = response.getDetailedCookies();
                testData.setRequestVerificationTokenToRegister(testData.getVerificationTokenFromBody(response.getBody().asPrettyString()));
                testData.setNopCustomerValueCookie(allDetailedCookies.getValue("Nop.customer"));
                testData.setRequestVerificationToken(allDetailedCookies.getValue("__RequestVerificationToken"));
            });
            step("Register with cookies", () -> {
                testData.setNopCommerceCookie(given().config(RestAssured.config().redirect(redirectConfig().followRedirects(false)))
                        .filter(AllureRestAssuredFilter.withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .contentType("application/x-www-form-urlencoded")
                        .cookie("Nop.customer", testData.getNopCustomerValueCookie())
                        .cookie("__RequestVerificationToken", testData.getRequestVerificationToken())
                        .body(testData.buildRegisterBody())
                        .when()
                        .post("/register")
                        .then()
                        .log().status()
                        .log().headers()
                        .statusCode(302)
                        .extract()
                        .cookie("NOPCOMMERCE.AUTH"));
            });
        });
        step("Verify successful registration and authorisation at UI and set cookie to browser", () -> {
            step("Open browser with lite contents", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));

            step("Set cookies to browser", () -> {
                getWebDriver().manage().addCookie(
                        new Cookie("Nop.customer", testData.getNopCustomerValueCookie()));
                getWebDriver().manage().addCookie(
                        new Cookie("NOPCOMMERCE.AUTH", testData.getNopCommerceCookie()));
            });

            step("Registration and authorisation verification", () -> {
                open("/");
                $("a.account").shouldHave(text(testData.getEmail()));
            });

        });
        step("Edit user info by API", () -> {
            step("Open account info page", () -> {
                Response response = given().filter(AllureRestAssuredFilter.withCustomTemplates())
                        .cookie("Nop.customer", testData.getNopCustomerValueCookie())
                        .cookie("NOPCOMMERCE.AUTH", testData.getNopCommerceCookie())
                        .when()
                        .get("/customer/info");
                testData.setRequestVerificationTokenToRegister(testData.getVerificationTokenFromBody(response.getBody().asPrettyString()));
                testData.setRequestVerificationToken(response.getDetailedCookies().getValue("__RequestVerificationToken"));
            });
            step("Change account info", () -> {
                given().config(RestAssured.config().redirect(redirectConfig().followRedirects(false)))
                        .filter(AllureRestAssuredFilter.withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .log().headers()
                        .contentType("application/x-www-form-urlencoded")
                        .cookie("Nop.customer", testData.getNopCustomerValueCookie())
                        .cookie("NOPCOMMERCE.AUTH", testData.getNopCommerceCookie())
                        .cookie("__RequestVerificationToken", testData.getRequestVerificationToken())
                        .body(testData.buildChangeDataBody(faker.name().firstName()))
                        .when()
                        .post("/customer/info")
                        .then()
                        .log().status()
                        .log().headers()
                        .statusCode(302);
            });
        });
        step("Check if info is changed in UI", () -> {
            open("/customer/info");
            $("#FirstName").shouldNotHave(value(testData.getName()));
        });

    }
}
