
import TestReporter.TestEventClass;
import WebAutomation.*;
import WebData.WebDictionary;
import org.openqa.selenium.WebDriver;
import org.json.simple.JSONObject;

public class Main  {

    static TestEventClass myTestEventClass = new TestEventClass();

    public static void main(String[] args) {
        System.out.print("Main Run Start\n");

        myTestEventClass = myTestEventClass.loadTestEventClass();

        //myErrorEventClass.myTestString = "new";
        //System.out.print(myErrorEventClass.myTestString);

        //runAllCases();

        int[] myCaseArray = {0};
        String mySuite = "Core";

        runSelectCases(myCaseArray,mySuite);



        System.out.print("\nMain Run Finish\n");
    }

    //
    ////
    //

    public static void runSelectCases (int[] myTestArray,String mySuiteName) {
        WebActions myWebActions = new WebActions();
        DataActions myDataActions = new DataActions();
        DataBuilder myDataBuilder = new DataBuilder();

        WebDriver driver = myWebActions.getWebDriver();
        TestListData myTestGroup = myDataBuilder.mapForJSONObject(myDataBuilder.myUSRDirectory(mySuiteName));

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
        TestListData myTestGroup = myDataBuilder.mapForJSONObject(myDataBuilder.myUSRDirectory(mySuite));

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

    //
    ////
    //

    ////div[@id='bigCalendar']/table/tbody/tr["+s+"]/td["+t+"]/div[2]/a/span[2]
    //Arrays.asList(myTestArray).contains(1)

















    public static void TESTmyRunKit () {
        WebActions myWebActions = new WebActions();
        DataActions myDataActions = new DataActions();
        DataBuilder myDataBuilder = new DataBuilder();

        WebDriver driver = myWebActions.getWebDriver();
        TestListData myTestGroup = myDataBuilder.mapForJSONObject(myDataBuilder.myUSRDirectory("Core"));

        int myTestNumber;
        JSONObject myTestObject;

        myTestNumber = 1;
        myTestObject = myTestGroup.testCases[myTestNumber];

        int myint = myTestGroup.testCases.length;
        System.out.print(myint);

        //myDataActions.testSelectedAction(myTestObject, driver);
    }

// MAKE A DICTIONARY LIST CHECKER
    public static void TESTDictionary () {
        WebDictionary myWebDictionary = new WebDictionary();
        //myWebDictionary.loadDictionary();
        String myString = myWebDictionary.xpathReturn("home_signup");
        System.out.print("\n");
        String myNewString = myWebDictionary.xpathReturn("home_login");
        System.out.print("\n");
        System.out.print(myString);
    }

}



