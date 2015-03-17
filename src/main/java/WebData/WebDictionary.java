package WebData;

import java.util.HashMap;
import java.util.Map;

public class WebDictionary {

    public static Map<String, String> theDictionary = new HashMap<String, String>();

    public WebDictionary(Map<String, String> myDictionary) {
        theDictionary = myDictionary;
    }

    public static String xpathReturn (String myString) {
        if (theDictionary.size() > 0 && myString.length() > 0) {
            String myValue = "";
            if (theDictionary.containsKey(myString)) {
                myValue = theDictionary.get(myString);
                if (myValue.length() >0) {
                    return myValue;
                }
                else {
                    System.out.print("\nError - Dictionary (xpathReturn) - No string for key : "+ myString);
                    return null;
                }
            }
            else {
                System.out.print("\nError - Dictionary (xpathReturn) - No key : " + myString);
                return null;
            }
        }
        else {
            System.out.print("\nError - Dictionary (xpathReturn) - null search ");
            return null;
        }
    }
}
