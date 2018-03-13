package Processing;

import javafx.scene.control.Label;

public class myLabel extends Label {
    public myLabel(String text, int row, int column) {
        super(text);
        this.row = row;
        this.column = column;

    }
    private int column;
    private int row;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }



}

