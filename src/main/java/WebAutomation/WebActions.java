package WebAutomation;

import WebData.WebDictionary;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class WebActions {
    static WebDictionary myWebDictionary = new WebDictionary();

    public static void findAndWriteForElement(String myText, WebElement myElement, String myPath) {
        if (myElement != null && myElement.isDisplayed()) {
            myElement.sendKeys(myText);
        }
        else {
            System.out.print("\nError - Web (findAndWriteForElement) - element is hidden : " + myPath);
        }
    }

    public static void clearText (String myPath, WebDriver driver) {
        WebElement myElement = getElement(myPath, driver);
        if (myElement != null && myElement.isDisplayed()) {
            myElement.clear();
        }
        else {
            System.out.print("\nError - Web (clearText) - element is hidden : " + myPath + " : " + myWebDictionary.xpathReturn(myPath));
        }
    }

    public static void findAndClickElement(String myPath, WebDriver driver) {
        WebElement myElement = getElement(myPath, driver);

       // JavascriptExecutor executor = (JavascriptExecutor)driver;
       // executor.executeScript("arguments[0].click();", myElement);

        if (myElement != null && myElement.isDisplayed()) {
            clickElement(myElement, myWebDictionary.xpathReturn(myPath), driver);
        }
        else {
            System.out.print("\nError - Web (findAndClickElement) - element is hidden : " + myPath + " : " + myWebDictionary.xpathReturn(myPath));
            //System.out.print("\nRan JS");
        }
    }

    //
    ////
    //

    public static void hoverOverElement (String myPath, WebDriver driver) {
        WebElement myElement = getElement(myPath, driver);
        if (myElement != null && myElement.isDisplayed()) {
            Actions action = new Actions(driver);
            action.moveToElement(myElement);
            action.perform();
            action = null;
        }
        else {
            System.out.print("\nError - Web (hoverOverElement) - element is hidden : " + myPath + " : " + myWebDictionary.xpathReturn(myPath));
        }
    }

    public static boolean elementIsVisible(String myPath, WebDriver driver) {
        WebElement myElement = getElement(myPath, driver);
        if (myElement != null) {
            if (myElement.isDisplayed()) {
                //empty
                return true;
            } else {
//FIX
                System.out.print("\nError - Web (elementIsVisible) - element is hidden : " + myPath + " : " + myWebDictionary.xpathReturn(myPath));
                return false;
            }
        } else {
//FIX
            System.out.print("\nError - Web (elementIsVisible) - element doesn't exist  : " + myPath + " : " + myWebDictionary.xpathReturn(myPath));
            return false;
        }
    }

    public static void clickElement(WebElement myElement, String myPath, WebDriver driver) {
        if (myElement != null && myElement.isDisplayed()) {
            myElement.click();
        }
        else if (myElement.isDisplayed() == false) {
//FIX
            System.out.print("\nError - Web (clickElement) - Element is not displayed Error : " + myPath);
            //JavascriptExecutor executor = (JavascriptExecutor)driver;
            //(JavascriptExecutor(driver)).executeScript("document.getElementsByClassName('post-tag')[0].click();");
            //document.getElementsByClassName('post-tag')[0].click();
            //String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
            //String newJS = "document.getElementsByClassName("+myPath+")[0].click()";
            //((JavascriptExecutor) driver).executeScript(newJS);
            //System.out.print("\n Ran JS");
            //executor.executeScript("arguments[0].click();", myElement);
            //wait.until( ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='register']")));
            //new WebDriverWait(Driver, TimeSpan.FromSeconds(10)).Until(ExpectedConditions.ElementIsVisible("your selector");
        }
    }

    //
    ////
    //

    public static void elementTextCompare (WebElement myElement, String myPath, String myCheckText) {
        String myText = elementTextReturn(myElement,myPath);
        if (myText.length() > 0 && myCheckText.length() > 0) {
            if (myText.equals(myCheckText)){
                //Empty - is correct
            }
            else {
//FIX
                System.out.print("\nError - Web (elementTextCompare) - Text Not Equal : " + myText +  " -- -- " + myCheckText);
            }
        }
        else {
//FIX
            System.out.print("\nError - Web (elementTextCompare) - No get text : " + myText +  " or no compare text : " + myCheckText);
        }
    }

     //
    ////
    //

    public static String elementTextReturn (WebElement myElement, String myPath) {
        if (myElement != null && myElement.isDisplayed()) {
            String myText = myElement.getText();
            if (myText.length() > 0) {
                return myText;
            } else {
//FIX
                System.out.print("\nError - Web (elementTextReturn) - No Text : " + myPath);
                return "";
            }
        }
        else {
//FIX
            System.out.print("\nError - Web (elementTextReturn) - Element is not displayed Error : " + myPath);
            return "";
        }
    }

    //
    ////
    //

    public static WebElement getElement(String myPath, WebDriver driver) {
        try {
        WebElement myElement = driver.findElement(By.xpath(myWebDictionary.xpathReturn(myPath)));
            if (myElement != null) {
                return myElement;
            }
            else {
                return null;
            }
        } catch (Exception e) {
            System.out.print("\nError - Web (getElement) - no element : " + myPath + " : " + myWebDictionary.xpathReturn(myPath));
            return null;
        }
    }

    //
    ////
    //

    public static void checkURL (String myText, WebDriver driver) {
        String url = driver.getCurrentUrl();
        if (url.equals(myText)){
//FIX
        }
        else {
//FIX
            System.out.print("\nError - Web (checkURL) - URL does not match");
        }
    }

    //
    ////
    //

    public static void webWait (String  mypath) {
        try {
            int myInt = Integer.parseInt(mypath);
            if (myInt > 1 && myInt < 100000) {
                Thread.sleep(myInt);
            }
            else {
                System.out.print("\nError - Web (webWait) - thread pause length)");
            }
        }
        catch (Exception error) {
            System.out.print("\nError - Web (webWait) - could not pause thread)");

        }
    }

    //
    ////
    //

    public static WebDriver getWebDriver() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(120000, TimeUnit.MILLISECONDS);
        return driver;
    }

    public static void closeWebDriver(WebDriver driver) {
        if (driver != null) {
            driver.close();
        }
    }

    //
    ////
    //

    public static void webClose (WebDriver driver) {
        closeWebDriver(driver);
    }

}
