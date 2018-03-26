package Processing;

import Display.MainView;
import Types.Connection;
import Types.Month;
import Types.Shift;
import Types.Worker;

import java.util.ArrayList;
import java.util.HashSet;

public class Data {
    public static HashSet<Connection> connections = new HashSet<>();
    public static HashSet<Worker> workers = new HashSet<>();
    static public Boolean timeVisible = false;
    private static Month month;
    private static Boolean[] isSpecial;

    public static void clearDay(int shift, int day){
        Data.connections.removeIf(con -> con.getShift().equals(new Shift(shift-1,day,true)));//todo
        Data.connections.removeIf(con -> con.getShift().equals(new Shift(shift-1,day,false)));
        Data.updateAllWorkers();
    }

    public static String getIsSpecialString(){
        String result="";
        for (int i=0;i<isSpecial.length;i++)
            if (isSpecial[i])
                result=result.concat(i+",");
        return result;
    }

    public static void setSpecial(int index){
        isSpecial[index]=true;
    }

    public static void switchSpecial(int index)
    {
        isSpecial[index]=!isSpecial[index];
        for (Connection connection: connections)
        {
            if (connection.getShift().getColumn()==index)
                connection.switchSpecial();
        }
        updateAllWorkers();
        MainView.setLabelColors();
    }

    public static Boolean getIsSpecial(int index) {
        return isSpecial[index];
    }

    public static void setMonth(Month month) {
        Data.month = month;
    }




    public static void emptySetupSpecial(){
        isSpecial = new Boolean[month.getNumberOfDays()];
        for (int i=0;i<month.getNumberOfDays();i++)
            isSpecial[i] = false;
    }

    public static void setUpSpecial()
    {
        isSpecial = new Boolean[month.getNumberOfDays()];
        for (int i=0;i<month.getNumberOfDays();i++) {
            isSpecial[i] = Helpers.checkIfSpecial(month.getInitialWeekday(), i);

        }
        MainView.setLabelColors();

    }

    public static void updateWorker(Worker worker){

        workers.removeIf(work -> work.equals(worker));
        workers.add(worker);
        updateAllWorkers();


    }

    public static void removeWorker(Worker worker){
        workers.remove(worker);
        updateAllWorkers();


        connections.removeIf(con -> con.getWorker().equals(worker));

        MainView.updateLabels();

    }


    public static Month getMonth() {
        return month;
    }

    public static void updateAllWorkers(){
        for (Worker worker: workers) {
            worker.resetMinutes();
            for (Connection connection: connections) {
                if (connection.getWorker() == worker)
                    worker.addMinutes(connection.getMinutes(),getIsSpecial(connection.getShift().getColumn()));
            }

        }


        ArrayList<Worker> sorted = new ArrayList<>(workers);
        sorted.sort(new WorkerComparator());


        MainView.updateWorkers(sorted);
    }
}
