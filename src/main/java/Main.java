
import TestReporter.TestEventClass;
import WebAutomation.*;
import WebData.WebDictionary;
import org.openqa.selenium.WebDriver;
import org.json.simple.JSONObject;

public class Main  {

    static TestEventClass myTestEventClass = new TestEventClass();
    static RunActions myRunActions  = new RunActions();

    public static void main(String[] args) {
        System.out.print("Main Run Start\n");

        myTestEventClass = myTestEventClass.loadTestEventClass();

        //myErrorEventClass.myTestString = "new";
        //System.out.print(myErrorEventClass.myTestString);

        //runSpecific();
        TESTPath();

        System.out.print("\nMain Run Finish\n");
    }

    public static void runSpecific () {
        int[] myCaseArray = {0};
        String mySuite = "Core";
        myRunActions.runSelectCases(myCaseArray,mySuite);
    }

    public static void runAll () {
        String mySuite = "Core";
        myRunActions.runAllCases(mySuite);
    }

    //
    ////
    //

    public static void TESTPath() {
        WebActions myWebActions = new WebActions();
        DataActions myDataActions = new DataActions();
        DataBuilder myDataBuilder = new DataBuilder();
        TestListDataClass myTestGroup = myDataBuilder.mapForJSONObject(myDataBuilder.myUSRDirectory("Core"));
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
        TestListDataClass myTestGroup = myDataBuilder.mapForJSONObject(myDataBuilder.myUSRDirectory("Core"));

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



