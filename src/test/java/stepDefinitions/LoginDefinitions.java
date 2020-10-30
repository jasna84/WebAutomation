package test.java.stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.config.Driver;
import org.testng.Assert;
import main.java.pageObject.LoginPage;
import test.java.BaseTest;

public class LoginDefinitions extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @Given("^there is open \"([^\"]*)\"$")
    public void thereIsOpen(String browser) throws Throwable {
        Driver.getInstance().setDriver(browser);

    }

    @When("^the user is on the login page$")
    public void theUserIsOnTheLoginPage() {
        goToLoginPage();
    }

    @And("^when valid \"([^\"]*)\" is entered to the email field$")
    public void whenValidIsEnteredToTheEmailField(String email) throws Throwable {
        loginPage.enterEmail(email);
    }

    @And("^when valid \"([^\"]*)\" is entered to the password field$")
    public void whenValidIsEnteredToThePasswordField(String password) throws Throwable {
        loginPage.enterPassword(password);
    }

    @And("^when login button is clicked$")
    public void whenLoginButtonIsClicked() {
        loginPage.clickOnSubmitButton();
    }

    @Then("^user will see their home page$")
    public void userWillSeeTheirHomePage() {
        boolean homePageIsVisible = loginPage.homePageIsVisible();
        Assert.assertTrue(homePageIsVisible);
    }

    @And("^when invalid \"([^\"]*)\" is entered to the email field$")
    public void whenInvalidIsEnteredToTheEmailField(String email) throws Throwable {
        loginPage.enterEmail(email);
    }

    @And("^when invalid \"([^\"]*)\" is entered to the password field$")
    public void whenInvalidIsEnteredToThePasswordField(String password) throws Throwable {
        loginPage.enterPassword(password);
    }

    @Then("^user will see the invalid account error message and will not be logged in$")
    public void userWillSeeTheInvalidAccountErrorMessageAndWillNotBeLoggedIn() {
        boolean errorMessageIsValid = loginPage.invalidAccountMessageIsValid();
        Assert.assertTrue(errorMessageIsValid);
    }

    @Then("^user will see the invalid password error message and will not be logged in$")
    public void userWillSeeTheInvalidPasswordErrorMessageAndWillNotBeLoggedIn() {
        boolean errorMessageIsValid = loginPage.invalidPasswordMessageIsValid();
        Assert.assertTrue(errorMessageIsValid);
    }

    @And("^login button is clicked without entering credentials$")
    public void loginButtonIsClickedWithoutEnteringCredentials() {
        loginPage.clickOnSubmitButton();
    }

}