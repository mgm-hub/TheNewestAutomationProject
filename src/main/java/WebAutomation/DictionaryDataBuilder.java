package WebAutomation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmoscatello on 3/17/2015.
 */
public class DictionaryDataBuilder {

    final static String DICTIONARY_KEY_01 = "testDictionary";
    final static String DICTIONARY_KEY_DICTIONARY = "pathDictionary";

    public static String theUSRDirectory;
    public static String theSuiteGroupName;

    public static Map <String, String> theJSONDictionaryData;

    public DictionaryDataBuilder(String mySuiteGroupName) {
        theSuiteGroupName = mySuiteGroupName;
        theUSRDirectory = myUSRDirectory(mySuiteGroupName);
        mapForJSONObject(theUSRDirectory);

    }

    //1.0 - main object builder
    private static void mapForJSONObject(String myFilePath) {
        //1.1
        JSONObject myJSONObject = readJSONFromFile(myFilePath);
        if (readJSONFromFile(myFilePath) != null) {
            //1.2
            objectListFromJSONBuilder(myJSONObject);
        } else {
            String errorMessage = "\nError - DictionaryDataBuilder (mapForJSONObject) - No File Path";
            System.out.print(errorMessage);
        }
    }

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

            //load in dictionary!!!!
            JSONArray myTestSuiteDictionary = (JSONArray) myTestSuiteFile.get(DICTIONARY_KEY_DICTIONARY);
            if (myTestSuiteDictionary.size() > 0) { // Build Dictionary
                JSONObject myObject = (JSONObject) myTestSuiteDictionary.get(0);
                newDictionarySetter(myObject); //sets dictionary here
            }
            else {
                String errorMessage = "\nError - Databuilder (objectListFromJSONBuilder) - dictionary empty";
                System.out.print(errorMessage);
            }
        } else {
            String errorMessage = "\nError - Databuilder (objectListFromJSONBuilder) - testSuiteList dictionary error";
            System.out.print(errorMessage);
        }
    }

    //1.4 dictionary writer
    private static  void  newDictionarySetter (JSONObject myObject ){
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
        if (myDictionary.size() >0){
            theJSONDictionaryData = myDictionary;
        }
        else {
            String errorMessage = "\nError - Databuilder (newDictionaryBuilder) - dictionary size error";
            System.out.print(errorMessage);
        }
    }


    //0.0
    private static String myUSRDirectory (String mySuiteName) {
        String USR_DIRECTORY = System.getProperty("user.dir");
        String OS_NAME = System.getProperty("os.name");
        String myPathAdd;
        if (OS_NAME.equals("Mac OS X")){
            myPathAdd = "/src/main/java/WebData/"+mySuiteName+"/"+mySuiteName+".json";
        }
        else{
            myPathAdd = "\\src\\main\\java\\WebData\\"+mySuiteName+"\\"+mySuiteName+"Dictionary.json";
        }
        String fullPath = USR_DIRECTORY + myPathAdd;
        return fullPath;
    }

}
