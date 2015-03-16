package WebData;

import java.util.HashMap;
import java.util.Map;


public class WebDictionary {

    public static Map<String, String> theDictionary = new HashMap<String, String>();

    public static void loadDictionary () {

        theDictionary = mainDictionary();


    }

    public static Map <String, String> mainDictionary () {
        Map<String, String> dictionary = new HashMap<String, String>();
        dictionary.put("home_login_link", "//a[contains(.,'Login')]");
        dictionary.put("home_signup", "//a[contains(.,'Sign Up')]");
        dictionary.put("home_contact", "//a[contains(.,'Contact')]");
        dictionary.put("home_help", "//a[contains(@href,'http://liftopia.zendesk.com/forums')]");
        dictionary.put("home_chat", "//a[contains(.,'Chat')]");
        dictionary.put("home_callus", "//a[contains(.,'Call us! (800) 349-0870')]");
        dictionary.put("home_company", "//a[contains(.,'Company')]");
        dictionary.put("home_platform", "//a[contains(.,'Platform')]");
        dictionary.put("home_team", "//a[contains(.,'Team')]");
        dictionary.put("home_career", "//a[contains(.,'Careers')]");
        dictionary.put("home_press", "//a[contains(.,'Press')]");
        dictionary.put("home_blog", "//a[contains(.,'Blog')]");
        dictionary.put("main_login_user_email", "//input[@id='SignInUserName']");
        dictionary.put("main_login_user_password", "//input[@id='SignInPasswordaaaaaa']");
        dictionary.put("main_login_signin_button", "//input[@value='Sign In']");
        dictionary.put("main_login_alert", "//div[contains(@class,'alert-error')]");
        dictionary.put("cloud_login_link", "//i[@class='fa fa-user']");
        dictionary.put("cloud_user_name", "//input[@id='SignInUserName']");
        dictionary.put("cloud_login_button", "//input[@value='Login']");
        dictionary.put("", "");
        dictionary.put("", "");
        dictionary.put("", "");
        dictionary.put("", "");
        dictionary.put("", "");
        dictionary.put("", "");
        dictionary.put("", "");
        dictionary.put("", "");


        dictionary.put("empty", "");
        return dictionary;
    }

    public static String xpathReturn (String myString) {
        //lazy init
        if (theDictionary.size() <= 0){//first call to this method
            System.out.print("\nDictionary Loaded!");
            loadDictionary();
        }
        else {
            //empty already initialized
        }

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
