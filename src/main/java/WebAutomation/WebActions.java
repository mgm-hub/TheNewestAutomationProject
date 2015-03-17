package WebAutomation;

import WebData.WebDictionary;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class WebActions {
    static WebDictionary theWebDictionary;
    static DataBuilder theDataBuilder;

    public WebActions(DataBuilder myDataBuilder) {
        theDataBuilder = myDataBuilder;
        theWebDictionary = new WebDictionary(myDataBuilder.theJSONDictionaryData);
    }

    public static void findAndWriteForElement(String myText, WebElement myElement, String myPath) {
        if (myElement != null && myElement.isDisplayed()) {
            myElement.sendKeys(myText);
        }
        else {
            String errorMessage = ("\nError - Web (findAndWriteForElement) - element is hidden : " + myPath);
            System.out.print(errorMessage);

        }
    }

    public static void clearText (String myPath, WebDriver driver) {
        WebElement myElement = getElement(myPath, driver);
        if (myElement != null && myElement.isDisplayed()) {
            myElement.clear();
        }
        else {
            String errorMessage = ("\nError - Web (clearText) - element is hidden : " + myPath + " : " + theWebDictionary.xpathReturn(myPath));
            System.out.print(errorMessage);

        }
    }

    public static void findAndClickElement(String myPath, WebDriver driver) {
        WebElement myElement = getElement(myPath, driver);

       // JavascriptExecutor executor = (JavascriptExecutor)driver;
       // executor.executeScript("arguments[0].click();", myElement);

        if (myElement != null && myElement.isDisplayed()) {
            clickElement(myElement, theWebDictionary.xpathReturn(myPath), driver);
        }
        else {
            String errorMessage = ("\nError - Web (findAndClickElement) - element is hidden : " + myPath + " : " + theWebDictionary.xpathReturn(myPath));
            System.out.print(errorMessage);

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
            String errorMessage = ("\nError - Web (hoverOverElement) - element is hidden : " + myPath + " : " + theWebDictionary.xpathReturn(myPath));
            System.out.print(errorMessage);

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
                String errorMessage = ("\nError - Web (elementIsVisible) - element is hidden : " + myPath + " : " + theWebDictionary.xpathReturn(myPath));
                System.out.print(errorMessage);

                return false;
            }
        } else {
//FIX
            String errorMessage = ("\nError - Web (elementIsVisible) - element doesn't exist  : " + myPath + " : " + theWebDictionary.xpathReturn(myPath));
            System.out.print(errorMessage);

            return false;
        }
    }

    public static void clickElement(WebElement myElement, String myPath, WebDriver driver) {
        if (myElement != null && myElement.isDisplayed()) {
            myElement.click();
        }
        else if (myElement.isDisplayed() == false) {
//FIX
            String errorMessage = ("\nError - Web (clickElement) - Element is not displayed Error : " + myPath);
            System.out.print(errorMessage);

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
                String errorMessage = ("\nError - Web (elementTextCompare) - Text Not Equal : " + myText +  " -- -- " + myCheckText);
                System.out.print(errorMessage);

            }
        }
        else {
//FIX
            String errorMessage = ("\nError - Web (elementTextCompare) - No get text : " + myText +  " or no compare text : " + myCheckText);
            System.out.print(errorMessage);

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
                String errorMessage = ("\nError - Web (elementTextReturn) - No Text : " + myPath);
                System.out.print(errorMessage);

                return "";
            }
        }
        else {
//FIX
            String errorMessage = ("\nError - Web (elementTextReturn) - Element is not displayed Error : " + myPath);
            System.out.print(errorMessage);

            return "";
        }
    }

    //
    ////
    //

    public static WebElement getElement(String myPath, WebDriver driver) {
        try {
        WebElement myElement = driver.findElement(By.xpath(theWebDictionary.xpathReturn(myPath)));
            if (myElement != null) {
                return myElement;
            }
            else {
                return null;
            }
        } catch (Exception e) {
            String errorMessage = ("\nError - Web (getElement) - no element : " + myPath + " : " + theWebDictionary.xpathReturn(myPath));
            System.out.print(errorMessage);

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
            String errorMessage = ("\nError - Web (checkURL) - URL does not match");
            System.out.print(errorMessage);

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
                String errorMessage = ("\nError - Web (webWait) - thread pause length)");
                System.out.print(errorMessage);

            }
        }
        catch (Exception error) {
            String errorMessage = ("\nError - Web (webWait) - could not pause thread)");
            System.out.print(errorMessage);


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
