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

    public String toFile(){
        return nick+";"+fullName+";"+offset;
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

    Worker(String nick,String fullName,String offset)
    {
        this.nick=nick;
        this.fullName=fullName;
        this.offset=Integer.parseInt(offset);
    }


    @Override
    public String toString() {

        int time=offset+minutes;
        return "(" + Helpers.convertTime(offset) +") " + nick + "     " + Helpers.convertTime(time);

    }

    String getNick(){

        return nick;

    }




}
