public class Shift {
    int row;
    int column;


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

    Shift(int row, int column) {
        this.row = row;
        this.column = column;
    }
}


