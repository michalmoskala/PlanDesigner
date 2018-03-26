package Types;

public class Month {
    private int initialWeekday;
    private int numberOfDays;

    public Month(int initialWeekday, int numberOfDays) {
        this.initialWeekday = initialWeekday;
        this.numberOfDays = numberOfDays;
    }

    public Month(String initialWeekday, String numberOfDays) {
        this.initialWeekday = Integer.parseInt(initialWeekday);
        this.numberOfDays = Integer.parseInt(numberOfDays);
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public int getInitialWeekday() {
        return initialWeekday;
    }
}
