package WebAutomation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public class DataBuilder {

    final static int NUMBER_OF_OBJECTS_IN_PATH_ARRAY = 2;
    final static String DICTIONARY_KEY_01 = "testSuiteList";
    final static String DICTIONARY_KEY_02 = "testSuite";
    final static String DICTIONARY_KEY_PATHLIST = "pathList";
    final static String DICTIONARY_KEY_DICTIONARY = "testDictionary";

    public static  Map<String, String>  theJSONDictionaryData;
    public static String theSuiteGroupName;
    public static String theSuiteFileName;
    public static TestListDataClass theTestListDataClass;
    public static String theUSRDirectory;

    //init
    public DataBuilder(String mySuiteGroupName, String mySuiteFileName) {

        theSuiteGroupName = mySuiteGroupName;
        theSuiteFileName = mySuiteFileName;
        System.out.print("\nInit - DataBuilder -- "+theSuiteGroupName + " : " +theSuiteFileName);
        theUSRDirectory = myUSRDirectory(mySuiteGroupName,mySuiteFileName);
        theTestListDataClass = mapForJSONObject(theUSRDirectory);

    }

    //3.2
    public static PathDataClass[] pathDataReturn (JSONObject myObject) {
        JSONArray myArray = (JSONArray) myObject.get(DICTIONARY_KEY_PATHLIST);

        JSONObject myPD = (JSONObject) myArray.get(0);
        List<PathDataClass> myPathList = new ArrayList<PathDataClass>();
        for (int i = 0; i < myPD.size(); i++) {
            String myIntString = Integer.toString(i);
            JSONArray myPathArray = (JSONArray) myPD.get(myIntString);
            PathDataClass myPathData = pathDataMapper(myPathArray);
            if (myPathData != null) {
                myPathList.add(myPathData);
            }
        }
        PathDataClass[] myPathDataArray = new PathDataClass[myPathList.size()];
        myPathList.toArray(myPathDataArray);

        return myPathDataArray;
    }

    //3.1
    public static PathDataClass pathDataMapper (JSONArray myPathArray) {
        if (myPathArray.size() == NUMBER_OF_OBJECTS_IN_PATH_ARRAY) {
            String myCommand = (String) myPathArray.get(0);
            String myPath = (String) myPathArray.get(1);
            return pathDataBuilder(myCommand, myPath);
        }
        else {
            System.out.print("Error JSONPathArray is null for Mapper");
            return null;
        }
    }

    //3.0
    public static PathDataClass pathDataBuilder (String myCommand, String myPath ) {
        if (myCommand.length() > 0 && myPath.length() > 0) {
            PathDataClass myPathData = new PathDataClass();
            myPathData.command = myCommand;
            myPathData.path = myPath;
            return myPathData;
        }
        else {
            System.out.print("\nError - Empty path Data for Builder");
            return null;
        }
    }

    //
    ////
    //

    //2.0 - object return
    public static TestListDataClass mapForJSONObject(String myFilePath) {
        JSONObject myJSONObject = readJSONFromFile(myFilePath);
        if (readJSONFromFile(myFilePath) != null) {
            return objectListFromJSONBuilder(myJSONObject);
        } else {
            System.out.print("\nError - Databuilder (mapForJSONObject) - No File Path");
            return null;
        }
    }

    //
    ////
    //

    // 1.0 - file reader
    public static JSONObject readJSONFromFile(String myFilePath) {
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



    // 1.1 - json mapper
    public static TestListDataClass objectListFromJSONBuilder(JSONObject myJSONOobject){
    JSONObject myTestSuiteFile;
        JSONArray myTestSuiteList;

        myTestSuiteFile = (JSONObject) myJSONOobject.get(DICTIONARY_KEY_01);

        if (myTestSuiteFile != null) {
            //load in testsuites!!!!
            myTestSuiteList = (JSONArray) myTestSuiteFile.get(DICTIONARY_KEY_02);

            //load in dictionary!!!!
            JSONArray myTestSuiteDictionary = (JSONArray) myTestSuiteFile.get(DICTIONARY_KEY_DICTIONARY);
           if (myTestSuiteDictionary.size() > 0) { // Build Dictionary
               JSONObject myObject = (JSONObject) myTestSuiteDictionary.get(0);
               theJSONDictionaryData = newDictionaryBuilder(myObject);
           }
        } else {
            System.out.print("\nError - testSuiteList dictionary error");
            myTestSuiteList = null;
        }

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
        return myTestListData;
    }

    //1.2
    public static  Map<String, String>  newDictionaryBuilder (JSONObject myObject ){
        Collection myCollectionValues = myObject.values();
        Collection myCollectionKeys = myObject.keySet();

        Object[] mValueyArray = myCollectionValues.toArray();
        Object[] myKeyArray = myCollectionKeys.toArray();

        Map<String, String> myDictionary = new HashMap<String, String>();

        for(int i = 0; i< myKeyArray.length; i++) {
            String myKey = (String) myKeyArray[i];
            String myValue = (String) mValueyArray[i];
            myDictionary.put(myKey,myValue);
        }
        return myDictionary;
    }

    //1.3 test iteration
    public static JSONObject singleTestObject (JSONArray myTestSuiteList, int myTestNumber) {
        JSONObject myTestSuite;
        if (myTestSuiteList != null) {
            myTestSuite = (JSONObject) myTestSuiteList.get(myTestNumber);
            return myTestSuite;
        } else {
            System.out.print("\nError - test number error");
            return null;
        }
    }

    //
    ////
    //

    //0.0
    public static String myUSRDirectory (String mySuiteName, String myFileName) {
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
