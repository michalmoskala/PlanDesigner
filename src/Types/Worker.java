package Types;

import Processing.Helpers;

public class Worker {
    private String nick;
    private String fullName;
    private int minutes;
    private int offset;
    private int vacation;
    private int specialMinutes;


    public void addMinutes(int add,boolean isSpecial){
        this.minutes+=add;
        if (isSpecial)
            this.specialMinutes+=add;
    }


    public void resetMinutes() {
        this.minutes = 0;
        this.specialMinutes=0;
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

        return String.format("%s     %s   (Przepracowane: %s, Weekendy: %s, Offset: %s, Urlop: %s dni | %s) %s",
                nick,
                Helpers.convertTimeToHours(time),
                Helpers.convertTimeToHours(minutes),
                Helpers.convertTimeToHours(specialMinutes),
                Helpers.convertTimeToHours(offset),
                Helpers.convertTimeToDays(vacation),
                Helpers.convertTimeToHours(vacation),
                fullName
        );
    }

    public String getNick(){

        return nick;

    }


}
