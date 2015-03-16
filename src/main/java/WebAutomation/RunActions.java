package WebAutomation;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

/**
 * Created by mmoscatello on 3/16/2015.
 */
public class RunActions {

    public static void runSelectCases (int[] myTestArray,String mySuiteName) {
        WebActions myWebActions = new WebActions();
        DataActions myDataActions = new DataActions();
        DataBuilder myDataBuilder = new DataBuilder();

        WebDriver driver = myWebActions.getWebDriver();
        TestListDataClass myTestGroup = myDataBuilder.mapForJSONObject(myDataBuilder.myUSRDirectory(mySuiteName));

        if (myTestGroup != null) {
            int myCompleteTestCaseCount = myTestGroup.testCases.length;
            int myTestArrayCount = myTestArray.length;

            for (int i = 0; i < myTestArrayCount; i++) {
                int myTestNumber;
                JSONObject myTestObject;
                myTestNumber = myTestArray[i];
                if (myTestNumber < myCompleteTestCaseCount) {
                    myTestObject = myTestGroup.testCases[myTestNumber];
                    myDataActions.testSelectedAction(myTestObject, driver);
                }
                else {
                    String errorMessage = "Error - Main (runSelectCases) - test number outside of bounds";
                    System.out.print(errorMessage);
                }
            }
        }
        else {
            String errorMessage = "Error - Main (runSelectCases) - test group is empty";
            System.out.print(errorMessage);
        }
    }

    public static void runAllCases(String mySuite) {
        WebActions myWebActions = new WebActions();
        DataActions myDataActions = new DataActions();
        DataBuilder myDataBuilder = new DataBuilder();

        WebDriver driver = myWebActions.getWebDriver();
        TestListDataClass myTestGroup = myDataBuilder.mapForJSONObject(myDataBuilder.myUSRDirectory(mySuite));

        if (myTestGroup != null) {
            int myCompleteTestCaseCount = myTestGroup.testCases.length;

            for (int i = 0; i < myCompleteTestCaseCount; i++) {
                int myTestNumber;
                JSONObject myTestObject;
                myTestNumber = i;
                myTestObject = myTestGroup.testCases[myTestNumber];
                myDataActions.testSelectedAction(myTestObject, driver);
            }
        }
        else {
            String errorMessage = "Error - Main (runAllCases) - test group is empty";
            System.out.print(errorMessage);
        }
    }
}
