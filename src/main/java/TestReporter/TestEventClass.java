package TestReporter;

public class TestEventClass {
    
    static String myTestPath;
    static String myTestID;
    static float myStartTime = 0;
    static float myEndTime = 0;
    static float myCompletionTime = 0;
    static Timeclass myTimeclass = new Timeclass();

    public static String myCurrentCaseNumber = "";
    public static String myCurrentError = "";

    public static TestEventClass loadTestEventClass () {
        TestEventClass myErrorEventClass = new TestEventClass();
        return myErrorEventClass;
    }

    public static void startTest() {
        myTimeclass.resetValues();
        myTimeclass.setDate();
        myTimeclass.setStartTime();
    }

    public static void endTest() {
        myTimeclass.setEndTime();
        myStartTime = myTimeclass.startTime;
        myEndTime = myTimeclass.endTime;
        myCompletionTime = myTimeclass.completionTime;
        myTimeclass.printTime();
    }

    public static void reportError () {
        System.out.print("check");


    }




}
