package WebAutomation;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DataActions {

    final static WebActions myWebActions = new WebActions();
    final static DataBuilder myDataBuilder = new DataBuilder();
    final static String DICTIONARY_KEY_URL = "baseURL";

    //
    ////
    //

    // 5.0 test choice
    public static void testSelectedAction (JSONObject myObject, WebDriver driver) {
        String baseURL = (String) myObject.get(DICTIONARY_KEY_URL);
        driver.get(baseURL);
        //path array builder
        PathData[] myPathArray = myDataBuilder.pathDataReturn(myObject);
        //run test
        mainTestRunAction(driver, myPathArray);
    }

    //
    ////
    //

    //4.1
    public static int pathChoiceAction (PathData path, int myCount, WebDriver driver, PathData[] myPathArray) {
        if (path.command.equals("click")){ //case 1
            myWebActions.findAndClickElement(path.path, driver);
        }
        else if (path.command.equals("wait")) { //case 2
            myWebActions.webWait(path.path);
        }
        else if (path.command.equals("writePRE") && myCount >= 1){ //case 3
            PathData myWritePath = myPathArray[myCount-1];
            if (myWritePath != null) {
                WebElement myElement = myWebActions.getElement(myWritePath.path, driver);
                if (myElement != null){
                    myWebActions.findAndWriteForElement(path.path, myElement, myWritePath.path);
                }
                else {
                    System.out.print("\nError - Action (pathChoiceAction) - element for write paths");
                }
            }
            else {
                System.out.print("\nError - Action (pathChoiceAction) - write path");
            }
        }
        else if (path.command.equals("find")) { //case 4
            myWebActions.elementIsVisible(path.path,driver);
        }
        else if (path.command.equals("finished")) { //case 5
//FIX
            System.out.print(path.path + " - Complete");
        }
        else if (path.command.equals("closebrowser")) { //case 6
            System.out.print("\nBrowser Closed");
            myWebActions.webClose(driver);
        }
        else if (path.command.equals("hover")) { //case 7
            myWebActions.hoverOverElement(path.path, driver);
        }
        else if (path.command.equals("url")) { // case 8
            myWebActions.checkURL(path.path, driver);
        }
        else if (path.command.equals("clear")) { //case 9
            myWebActions.clearText(path.path, driver);
        }
        else if (path.command.equals("compareTextPRE") && myCount >= 1) { //case 10
            PathData myWritePath = myPathArray[myCount-1];
            if (myWritePath != null) {
                WebElement myElement = myWebActions.getElement(myWritePath.path, driver);
                if (myElement != null){
                    myWebActions.elementTextCompare(myElement, myWritePath.path, path.path );
                }
                else {
                    System.out.print("\nError - Action (pathChoiceAction) - element for write paths");
                }
            }
            else {
                System.out.print("\nError - Action (pathChoiceAction) - write path is null");
            }
        }
        else {
            System.out.print("\nError - command case not covered");
        }
        myCount++; //Count HERE
        return  myCount;
    }

    //4.0 automation action
    public static void mainTestRunAction(WebDriver driver, PathData[] myPathArray) {
        int myLength = myPathArray.length;
        if (myLength > 0 && driver != null) {
            int myCount = 0;
            for (PathData path : myPathArray) {
                myCount = pathChoiceAction(path, myCount, driver, myPathArray);
            }
        }
    }

}