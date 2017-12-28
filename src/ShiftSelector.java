import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class ShiftSelector {
    private static Connection connection;


    static Connection display(myButton mB, HashSet<Worker> workers){

        Stage window = new Stage();
        Label l1=new Label("Kto?");

        ArrayList<String> arrayList=new ArrayList<>();

        for (Worker worker:workers) {

            arrayList.add(worker.getNick());

        }

        ObservableList<String> options =
                FXCollections.observableArrayList(
                      arrayList
                );


        ComboBox<String> textArea=new ComboBox<>(options);



        Label l2=new Label("Ile?");
        TextArea textArea2=new TextArea();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wybierz");
        window.setMinWidth(300);
        window.setWidth(300);
        window.setHeight(300);

        Button closeButton1 = new Button ("OK");
        closeButton1.setOnAction(e-> {
            window.close();
            connection=new Connection(mB.row, mB.column,WorkerHandler.findWorker(textArea.getValue()),Integer.parseInt(textArea2.getText()));
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(l1, textArea,l2,textArea2,closeButton1);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return connection;
    }

}
