package WebAutomation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmoscatello on 3/17/2015.
 */
public class DataPathActions {

    final static String DICTIONARY_KEY_PATHLIST = "pathList";
    final static int NUMBER_OF_OBJECTS_IN_PATH_ARRAY = 2;

    //3.0
    public static PathDataClass[] pathDataReturn (JSONObject myObject) { //out
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
    private static PathDataClass pathDataMapper (JSONArray myPathArray) {
        if (myPathArray.size() == NUMBER_OF_OBJECTS_IN_PATH_ARRAY) {
            String myCommand = (String) myPathArray.get(0);
            String myPath = (String) myPathArray.get(1);
            return pathDataBuilder(myCommand, myPath);
        }
        else {
            String errorMessage = "\nError - DataPathActions (pathDataMapper) - JSONPathArray is null for Map";
            System.out.print(errorMessage);
            return null;
        }
    }

    //3.2
    private static PathDataClass pathDataBuilder (String myCommand, String myPath ) {
        if (myCommand.length() > 0 && myPath.length() > 0) {
            PathDataClass myPathData = new PathDataClass();
            myPathData.command = myCommand;
            myPathData.path = myPath;
            return myPathData;
        }
        else {
            String errorMessage = "\nError - DataPathActions (pathDataBuilder) - Empty path Data for Builder";
            System.out.print(errorMessage);
            return null;
        }
    }




}
