
public class Connection {
    Shift shift;
    Worker worker;
    int minutes;



    @Override
    public String toString(){
        return worker.getNick() + "    " + Helpers.convertTime(minutes);
    }

    public String toFile(){
        return shift.row+";"+shift.column+";"+worker.getNick()+";"+minutes;
    }

    Connection(int row, int column, Worker worker, int minutes){
        this.shift=new Shift(row, column);
        this.worker=worker;
        this.minutes=minutes;
    }

    Connection(String row, String column, String worker, String minutes){
        this.shift=new Shift(Integer.parseInt(row),Integer.parseInt(column));
        this.worker=Helpers.findWorker(worker);
        this.minutes=Integer.parseInt(minutes);

    }


}
