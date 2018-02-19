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
            Main.connections.add(input);


            Main.updateAllWorkers();
            Main.cleanLabels();

            for (Connection connection : Main.connections) {
                Main.labels[connection.shift.row + 1][connection.shift.column].setText(Main.labels[connection.shift.row + 1][connection.shift.column].getText().concat(connection.toString()) + "\n");
            }
        }

        else if(a.getText().equals("Wybierz dzien tygodnia"))
        {
            Integer b=SetupWindow.weekday();
            Main.setWeekday(b);

        }

        else if(a.getText().equals("Zapisz"))
        {
            Main.save("v1");
        }

        else if(a.getText().equals("Wczytaj"))
        {
            Main.load();
        }

    }

}
