package TestReporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mmoscatello on 3/17/2015.
 */
public class Timeclass {

    public float startTime = 0;
    public float endTime = 0;
    public float completionTime = 0;
    public String theDatestring;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public void resetValues() {
        String resetString = "\nTime Class Reset";
        System.out.print(resetString);
        startTime = 0;
        endTime = 0;
        completionTime = 0;
        theDatestring = "";
    }

    public void setDate () {
        Date theDate = new Date();
        theDatestring = dateFormat.format(theDate);
    }

    public void setStartTime () {
        startTime = System.currentTimeMillis(); // Get the start Time
    }

    public void setEndTime () {
        endTime = System.currentTimeMillis(); // Get the start Time
        completionTime = (endTime - startTime)/1000;
    }

    public void printTime () {
        System.out.print("\nStartTime : "+startTime);
        System.out.print("\nEndTime : "+endTime);
        System.out.print("\nCompletionTime : "+completionTime);
        System.out.print("\nThe Date : "+theDatestring);
    }


}
