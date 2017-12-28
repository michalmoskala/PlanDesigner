import java.util.HashSet;

public class myHashSet<Worker> extends HashSet<Worker>{
    @Override
    public String toString() {

        String ret= "";
        for (Object worker : this) {
            ret=ret.concat(worker.toString());
            ret=ret.concat("\n");
        }
        return ret;
    }
}
