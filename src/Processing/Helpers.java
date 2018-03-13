package Processing;

import Types.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashSet;

public class Helpers {

    private static final String version = "v4";

    public static String getVersion(){return version;}

    public static String getWorkersString(ArrayList<Worker> Workers) {

        String ret= "";
        for (Object worker : Workers) {
            ret=ret.concat(worker.toString());
            ret=ret.concat("\n");
        }
        return ret;
    }


    static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }


    public static String convertTime(int time)
    {
        String ret= "";
        if (time<0)
        {
            ret = "-";
            time=-time;
        }
        ret=ret.concat(time/60 + ":" + Integer.toString(time-(60*(time/60))));

        return ret;

    }

    public static Worker findWorker(String nick){

        for (Worker worker : Data.workers) {
            if (worker.getNick().equals(nick))
                return worker;

        }
        return null;

    }


}
