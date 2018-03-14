package Processing;

import Display.FileView;
import Display.MainView;
import Types.Connection;
import Types.Worker;

import java.io.*;
import java.util.HashSet;

public class FileManager {
    public static void load(File file){
        try{

            Data.connections = new HashSet<>();
            Data.workers = new HashSet<>();

            Worker worker;

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            System.out.println(line);
            while  (true){

                line = bufferedReader.readLine();
                if (line.equals("-"))
                    break;
                String[] words = line.split(";");
                worker=new Worker(words[0],words[1],words[2],words[3]);
                Data.workers.add(worker);

            }

            Connection connection;

            while  ((line = bufferedReader.readLine()) != null){

                String[] words = line.split(";");
                connection=new Connection(words[0],words[1],words[2],words[3]);
                Data.connections.add(connection);

            }
            fileReader.close();

            MainView.updateAllWorkers();
            MainView.updateLabels();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(String header, String workersFilname, Boolean isBackup){

        try {

            File file = FileView.save();

            if (file==null) return;

            if (file.exists()&&!isBackup)
            {
                //delete if exists
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }

            PrintWriter out = new PrintWriter(file);

            out.println(header);
            for(Worker worker : Data.workers)

                out.println(worker.toFile());


            out.println("-");
            for (Connection connection: Data.connections)

                out.println(connection.toFile());

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
