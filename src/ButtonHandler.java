import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        myButton mB= (myButton) event.getSource();


        Connection input=ShiftSelector.display(mB,Main.workers);
        Main.connections.add(input);


        Main.updateAllWorkers();
        Main.cleanLabels();

        for (Connection connection:Main.connections) {
            Main.labels[connection.shift.row+1][connection.shift.column].setText(Main.labels[connection.shift.row+1][connection.shift.column].getText().concat(connection.toString())+"\n");
        }

    }

}
