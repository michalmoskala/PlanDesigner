public class Worker {
    private String nick;
    private String fullName;
    private int minutes;
    private int offset;


    void addMinutes(int add){
        this.minutes+=add;
    }

    void resetMinutes() {
        this.minutes = 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        return minutes == worker.minutes && (nick != null ? nick.equals(worker.nick) : worker.nick == null) && (fullName != null ? fullName.equals(worker.fullName) : worker.fullName == null);
    }

    @Override
    public int hashCode() {
        int result = nick != null ? nick.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + minutes;
        return result;
    }

    void setOffset(int offset) {
        this.offset = offset;
    }

    Worker(String fullName, String nick) {
        this.fullName = fullName;
        this.nick = nick;
        this.offset = 0;
    }


    @Override
    public String toString() {

        int time=offset+minutes;
        return "(" + convertTime(offset) +") " + nick + "     " + convertTime(time);

    }

    String getNick(){

        return nick;

    }

    private String convertTime(int time)
    {
        String ret= "";
        if (time<0)
        {
            ret = "-";
            time=-time;
        }
        ret=ret.concat(time/60 + ":" + Integer.toString(time-(60*(time/60))));

        return ret;

    }


}
