import javafx.scene.control.Button;

public class myButton extends Button {

    int row,column;


    @Override
    public String toString() {
        return Integer.toString(row)+","+Integer.toString(column);
    }
}
