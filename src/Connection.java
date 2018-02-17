
public class Connection {
    Shift shift;
    Worker worker;
    int minutes;



    @Override
    public String toString(){
        return worker.getNick() + " " + Integer.toString(minutes/60) + ":" + (minutes-((minutes/60)*60));
    }

    Connection(int row, int column, Worker worker, int minutes){
        this.shift=new Shift(row, column);
        this.worker=worker;
        this.minutes=minutes;
    }


}
