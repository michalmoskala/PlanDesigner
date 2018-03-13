package Processing;

import javafx.scene.control.Button;

public class myButton extends Button {

    private int row,column;


    @Override
    public String toString() {
        return Integer.toString(row)+","+Integer.toString(column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
