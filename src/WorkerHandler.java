import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class WorkerHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

            Worker newWorker=WorkerSelector.addWorker();
            Main.workers.add(newWorker);
            Main.updateString();


    }

    static public Worker findWorker(String nick){

        for (Worker worker : Main.workers) {
            if (worker.nick.equals(nick))
                return worker;

        }
        return null;

    }
}
