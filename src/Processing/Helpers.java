package Processing;

import Types.Month;
import Types.Worker;
import java.util.ArrayList;

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


    public static String convertTimeToHours(int time)
    {
        String ret= "";
        if (time<0)
        {
            ret = "-";
            time=-time;
        }
        ret=ret.concat(String.format("%d:%02d",time/60,(time-(60*(time/60)))));

        return ret;

    }

    public static String convertTimeToDays(int time)
    {
        time /= 455;
        return Integer.toString(time);

    }

    public static Worker findWorker(String nick){

        for (Worker worker : Data.workers) {
            if (worker.getNick().equals(nick))
                return worker;

        }
        return null;

    }


    public static Boolean checkIfSpecial (int initDay,int currentDay)
    {
        int weekDay = (currentDay%7+initDay)%7;
        return weekDay == 5 || weekDay == 6;
    }

}
