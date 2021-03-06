package WebAutomation;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DataActions {

    static WebActions myWebActions;
    static DataBuilder theDataBuilder;
    static DataPathActions myDataMapper = new DataPathActions();
    final static String DICTIONARY_KEY_URL = "baseURL";

    public DataActions(DataBuilder myDataBuilder) {
        theDataBuilder = myDataBuilder;
        myWebActions = new WebActions(myDataBuilder);
    }

    //
    ////
    //

    // 4.0 test choice - ACTION-IN
    public static void testSelectedAction (JSONObject myObject, WebDriver driver) {
        String baseURL = (String) myObject.get(DICTIONARY_KEY_URL);
        driver.get(baseURL);
        //path array builder
        PathDataClass[] myPathArray = myDataMapper.pathDataReturn(myObject);
        //run test
        mainTestRunAction(driver, myPathArray);
    }

    //
    ////
    //

    //4.1 automation action
    private static void mainTestRunAction(WebDriver driver, PathDataClass[] myPathArray) {
        int myLength = myPathArray.length;
        if (myLength > 0 && driver != null) {
            int myCount = 0;
            for (PathDataClass path : myPathArray) {

                // ACTION - NUMBER HERE
                theDataBuilder.myTestEventClass.myCurrentCaseNumber = Integer.toString(myCount);
                System.out.print("\nCase number : "+theDataBuilder.myTestEventClass.myCurrentCaseNumber);

                myCount = pathChoiceAction(path, myCount, driver, myPathArray);
            }
        }
    }

    //
    ////
    //

    //4.2
    private static int pathChoiceAction (PathDataClass path, int myCount, WebDriver driver, PathDataClass[] myPathArray) {
        if (path.command.equals("click")){ //case 1
            myWebActions.findAndClickElement(path.path, driver);
        }
        else if (path.command.equals("wait")) { //case 2
            myWebActions.webWait(path.path);
        }
        else if (path.command.equals("writePRE") && myCount >= 1){ //case 3
            PathDataClass myWritePath = myPathArray[myCount-1];
            if (myWritePath != null) {
                WebElement myElement = myWebActions.getElement(myWritePath.path, driver);
                if (myElement != null){
                    myWebActions.findAndWriteForElement(path.path, myElement, myWritePath.path);
                }
                else {
                    String errorMessage = "\nError - Action (pathChoiceAction) - element for write paths is null";
                    System.out.print(errorMessage);
                }
            }
            else {
                String errorMessage = "\nError - Action (pathChoiceAction) - write path is null";
                System.out.print(errorMessage);
            }
        }
        else if (path.command.equals("find")) { //case 4
            myWebActions.elementIsVisible(path.path,driver);
        }
        else if (path.command.equals("finished")) { //case 5
//FIX COMPLETE
            String completionMessage = "\n"+path.path + " - Complete";
            System.out.print(completionMessage);
        }
        else if (path.command.equals("closebrowser")) { //case 6

            String completionMessage = "\nBrowser Closed";
            System.out.print(completionMessage);
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
            PathDataClass myWritePath = myPathArray[myCount-1];
            if (myWritePath != null) {
                WebElement myElement = myWebActions.getElement(myWritePath.path, driver);
                if (myElement != null){
                    myWebActions.elementTextCompare(myElement, myWritePath.path, path.path );
                }
                else {
                    String errorMessage = "\nError - Action (pathChoiceAction) - element for write paths";
                    System.out.print(errorMessage);
                }
            }
            else {
                String errorMessage = ("\nError - Action (pathChoiceAction) - write path is null");
                System.out.print(errorMessage);
            }
        }
        else {
            String errorMessage = ("\nError - command case not covered");
            System.out.print(errorMessage);
        }
        myCount++; //Count HERE
        return  myCount;
    }



}
