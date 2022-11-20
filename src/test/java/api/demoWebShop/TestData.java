package api.demoWebShop;

import org.apache.commons.lang3.StringUtils;

public class TestData {
    private String nopCustomerValueCookie,
            requestVerificationToken,
            requestVerificationTokenToRegister,
            nopCommerceCookie,
            gender = "M",
            name,
            lastName,
            email,
            password,
            registerButton = "Register";

    public String getNopCustomerValueCookie() {
        return nopCustomerValueCookie;
    }

    public void setNopCustomerValueCookie(String nopCustomerValueCookie) {
        this.nopCustomerValueCookie = nopCustomerValueCookie;
    }

    public String getRequestVerificationToken() {
        return requestVerificationToken;
    }

    public void setRequestVerificationToken(String requestVerificationToken) {
        this.requestVerificationToken = requestVerificationToken;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(String registerButton) {
        this.registerButton = registerButton;
    }

    public String getRequestVerificationTokenToRegister() {
        return requestVerificationTokenToRegister;
    }

    public void setRequestVerificationTokenToRegister(String requestVerificationTokenToRegister) {
        this.requestVerificationTokenToRegister = requestVerificationTokenToRegister;
    }

    public String getNopCommerceCookie() {
        return nopCommerceCookie;
    }

    public void setNopCommerceCookie(String nopCommerceCookie) {
        this.nopCommerceCookie = nopCommerceCookie;
    }

    public String buildRegisterBody() {
        return "__RequestVerificationToken=" + getRequestVerificationTokenToRegister() + "&Gender=" + getGender() + "&FirstName=" + getName() + "&LastName=" + getLastName() + "&Email=" + getEmail() + "&Password=" + getPassword() + "&ConfirmPassword=" + getPassword() + "&register-button=" + getRegisterButton();
    }

    public String buildChangeDataBody(String newName) {
        return "__RequestVerificationToken=" + getRequestVerificationTokenToRegister() + "&Gender=" + getGender() + "&FirstName=" + newName + "&LastName=" + getLastName() + "&Email=" + getEmail() + "&save-info-button=Save";
    }

    public String getVerificationTokenFromBody(String body) {
        String token = "";
        String[] contents = body.split("\n");
        for (String line : contents) {
            if (line.contains("name=\"__RequestVerificationToken\"")) {
                token = StringUtils.substringBetween(line, "value=\"", "\"/>");
            }
        }
        return token;
    }
}
