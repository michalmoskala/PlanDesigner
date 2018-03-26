package Handlers;

import Processing.Data;
import Types.Worker;
import Display.WorkerSelector;
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
                if (newWorker!=null) {
                    Data.workers.add(newWorker);
                    Data.updateAllWorkers();
                }
            }

            else if (a.getText().equals("Usun"))
            {
                Worker removeWorker = WorkerSelector.removeWorker(Data.workers);

                if(removeWorker!=null)
                    Data.removeWorker(removeWorker);


            }

            else if (a.getText().equals("Ustaw offset"))
            {
                Worker updatedWorker = WorkerSelector.updateOffset(Data.workers);
                if(updatedWorker!=null)
                    Data.updateWorker(updatedWorker);
            }

            else if (a.getText().equals("Ustaw urlop"))
            {
                Worker updatedWorker = WorkerSelector.updateVacation(Data.workers);
                if(updatedWorker!=null)
                    Data.updateWorker(updatedWorker);
            }



    }

}
