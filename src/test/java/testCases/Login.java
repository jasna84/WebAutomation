package test.java.testCases;

import main.java.config.Driver;
import main.java.pageObject.LoginPage;
import org.testng.annotations.*;
import org.testng.Assert;
import test.java.BaseTest;

public class Login extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @Parameters("browser")
    @BeforeTest
    public void setUp(String browser) throws Exception  {
        Driver.getInstance().setDriver(browser);
        goToLoginPage();
    }

    @Test(priority = 4, dataProvider = "ValidLoginProvider")
    public void isLoggedInWithValidCredentials(String email, String password) {

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickOnSubmitButton();

        boolean homePageIsVisible = loginPage.homePageIsVisible();
        Assert.assertTrue(homePageIsVisible);

    }

    @Test(priority = 3, dataProvider="InvalidAccountProvider")
    public void isNotLoggedInWithInvalidAccount(String email, String password)  {

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickOnSubmitButton();

        boolean errorMessageDisplayed = loginPage.errorMessageIsVisible();
        Assert.assertTrue(errorMessageDisplayed);

        boolean errorMessageIsValid = loginPage.invalidAccountMessageIsValid();
        Assert.assertTrue(errorMessageIsValid);

    }

    @Test(priority = 2, dataProvider = "InvalidPasswordProvider")
    public void isNotLoggedInWithInvalidPassword(String email, String password) {

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickOnSubmitButton();

        boolean errorMessageDisplayed = loginPage.errorMessageIsVisible();
        Assert.assertTrue(errorMessageDisplayed);

        boolean errorMessageIsValid = loginPage.invalidPasswordMessageIsValid();
        Assert.assertTrue(errorMessageIsValid);

    }

    @Test(priority = 1)
    public void isNotLoggedInWithoutCredentials() {

        loginPage.clickOnSubmitButton();

        boolean errorMessageDisplayed = loginPage.errorMessageIsVisible();
        Assert.assertTrue(errorMessageDisplayed);

        boolean errorMessageIsValid = loginPage.invalidAccountMessageIsValid();
        Assert.assertTrue(errorMessageIsValid);

    }

    @DataProvider(name="ValidLoginProvider")
    public Object[] getValidCredentials() {

        Object[][] data = new Object[1][2];

        data[0][0] = "blaaaa@gmail.com";
        data[0][1] = "SeleniumTest123";

        return data;
    }

    @DataProvider(name="InvalidPasswordProvider")
    public Object[] getInvalidPassword() {

        Object[][] data = new Object[1][2];

        data[0][0] = "blaaaa@gmail.com";
        data[0][1] = "fgfdgdgdfgdgdf";

        return data;
    }

    @DataProvider(name="InvalidAccountProvider")
    public Object[] getInvalidData() {

        Object[][] data = new Object[1][2];

        data[0][0] = "fgsfgsgbaaa@gmail.com";
        data[0][1] = "aaaaaaaaaaaaaaaa";

        return data;
    }

    @AfterTest
    public void tearDown() {
        teardown();
    }

}

