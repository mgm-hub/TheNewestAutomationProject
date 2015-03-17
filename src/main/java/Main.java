
import TestReporter.TestEventClass;
import TestReporter.Timeclass;
import WebAutomation.*;
import org.openqa.selenium.WebDriver;
import org.json.simple.JSONObject;

public class Main  {

    static TestEventClass myTestEventClass = new TestEventClass();
    static RunActions myRunActions  = new RunActions();

    public static void main(String[] args) {
        System.out.print("\nMain Run Start");

        myTestEventClass = myTestEventClass.loadTestEventClass();
        myTestEventClass.startTest();
        myTestEventClass.endTest();
        //myErrorEventClass.myTestString = "new";
        //System.out.print(myErrorEventClass.myTestString);





        //runSpecific();
        //runAll();




        System.out.print("\nMain Run Finish");
    }

    public static void runSpecific () {
        int[] myCaseArray = {1};
        String mySuite = "Core";
        String myFileName = "Core";
        myRunActions.runSelectCases(myCaseArray,mySuite,myFileName);
    }

    public static void runAll () {
        String mySuite = "Core";
        String myFileName = "Core";
        myRunActions.runAllCases(mySuite,myFileName);
    }

    //
    ////
    //














    public static void TESTmyRunKit () {
        DataBuilder myDataBuilder = new DataBuilder("Core","Core");
        TestListDataClass myTestGroup = myDataBuilder.theTestListDataClass;
        DataActions myDataActions = new DataActions(myDataBuilder);
        WebActions myWebActions = new WebActions(myDataBuilder);
        WebDriver driver = myWebActions.getWebDriver();

        int myTestNumber;
        JSONObject myTestObject;

        myTestNumber = 1;
        myTestObject = myTestGroup.testCases[myTestNumber];

        int myint = myTestGroup.testCases.length;
        System.out.print(myint);

        //myDataActions.testSelectedAction(myTestObject, driver);
    }

    public static void TESTPath() {
        DataBuilder myDataBuilder = new DataBuilder("Core","Core");
        TestListDataClass myTestGroup = myDataBuilder.theTestListDataClass;
        DataActions myDataActions = new DataActions(myDataBuilder);
        WebActions myWebActions = new WebActions(myDataBuilder);
    }



// MAKE A DICTIONARY LIST CHECKER
////div[@id='bigCalendar']/table/tbody/tr["+s+"]/td["+t+"]/div[2]/a/span[2]
//Arrays.asList(myTestArray).contains(1)





}



