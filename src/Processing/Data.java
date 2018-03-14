package Processing;

import Display.MainView;
import Types.Connection;
import Types.Worker;

import java.util.HashSet;

public class Data {
    public static HashSet<Connection> connections = new HashSet<>();
    public static HashSet<Worker> workers = new HashSet<>();
    static public Boolean timeVisible = false;
    private static Integer weekday=0;

    public static void setWeekday(Integer b){
        weekday=b;
    }

    public static void updateWorker(Worker worker){


        workers.removeIf(work -> work.equals(worker));
        workers.add(worker);
        MainView.updateAllWorkers();


    }

    public static void removeWorker(Worker worker){
        workers.remove(worker);
        MainView.updateAllWorkers();


        connections.removeIf(con -> con.getWorker().equals(worker));

        MainView.updateLabels();

    }
}
