package Processing;

import Display.MainView;
import Types.Connection;
import Types.Worker;

import java.io.*;
import java.util.HashSet;

public class FileManager {
    public static void load(){
        try{

            Data.connections=new HashSet<>();
            Data.workers=new HashSet<>();

            Worker worker;
            File file = new File("Workers.txt");

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            System.out.println(line);
            while  ((line = bufferedReader.readLine()) != null){

                String[] words = line.split(";");
                worker=new Worker(words[0],words[1],words[2],words[3]);
                Data.workers.add(worker);

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
                Data.connections.add(connection);

            }
            fileReader.close();

            MainView.updateAllWorkers();
            MainView.updateLabels();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(String header, String workersFilname, String connectionsFilename, Boolean isBackup){

        try {

            File file = new File(workersFilname);
            if (file.exists()&&!isBackup)
            {
                //delete if exists
                file.delete();
            }

            PrintWriter out = new PrintWriter(workersFilname);

            out.println(header);
            for(Worker worker : Data.workers)

                out.println(worker.toFile());

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            File file = new File(connectionsFilename);
            if (file.exists()&&!isBackup)
            {
                //delete if exists
                file.delete();
            }
            PrintWriter out = new PrintWriter(connectionsFilename);

            out.println(header);
            for (Connection connection: Data.connections)

                out.println(connection.toFile());

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
