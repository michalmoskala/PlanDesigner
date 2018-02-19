public class Helpers {
    static public String convertTime(int time)
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

    static Worker findWorker(String nick){

        for (Worker worker : Main.workers) {
            if (worker.getNick().equals(nick))
                return worker;

        }
        return null;

    }
}
