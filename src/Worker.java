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

        return nick + " " + minutes/60 + ":" + Integer.toString(minutes-(60*(minutes/60)));

    }

    public String getNick(){

        return nick;

    }
}
