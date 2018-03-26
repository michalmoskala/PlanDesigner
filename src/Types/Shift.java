package Types;

public class Shift {
    int row;
    int column;
    private boolean special;

    public void switchSpecial(){
        special=!special;

    }
    public Shift(int row, int column, boolean special) {
        this.row = row;
        this.column = column;
        this.special = special;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shift shift = (Shift) o;

        return row == shift.row && column == shift.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }


}


