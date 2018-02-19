import javafx.scene.control.Label;

class myLabel extends Label {
    myLabel(String text, int row, int column) {
        super(text);
        this.row = row;
        this.column = column;

    }

    int row,column;


}

