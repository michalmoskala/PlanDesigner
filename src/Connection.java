import java.util.HashSet;

public class Connection {
    Shift shift;
    public Worker worker;
    int minutes;

    Connection(Shift shift, Worker worker, int minutes){
        this.shift=shift;
        this.worker=worker;
        this.minutes=minutes;
    }


    @Override
    public String toString(){
        return worker.nick + " " + Integer.toString(minutes/60) + ":" + (minutes-((minutes/60)*60));
    }

    Connection(int row, int column, Worker worker, int minutes){
        this.shift=new Shift(row, column);
        this.worker=worker;
        this.minutes=minutes;
    }

    static HashSet<Connection> addConnection(HashSet<Connection> hashSet, Shift shift, Worker worker, int minutes){
        Connection connection=new Connection(shift,worker,minutes);
        hashSet.add(connection);
        return hashSet;
    }
}
