import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class SetupHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

        Button a= (Button) event.getSource();

        if(a.getText().equals("v"))
        {
            myButton mB = (myButton) event.getSource();


            Connection input = ShiftSelector.display(mB, Main.workers);

            if(input!=null) {
                Main.connections.add(input);


                Main.updateAllWorkers();
                Main.cleanLabels();

                if (!Main.connections.isEmpty())
                    for (Connection connection : Main.connections) {
                        Main.labels[connection.shift.row + 1][connection.shift.column].setText(Main.labels[connection.shift.row + 1][connection.shift.column].getText().concat(connection.toString()) + "\n");
                    }
            }

            Helpers.save("--------","bworkers.txt","bcons.txt",true);
        }

        else if(a.getText().equals("Wybierz dzien tygodnia"))
        {
            Integer b=SetupWindow.weekday();
            Main.setWeekday(b);

        }

        else if(a.getText().equals("Zapisz"))
        {
            Helpers.save("v3","Workers.txt","Connections.txt",false);
        }

        else if(a.getText().equals("Wczytaj"))
        {
            Helpers.load();
        }

    }

}
