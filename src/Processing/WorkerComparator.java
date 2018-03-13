package Processing;

import Types.Worker;

import java.util.Comparator;

public class WorkerComparator implements Comparator<Worker>{
    @Override
    public int compare(Worker o1, Worker o2) {

        if (Helpers.isNumeric(o1.getNick())&&Helpers.isNumeric(o2.getNick()))
            return Integer.compare(Integer.parseInt(o1.getNick()), Integer.parseInt(o2.getNick()));
        else
            return o1.getNick().compareTo(o2.getNick());

    }
}
