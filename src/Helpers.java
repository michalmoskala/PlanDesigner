import java.io.*;
import java.util.HashSet;

public class Helpers {


    static public String convertTime(int time)
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

    static Worker findWorker(String nick){

        for (Worker worker : Main.workers) {
            if (worker.getNick().equals(nick))
                return worker;

        }
        return null;

    }

    static void load(){
        try{

            Main.connections=new HashSet<>();
            Main.workers=new myHashSet<>();

            Worker worker;
            File file = new File("Workers.txt");

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            System.out.println(line);
            while  ((line = bufferedReader.readLine()) != null){

                String[] words = line.split(";");
                worker=new Worker(words[0],words[1],words[2],words[3]);
                Main.workers.add(worker);

            }
                fileReader.close();

            Connection connection;
            file = new File("Connections.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            line=bufferedReader.readLine();
            System.out.println(line);
            while  ((line = bufferedReader.readLine()) != null){

                String[] words = line.split(";");
                connection=new Connection(words[0],words[1],words[2],words[3]);
                Main.connections.add(connection);

            }
            fileReader.close();

            Main.updateAllWorkers();
            Main.cleanLabels();
            for (Connection connection1: Main.connections) {
                Main.labels[connection1.shift.row+1][connection1.shift.column].setText(Main.labels[connection1.shift.row+1][connection1.shift.column].getText().concat(connection1.toString())+"\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void save(String header){

        try {

            File file = new File("Workers.txt");
            if (file.exists())
            {
                //delete if exists
                file.delete();
            }

            PrintWriter out = new PrintWriter("Workers.txt");

            out.println(header);
            for(Worker worker : Main.workers)

                out.println(worker.toFile());

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            File file = new File("Connections.txt");
            if (file.exists())
            {
                //delete if exists
                file.delete();
            }
            PrintWriter out = new PrintWriter("Connections.txt");

            out.println(header);
            for (Connection connection: Main.connections)

                out.println(connection.toFile());

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }




}
