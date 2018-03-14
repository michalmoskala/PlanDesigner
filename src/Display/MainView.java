package Display;

import Processing.*;
import Types.Connection;
import Types.Shift;
import Types.Worker;
import Handlers.ConnectionHandler;
import Handlers.SetupHandler;
import Handlers.WorkerHandler;
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

import java.util.*;


public class MainView extends Application{

    private static Label bottomText;
    private static myLabel[][] labels;
    private static myButton[][]buttons;
    private static int numberOfDays = 31;
    private int width=1200;
    private int height=600;
    private GridPane gridPane;
    private BorderPane borderPane;
    private Button add,remove,offset,vacation,month,save,load,change;

    public static void updateAllWorkers(){
        for (Worker worker: Data.workers) {
            worker.resetMinutes();
            for (Connection connection: Data.connections) {
                if (connection.getWorker() == worker)
                    worker.addMinutes(connection.getMinutes());
            }

        }


        ArrayList<Worker> sorted = new ArrayList<>(Data.workers);
        sorted.sort(new WorkerComparator());


        bottomText.setText(Helpers.getWorkersString(sorted));

    }

    public static void updateLabels(){
        MainView.cleanLabels();
        for (Connection connection: Data.connections) {
            MainView.labels[connection.getShift().getRow() +1][connection.getShift().getColumn()].setText(MainView.labels[connection.getShift().getRow() +1][connection.getShift().getColumn()].getText().concat(connection.toString())+"\n");
        }
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
                buttons[j][i].setColumn(i);
                buttons[j][i].setRow(j);
            }
        }

        add=new Button("Dodaj");
        remove=new Button("Usun");
        offset = new Button("Ustaw offset");
        vacation = new Button("Ustaw urlop");
        month = new Button("Wybierz dzien tygodnia");
        save = new Button("Zapisz");
        load = new Button("Wczytaj");
        change = new Button("Zmien");

        add.setOnAction(workerHandler);
        remove.setOnAction(workerHandler);
        offset.setOnAction(workerHandler);
        vacation.setOnAction(workerHandler);
        month.setOnAction(setupHandler);
        save.setOnAction(setupHandler);
        load.setOnAction(setupHandler);
        change.setOnAction(setupHandler);
    }

    private static void cleanLabels(){
        for (int j = 1; j < 4; j++) {
            for (int i = 0; i < numberOfDays; i++) {

                labels[j][i].setText("");

            }
        }
    }

    public static void clearDay(int shift, int day){
        Data.connections.removeIf(con -> con.getShift().equals(new Shift(shift-1,day)));
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

                labels[j][i].setOnMouseClicked(new ConnectionHandler());

            }
        }

    }

    private void GridPaneSetup(){
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));

    }

    private void BorderPaneSetup(){
        borderPane=new BorderPane();

        ToolBar toolBar = new ToolBar(add, remove, offset,vacation, month,save,load,change);

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
                    if(ExitWindow.display("100%?", "Czy chcesz zamknac aplikacje?"))
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
