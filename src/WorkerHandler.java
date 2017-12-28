import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class WorkerHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

            Button a= (Button) event.getSource();


            if(a.getText().equals("Dodaj"));
            {
                Worker newWorker = WorkerSelector.addWorker();
                Main.workers.add(newWorker);
                Main.updateString();
            }




    }

    static public Worker findWorker(String nick){

        for (Worker worker : Main.workers) {
            if (worker.nick.equals(nick))
                return worker;

        }
        return null;

    }
}
