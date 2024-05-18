package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleJUnitTest {
    @BeforeAll
    static void config() {
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
    }

    @Test
    void formSubmissionWithAllFieldsFilledTest() {
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
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $("select.react-datepicker__month-select").selectOption(monthOfBirth);
        $("select.react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--011").click();
        $("input#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFile(picture);
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#state").$(byText(state)).click();
        $("#city").click();
        $("#city").$(byText(city)).click();
        $("#submit").submit();

        // Assert
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(email));
        $(".table-responsive").shouldHave(text(gender));
        $(".table-responsive").shouldHave(text(phoneNumber));
        $(".table-responsive").shouldHave(
                text(formatDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)));
        $(".table-responsive").shouldHave(text(subject));
        $(".table-responsive").shouldHave(text(hobby));
        $(".table-responsive").shouldHave(text(picture.getName()));
        $(".table-responsive").shouldHave(text(address));
        $(".table-responsive").shouldHave(text(state + " " + city));
    }

    private String formatDateOfBirth(String day, String month, String year) {
        return day + " " + month + "," + year;
    }
}
