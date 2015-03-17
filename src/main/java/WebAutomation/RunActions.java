package WebAutomation;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

/**
 * Created by mmoscatello on 3/16/2015.
 */
public class RunActions {

    public static void runSelectCases (int[] myTestArray,String mySuiteGroupName, String mySuiteFileName) {
        DataBuilder myDataBuilder = new DataBuilder(mySuiteGroupName,mySuiteFileName);
        TestListDataClass myTestGroup = myDataBuilder.theTestListDataClass;
        DataActions myDataActions = new DataActions(myDataBuilder);
        WebActions myWebActions = new WebActions(myDataBuilder);
        WebDriver driver = myWebActions.getWebDriver();

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

    public static void runAllCases(String mySuiteGroupName, String mySuiteFileName) {
        DataBuilder myDataBuilder = new DataBuilder(mySuiteGroupName,mySuiteFileName);
        TestListDataClass myTestGroup = myDataBuilder.theTestListDataClass;

        WebActions myWebActions = new WebActions(myDataBuilder);
        DataActions myDataActions = new DataActions(myDataBuilder);
        WebDriver driver = myWebActions.getWebDriver();

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
