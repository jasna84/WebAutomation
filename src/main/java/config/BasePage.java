package main.java.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {

    public static Integer TIMEOUT_WEBDRIVER_WAIT = 10;

    private static final Logger logger = LogManager.getLogger(BasePage.class.getSimpleName());

    public BasePage() {
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
        System.out.println("Page created: " + this.getClass().getName());
    }


    public boolean waitForElement(By by, int attempts) {

        boolean condition = false;
        int waitUntilNow = 0;
        while (!condition && waitUntilNow < attempts) {
            try {
                condition = (new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT))
                        .until(sizes ->Driver.getInstance().getDriver().findElements(by).size()!=0);
            } catch(TimeoutException e) {
                System.out.println("Element: "+by+" was not found within 10 sec. Trying again ...");
            }
            waitUntilNow = waitUntilNow + 1;
        }

        if(waitUntilNow == attempts) {
            System.out.println("Element was not found: "+by + " number of attempts: "+attempts+", each attempts lasted 10sec.");
        }

        return condition;
    }

    public void clickJS(By by) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.elementToBeClickable(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) (Driver.getInstance().getDriver());
        js.executeScript("arguments[0].click();", element);
    }

    public void clickJS(WebElement element) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor) (Driver.getInstance().getDriver());
        js.executeScript("arguments[0].click();", element);
    }

    public void click(By by) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.elementToBeClickable(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        element.click();
    }

    public void click(WebElement element) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendText(By by, String text) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public static String getUrl() {
        hardCodedWaiter(2000);
        return Driver.getInstance().getDriver().getCurrentUrl();
    }

    public List<WebElement> getListElements(By by) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        return Driver.getInstance().getDriver().findElements(by);
    }

    public List<String> getListOfStrings(By by) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        List<WebElement> elements = Driver.getInstance().getDriver().findElements(by);
        List<String> elementsText = new ArrayList<>();
        for(WebElement element : elements){
            elementsText.add(element.getText());
        }
        return elementsText;
    }

    public String getElementText(By by) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        return element.getText();
    }

    public String getFirstOption(By by) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        Select select = new Select(Driver.getInstance().getDriver().findElement(by));
        return select.getFirstSelectedOption().getText();
    }

    public static void hardCodedWaiter(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getElementAttribute(By by, String text) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        return element.getAttribute(text);
    }

    public void enterSearchTerm(By by, String text) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        element.clear();
        element.sendKeys(text);
        hardCodedWaiter(2000);
        element.sendKeys(Keys.SPACE);
        hardCodedWaiter(2000);
    }

    public void scrollTo(WebElement webelement) {
        JavascriptExecutor js = (JavascriptExecutor) (Driver.getInstance().getDriver());
        js.executeScript("arguments[0].scrollIntoView();", webelement);
    }

    public boolean exist() {
        return false;
    }

    public String getAlertText() {
        waitForAlert();
        return Driver.getInstance().getDriver().switchTo().alert().getText();
    }

    public void acceptAlert() {
        waitForAlert();
        Driver.getInstance().getDriver().switchTo().alert().accept();
    }

    public void dismissAlert() {
        waitForAlert();
        Driver.getInstance().getDriver().switchTo().alert().dismiss();
    }

    public void enterTextToAlert(String text) {
        waitForAlert();
        Driver.getInstance().getDriver().switchTo().alert().sendKeys(text);
    }

    public void waitForAlert() {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.alertIsPresent());
    }

    public boolean isAlertPresent() {
        try {
            new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public String getWindowHandle() {
        return Driver.getInstance().getDriver().getWindowHandle();
    }

    public void switchToNewWindow() {
        for(String winHandle : Driver.getInstance().getDriver().getWindowHandles()){
            Driver.getInstance().getDriver().switchTo().window(winHandle);
        }
    }

    public void switchBackToPreviousWindow() {
        String winHandleBefore = getWindowHandle();
        Driver.getInstance().getDriver().switchTo().window(winHandleBefore);
    }

    public void pressKeys(Keys key) {
        new Actions(Driver.getInstance().getDriver()).sendKeys(key).perform();
    }

    public void hoverOverElement(WebElement element) {
       new Actions(Driver.getInstance().getDriver()).moveToElement(element).perform();
    }

    public void hoverOverElement(By by) {
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        new Actions(Driver.getInstance().getDriver()).moveToElement(element).perform();
    }

}
