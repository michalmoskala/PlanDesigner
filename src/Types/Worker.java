package Types;

import Processing.Helpers;

public class Worker {
    private String nick;
    private String fullName;
    private int minutes;
    private int offset;
    private int vacation;


    public void addMinutes(int add){
        this.minutes+=add;
    }

    public void resetMinutes() {
        this.minutes = 0;
    }

    public String toFile(){
        return nick+";"+fullName+";"+offset+";"+vacation;
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

    public void setVacation(int vacation) {
        this.vacation = vacation;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Worker(String fullName, String nick) {
        this.fullName = fullName;
        this.nick = nick;
    }

    public Worker(String nick, String fullName, String offset, String vacation)
    {
        this.nick=nick;
        this.fullName=fullName;
        this.offset=Integer.parseInt(offset);
        this.vacation=Integer.parseInt(vacation);
    }


    @Override
    public String toString() {

        int time=offset+minutes+vacation;
        return nick + "     " + Helpers.convertTime(time)+"   (Offset: " + Helpers.convertTime(offset) + ", Urlop: " + Helpers.convertTime(vacation) + ") ";

    }

    public String getNick(){

        return nick;

    }


}