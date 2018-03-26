package Types;

import Processing.Data;
import Processing.Helpers;

public class Connection {


    private Shift shift;
    private Worker worker;
    private int minutes;

    public void switchSpecial(){
        shift.switchSpecial();
    }

    public Worker getWorker() {
        return worker;
    }

    public int getMinutes() {
        return minutes;
    }

    public Shift getShift() {
        return shift;
    }

    @Override
    public String toString(){
        if (Data.timeVisible)
            return worker.getNick() + ":  (" + Helpers.convertTimeToHours(minutes)+"}";
        else
            return worker.getNick();
    }

    public String toFile(){
        return shift.row+";"+shift.column+";"+worker.getNick()+";"+minutes;
    }

    public Connection(int row, int column, Worker worker, int minutes, boolean holiday){
        this.shift=new Shift(row, column,holiday);
        this.worker=worker;
        this.minutes=minutes;
    }

    public Connection(String row, String column, String worker, String minutes){
        this.shift=new Shift(Integer.parseInt(row),Integer.parseInt(column),Helpers.checkIfSpecial(Data.getMonth().getInitialWeekday(),Integer.parseInt(column)));
        this.worker= Helpers.findWorker(worker);
        this.minutes=Integer.parseInt(minutes);

    }


}
