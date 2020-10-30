package main.java.pageObject;

import main.java.config.BasePage;
import main.java.config.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class.getSimpleName());

    public String URL = "https://www.facebook.com/";

    public void navigateToLoginPage() {
        Driver.getInstance().getDriver().navigate().to(URL);
    }

    By emailField = By.id("email");
    By passwordField = By.id("pass");
    By submitButton = By.cssSelector("button[name = 'login']");
    By homePageElement = By.id("u_0_c");
    By errorMessage = By.className("_9ay7");

    public void enterEmail(String email) {
        sendText(emailField, email);
    }

    public void enterPassword (String password) {
        sendText(passwordField, password);
    }

    public void clickOnSubmitButton () {
        click(submitButton);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    public boolean invalidAccountMessageIsValid() {
        return getErrorMessage().contains("The email or phone number you’ve entered doesn’t match any account.");
    }

    public boolean invalidPasswordMessageIsValid() {
        return getErrorMessage().contains("The password you’ve entered is incorrect. ");
    }

    public boolean errorMessageIsVisible () {
        return waitForElement(errorMessage, 3);
    }

    public boolean homePageIsVisible () {
        return waitForElement(homePageElement, 3);
    }

}

