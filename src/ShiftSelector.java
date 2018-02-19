import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;

class ShiftSelector {
    private static Connection connection;


    static Connection display(myButton mB, HashSet<Worker> workers){

        Stage window = new Stage();
        Label l1=new Label("Kto?");

        ArrayList<String> arrayListWorkers=new ArrayList<>();

        for (Worker worker:workers) {
            arrayListWorkers.add(worker.getNick());
        }

        ObservableList<String> optionsWorkers = FXCollections.observableArrayList(arrayListWorkers);
        ComboBox<String> comboWorkers=new ComboBox<>(optionsWorkers);


        ArrayList<Integer> arrayListHours = new ArrayList<>();

        for (int i=0;i<=12;i++){
            arrayListHours.add(i);
        }

        ObservableList<Integer> optionsHours = FXCollections.observableArrayList(arrayListHours);
        ComboBox<Integer> comboHours=new ComboBox<>(optionsHours);


        ArrayList<Integer> arrayListMinutes = new ArrayList<>();

        for (int i=0;i<60;i+=5){
            arrayListMinutes.add(i);
        }

        ObservableList<Integer> optionsMinutes = FXCollections.observableArrayList(arrayListMinutes);
        ComboBox<Integer> comboMinutes=new ComboBox<>(optionsMinutes);


        Label l2=new Label("h");
        Label l3=new Label("min");

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wybierz");
        window.setMinWidth(300);
        window.setWidth(300);
        window.setHeight(300);

        Button closeButton1 = new Button ("OK");
        closeButton1.setOnAction(e-> {
            window.close();
            connection=new Connection(mB.row, mB.column, Helpers.findWorker(comboWorkers.getValue()),comboHours.getValue()*60+comboMinutes.getValue());
        });


        GridPane layout = new GridPane();
        closeButton1.setMinWidth(200);
        comboHours.setMinWidth(200-l1.getWidth()-40);
        comboMinutes.setMinWidth(200-l1.getWidth()-40);
        comboWorkers.setMinWidth(200-l1.getWidth()-40);

        layout.add(l1,0,0);
        layout.add(comboWorkers,1,0);
        layout.add(l2,0,1);
        layout.add(comboHours,1,1);
        layout.add(l3,0,2);
        layout.add(comboMinutes,1,2);
        layout.add(closeButton1,0,3,2,2);

        l1.setAlignment(Pos.CENTER);

        layout.setVgap(10);
        layout.setHgap(10);

        layout.setAlignment(Pos.CENTER);

        layout.autosize();

        window.setOnCloseRequest(e -> connection = null);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return connection;
    }

}
