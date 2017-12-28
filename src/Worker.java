public class Worker {
    String nick;
    String fullName;
    int minutes;

    public Worker(String fullName,String nick) {
        this.fullName = fullName;
        this.nick = nick;
    }


    @Override
    public String toString() {

        return nick + " " + minutes;

    }
}
