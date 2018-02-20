import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class WorkerHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

            Button a= (Button) event.getSource();

            if(a.getText().equals("Dodaj"))
            {
                Worker newWorker = WorkerSelector.addWorker();
                Main.workers.add(newWorker);
                Main.updateAllWorkers();
            }

            else if (a.getText().equals("Usun"))
            {
                Worker removeWorker = WorkerSelector.removeWorker(Main.workers);

                Main.handleMain(removeWorker);


            }

            else if (a.getText().equals("Ustaw offset"))
            {
                Worker updatedWorker = WorkerSelector.updateOffset(Main.workers);
                Main.updateWorker(updatedWorker);
            }

            else if (a.getText().equals("Ustaw urlop"))
            {
                Worker updatedWorker = WorkerSelector.updateVacation(Main.workers);
                Main.updateWorker(updatedWorker);
            }



    }

}
