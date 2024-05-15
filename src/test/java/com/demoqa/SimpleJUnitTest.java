package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class SimpleJUnitTest {
    @BeforeAll
    static void config() {
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.headless = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void formSubmissionWithAllFieldsFilled() {
        // Arrange
        String firstName = "FirstName";
        String lastName = "LastName";
        String email = "name@example.com";
        String gender = "Male";
        String phoneNumber = "1234567890";
        String dayOfBirth = "11";
        String monthOfBirth = "January";
        String yearOfBirth = "2000";
        String subject = "Maths";
        String hobby = "Sports";
        File picture = new File("src/test/resources/qa_testing1ru.jpg");
        String address = "SomeTextSomeText";
        String state = "NCR";
        String city = "Delhi";

        // Act
        open("automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $x(String.format("//label[contains(text(), '%s')]", gender)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $("select.react-datepicker__month-select").selectOption(monthOfBirth);
        $("select.react-datepicker__year-select").selectOption(yearOfBirth);
        $x(String.format("//div[@class='react-datepicker__month-container']//div[contains(text(), '%s')]", dayOfBirth)).click();
        $("input#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byXpath(String.format(".//*[text()='%s']", hobby))).click();
        $("#uploadPicture").uploadFile(picture);
        $("#currentAddress").setValue(address);
        $("#state").click();
        $x(String.format("//*[@id='state']//*[text()='%s']", state)).click();
        $("#city").click();
        $x(String.format("//*[@id='city']//*[text()='%s']", city)).click();
        $("#submit").submit();

        // Assert
        $(".table-responsive").shouldHave(Condition.text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(Condition.text(email));
        $(".table-responsive").shouldHave(Condition.text(gender));
        $(".table-responsive").shouldHave(Condition.text(phoneNumber));
        $(".table-responsive").shouldHave(
                Condition.text(formatDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)));
        $(".table-responsive").shouldHave(Condition.text(subject));
        $(".table-responsive").shouldHave(Condition.text(hobby));
        $(".table-responsive").shouldHave(Condition.text(picture.getName()));
        $(".table-responsive").shouldHave(Condition.text(address));
        $(".table-responsive").shouldHave(Condition.text(state + " " + city));
    }

    private String formatDateOfBirth(String day, String month, String year) {
        return day + " " + month + "," + year;
    }
}
