package WebAutomation;

import TestReporter.TestEventClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public class DataBuilder {

    final static String DICTIONARY_KEY_01 = "testSuiteList";
    final static String DICTIONARY_KEY_02 = "testSuite";

    public static Map <String, String> theJSONDictionaryData;
    public static String theSuiteGroupName;
    public static String theSuiteFileName;
    public static TestListDataClass theTestListDataClass;
    public static String theUSRDirectory;
    public static TestEventClass myTestEventClass = new TestEventClass();

    //init
    public DataBuilder(String mySuiteGroupName, String mySuiteFileName) {
        theSuiteGroupName = mySuiteGroupName;
        theSuiteFileName = mySuiteFileName;
        String initStatement = "\nInit - DataBuilder -- "+theSuiteGroupName + " : " +theSuiteFileName;
        System.out.print(initStatement);
        theUSRDirectory = myUSRDirectory(mySuiteGroupName,mySuiteFileName);
        mapForJSONObject(theUSRDirectory);

        DictionaryDataBuilder myDictionaryDataBuilder = new DictionaryDataBuilder(theSuiteGroupName);
        theJSONDictionaryData = myDictionaryDataBuilder.theJSONDictionaryData;
    }

    //
    ////
    //

    //1.0 - main object builder
    private static void mapForJSONObject(String myFilePath) {
        //1.1
        JSONObject myJSONObject = readJSONFromFile(myFilePath);
        if (readJSONFromFile(myFilePath) != null) {
            //1.2
            objectListFromJSONBuilder(myJSONObject);
        } else {
            String errorMessage = "\nError - Databuilder (mapForJSONObject) - No File Path";
            System.out.print(errorMessage);
        }
    }

    //
    ////
    //

    // 1.1 - file reader
    private static JSONObject readJSONFromFile(String myFilePath) {
        try {
            JSONParser parser = new JSONParser();
            File myFile = new File(myFilePath);
            if (myFile.exists()){
                Object obj = parser.parse(new FileReader(myFilePath));
                return (JSONObject) obj;
            }
            else {
                String errorMessage = "\nError - Databuilder (readJSONFromFile) - no file at path";
                System.out.print(errorMessage);
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    // 1.2 - json mapper
    private static void objectListFromJSONBuilder(JSONObject myJSONOobject){
        JSONObject myTestSuiteFile = (JSONObject) myJSONOobject.get(DICTIONARY_KEY_01);

        if (myTestSuiteFile != null) {
            //load in testsuites!!!!
            JSONArray myTestSuiteList = (JSONArray) myTestSuiteFile.get(DICTIONARY_KEY_02);
            testListDataSetter(myTestSuiteList); //sets test list here
        } else {
            String errorMessage = "\nError - Databuilder (objectListFromJSONBuilder) - testSuiteList dictionary error";
            System.out.print(errorMessage);
        }
    }

    //
    ////
    //

    //1.3
    private static void testListDataSetter (JSONArray myTestSuiteList) {
        int myNumberOfTests = myTestSuiteList.size();

        // iterate through all test cases
        List<JSONObject> testCases = new ArrayList<JSONObject>();
        for (int i = 0; i < myNumberOfTests; i++) {
            JSONObject myTestSuite = singleTestObject(myTestSuiteList,i);
            if (myTestSuite != null) {
                testCases.add(myTestSuite);
            }
        }

        TestListDataClass myTestListData = new TestListDataClass();
        JSONObject[] myTestCases = new JSONObject[myTestSuiteList.size()];
        testCases.toArray(myTestCases);
        myTestListData.testCases = myTestCases;
        myTestListData.numberOfTests = myNumberOfTests;

        if (myTestListData != null) {
            theTestListDataClass = myTestListData;
        }
        else {
            String errorMessage = "\nError - Databuilder (testListDataSetter) - test list data is null";
            System.out.print(errorMessage);
        }
    }



    //
    ////
    //

    //1.5 test iteration
    private static JSONObject singleTestObject (JSONArray myTestSuiteList, int myTestNumber) {
        JSONObject myTestSuite;
        if (myTestSuiteList != null) {
            myTestSuite = (JSONObject) myTestSuiteList.get(myTestNumber);
            return myTestSuite;
        } else {
            String errorMessage = "\nError - Databuilder (singleTestObject) - test number error";
            System.out.print(errorMessage);
            return null;
        }
    }

    //
    ////
    //

    //0.0
    private static String myUSRDirectory (String mySuiteName, String myFileName) {
        String USR_DIRECTORY = System.getProperty("user.dir");
        String OS_NAME = System.getProperty("os.name");
        String myPathAdd;
        if (OS_NAME.equals("Mac OS X")){
            myPathAdd = "/src/main/java/WebData/"+mySuiteName+"/"+myFileName+".json";
        }
        else{
            myPathAdd = "\\src\\main\\java\\WebData\\"+mySuiteName+"\\"+myFileName+".json";
        }
        String fullPath = USR_DIRECTORY + myPathAdd;
        return fullPath;
    }

}
