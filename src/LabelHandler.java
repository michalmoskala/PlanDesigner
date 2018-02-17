
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LabelHandler  implements EventHandler<MouseEvent> {



    @Override
    public void handle(MouseEvent event) {
        myLabel mL= (myLabel) event.getSource();
        Main.clearDay(mL.row,mL.column);
    }
}
