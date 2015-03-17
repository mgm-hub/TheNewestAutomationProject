package TestReporter;

public class TestEventClass {
    
    static String myTestPath;
    static String myTestID;
    static int mySTartTime;
    static int myEndTime;
    static Timeclass myTimeclass = new Timeclass();

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
        myTimeclass.printTime();
    }

    public static void reportError () {
        System.out.print("check");


    }




}
