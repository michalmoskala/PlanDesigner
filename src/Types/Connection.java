package Types;

import Processing.Data;
import Processing.Helpers;

public class Connection {


    private Shift shift;
    private Worker worker;
    private int minutes;

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
        if (Data.isSetup)
            return worker.getNick() + ":  (" + Helpers.convertTime(minutes)+"}";
        else
            return worker.getNick();
    }

    public String toFile(){
        return shift.row+";"+shift.column+";"+worker.getNick()+";"+minutes;
    }

    public Connection(int row, int column, Worker worker, int minutes){
        this.shift=new Shift(row, column);
        this.worker=worker;
        this.minutes=minutes;
    }

    public Connection(String row, String column, String worker, String minutes){
        this.shift=new Shift(Integer.parseInt(row),Integer.parseInt(column));
        this.worker= Helpers.findWorker(worker);
        this.minutes=Integer.parseInt(minutes);

    }


}
