package demoqa.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import demoqa.page.components.CalendarComponent;
import demoqa.page.components.Countries;
import demoqa.page.components.ResultsModal;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    private final static String TITLE_TEXT = "Student Registration Form";
    private CalendarComponent calendarComponent = new CalendarComponent();
    private ResultsModal resultsModal = new ResultsModal();
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            countryCheck = $("#state"),
            cityCheck = $("#city"),
            countryCityInput = $("#stateCity-wrapper"),
            submitButton = $("#submit");

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage clearFirstName() {
        firstNameInput.clear();
        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }


    public RegistrationFormPage setSubjects(String[] subjects) {
        for (int i = 0; i < subjects.length; i++) {
            subjectsInput.setValue(subjects[i]).pressEnter();
        }
        return this;
    }

    public RegistrationFormPage setHobbies(String[] hobbies) {
        for (int i = 0; i < hobbies.length; i++) {
            hobbiesInput.find(new ByText(hobbies[i])).click();
        }
        return this;
    }

    public RegistrationFormPage setPicture(String path, String value) {
        pictureInput.uploadFile(new File(path + value));
        return this;
    }

    public RegistrationFormPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setCountry(Countries country) {
        countryCheck.click();
        countryCityInput.$(byText(country.getTitle())).click();
        return this;
    }

    public RegistrationFormPage setCity(String value) {
        cityCheck.click();
        countryCityInput.$(byText(value)).click();
        return this;
    }

    public void submitButtonClick() {
        submitButton.click();
    }

    public RegistrationFormPage checkResultsTableVisible() {
        resultsModal.checkVisible();

        return this;
    }

    public RegistrationFormPage checkResult(String key, String value) {
        resultsModal.checkResult(key, value);

        return this;
    }
}
