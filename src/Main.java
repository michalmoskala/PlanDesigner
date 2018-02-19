import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;


public class Main extends Application{

    private static Label bottomText;
    static myLabel[][] labels;
    static private int numberOfDays = 31;
    private int width=1200;
    private int height=600;
    static private myButton[][]buttons;
    private GridPane gridPane;
    private BorderPane borderPane;
    private Button add,remove,offset,month,save,load;
    static HashSet<Connection> connections=new HashSet<>();
    static myHashSet<Worker> workers=new myHashSet<>();
    static Integer weekday=0;

    static void load(){
        try{
            Worker worker;
            File file = new File("Workers.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line=bufferedReader.readLine();
            System.out.println(line);
            while  ((line = bufferedReader.readLine()) != null){

                String[] words = line.split(";");
                worker=new Worker(words[0],words[1],words[2]);
                workers.add(worker);

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
                connections.add(connection);

            }
            fileReader.close();

            updateAllWorkers();
            cleanLabels();
            for (Connection connection1:connections) {
                labels[connection1.shift.row+1][connection1.shift.column].setText(labels[connection1.shift.row+1][connection1.shift.column].getText().concat(connection1.toString())+"\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void save(String header){

        try {
            PrintWriter out = new PrintWriter("Workers.txt");

            out.println(header);
            for(Worker worker : workers)

                out.println(worker.toFile());

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter out = new PrintWriter("Connections.txt");

            out.println(header);
            for (Connection connection:connections)

                out.println(connection.toFile());

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    static void setWeekday(Integer b){
        weekday=b;
    }

    static void updateWorker(Worker worker){


        workers.removeIf(work -> work.equals(worker));
        workers.add(worker);
        updateAllWorkers();


    }

    static void handleMain(Worker worker){
        workers.remove(worker);
        updateAllWorkers();


        connections.removeIf(con -> con.worker.equals(worker));

        cleanLabels();
        for (Connection connection:connections) {
            labels[connection.shift.row+1][connection.shift.column].setText(labels[connection.shift.row+1][connection.shift.column].getText().concat(connection.toString())+"\n");
        }

    }
    static void updateAllWorkers(){
        for (Worker worker:workers) {
            worker.resetMinutes();
            for (Connection connection:connections) {
                if (connection.worker==worker)
                    worker.addMinutes(connection.minutes);
            }

        }


        ArrayList<Worker> sorted = new ArrayList<>(workers);
        sorted.sort(new WorkerComparator());


        String ret= "";
        for (Worker worker : sorted) {
            ret=ret.concat(worker.toString());
            ret=ret.concat("\n");
        }

        bottomText.setText(ret);

    }

    private void ButtonsSetup(){
        final WorkerHandler workerHandler=new WorkerHandler();
        final SetupHandler setupHandler=new SetupHandler();

        buttons = new myButton[3][numberOfDays];

        for(int j=0;j<3;j++){
            for(int i=0;i<numberOfDays;i++){
                buttons[j][i]=new myButton();
                buttons[j][i].setMinWidth(width / numberOfDays);
                GridPane.setConstraints(buttons[j][i],i,2*j+1);
                buttons[j][i].setOnAction(new SetupHandler());
                buttons[j][i].setText("v");
                buttons[j][i].column=i;
                buttons[j][i].row=j;
            }
        }

        add=new Button("Dodaj");
        remove=new Button("Usun");
        offset = new Button("Ustaw offset");
        month = new Button("Wybierz dzien tygodnia");
        save = new Button("Zapisz");
        load = new Button("Wczytaj");

        add.setOnAction(workerHandler);
        remove.setOnAction(workerHandler);
        offset.setOnAction(workerHandler);
        month.setOnAction(setupHandler);
        save.setOnAction(setupHandler);
        load.setOnAction(setupHandler);

    }

    static void cleanLabels(){
        for (int j = 1; j < 4; j++) {
            for (int i = 0; i < numberOfDays; i++) {

                labels[j][i].setText("");

            }
        }
    }

    static void clearDay(int shift, int day){
        connections.removeIf(con -> con.shift.equals(new Shift(shift-1,day)));
        updateAllWorkers();
        labels[shift][day].setText("");

    }

    private void LabelsSetup(){
        labels = new myLabel[4][numberOfDays];

        for (int i = 0; i < numberOfDays; i++) {
            labels[0][i] = new myLabel(Integer.toString(i + 1),0,i);
            labels[1][i] = new myLabel("",1,i);
            labels[2][i] = new myLabel("",2,i);
            labels[3][i] = new myLabel("",3,i);

        }

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < numberOfDays; i++) {
                GridPane.setConstraints(labels[j][i], i, 2*j);
                labels[j][i].setMinSize(width / numberOfDays, height/5);
                labels[j][i].setAlignment(Pos.CENTER);

            }
        }

        for (int i = 0; i < numberOfDays; i++) {
            labels[0][i].setMinSize(width / numberOfDays, 30);
        }

        for (int j = 1; j < 4; j++) {
            for (int i = 0; i < numberOfDays; i++) {

                labels[j][i].setOnMouseClicked(new LabelHandler());

            }
        }

    }

    private void GridPaneSetup(){
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));

    }

    private void BorderPaneSetup(){
        borderPane=new BorderPane();

        ToolBar toolBar = new ToolBar(add, remove, offset, month,save,load);

        borderPane.setTop(toolBar);
        borderPane.setCenter(gridPane);

        bottomText=new Label();
        borderPane.setBottom(bottomText);

    }

    private void GridPaneAddChildren(){

        for (int i = 0; i < 4; i++) {
            gridPane.getChildren().addAll(labels[i]);
        }
        for(int i=0;i<3;i++) {
            gridPane.getChildren().addAll(buttons[i]);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setOnCloseRequest(e -> {
                    e.consume();
                    if(Alert.display("100%?", "Czy chcesz zamknac aplikacje?"))
                        window.close();
                }
        );


        window.setTitle("Plan Designer");


        GridPaneSetup();
        ButtonsSetup();
        LabelsSetup();

        GridPaneAddChildren();

        gridPane.setGridLinesVisible(true);

        BorderPaneSetup();

        Scene scene1 = new Scene(borderPane, width + 25, height + 25);
        window.setScene(scene1);
        window.show();

    }




}
