package Display;

import Processing.Helpers;
import Types.Connection;
import Types.Worker;
import Processing.myButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashSet;


public class ShiftSelector {
    private static Connection connection;


    public static Connection display(myButton mB, HashSet<Worker> workers){

        Stage window = new Stage();
        Label l1=new Label("Kto?");
        Label l2=new Label("h");
        Label l3=new Label("min");

        ComboBox<String> comboWorkers = ViewHelpers.prepareComboBoxString(workers,l1.getWidth());
        ComboBox<Integer> comboHours = ViewHelpers.prepareComboBoxInteger(12,1,l1.getWidth());
        ComboBox<Integer> comboMinutes = ViewHelpers.prepareComboBoxInteger(60,5,l1.getWidth());

        comboHours.getSelectionModel().select(12);
        comboMinutes.getSelectionModel().select(0);


        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wybierz");
        window.setMinWidth(300);
        window.setWidth(300);
        window.setHeight(300);

        Button closeButton1 = new Button ("OK");
        closeButton1.setOnAction(e-> {
            window.close();
            connection=new Connection(mB.getRow(), mB.getColumn(), Helpers.findWorker(comboWorkers.getValue()),comboHours.getValue()*60+comboMinutes.getValue());
        });


        GridPane layout = new GridPane();
        closeButton1.setMinWidth(200);


        layout.add(l1,0,0);
        layout.add(comboWorkers,1,0);
        layout.add(l2,0,1);
        layout.add(comboHours,1,1);
        layout.add(l3,0,2);
        layout.add(comboMinutes,1,2);
        layout.add(closeButton1,0,3,2,2);

        l1.setAlignment(Pos.CENTER);

        layout= ViewHelpers.setupLayout(layout);

        window.setOnCloseRequest(e -> connection = null);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return connection;
    }

}
