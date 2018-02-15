import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

class WorkerSelector {
    private static Worker worker;

    static Worker removeWorker(myHashSet<Worker> workers){



        Stage window = new Stage();
        Label l1=new Label("Kto?");

        ArrayList<String> arrayListWorkers=new ArrayList<>();

        for (Worker worker:workers) {
            arrayListWorkers.add(worker.getNick());
        }

        ObservableList<String> optionsWorkers = FXCollections.observableArrayList(arrayListWorkers);
        ComboBox<String> comboWorkers=new ComboBox<>(optionsWorkers);

        Button closeButton1 = new Button ("OK");

        closeButton1.setOnAction(e-> {
            window.close();
            for (Worker worker1:workers)
                if (worker1.getNick().equals(comboWorkers.getValue()))
                    worker=worker1;
        });

        VBox vBox=new VBox(10);
        vBox.getChildren().addAll(l1,comboWorkers,closeButton1);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Usun");
        window.setWidth(200);
        window.setHeight(200);

        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
        return worker;
    }

    static Worker addWorker(){

        Stage window = new Stage();
        TextArea textArea=new TextArea();
        TextArea textArea2=new TextArea();

        textArea.setMaxWidth(200);
        textArea.setMaxHeight(1);

        textArea2.setMinWidth(40);
        textArea2.setMaxHeight(1);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Dodaj");
        window.setWidth(700);
        window.setHeight(200);

        Label label1 = new Label("Imię i Nazwisko");
        Label label2 = new Label("Nickname");
        Button closeButton1 = new Button ("OK");
        closeButton1.setOnAction(e-> {
            window.close();
            worker = new Worker(textArea.getText(),textArea2.getText());
        });


        label1.setMinSize((double)label1.getText().length(),1);


        GridPane layout = new GridPane();

        layout.setHgap(15);
        layout.setVgap(15);

        GridPane.setConstraints(label1,0,0);
        GridPane.setConstraints(label2,0,1);

        GridPane.setConstraints(textArea,1,0);
        GridPane.setConstraints(textArea2,1,1);

        GridPane.setConstraints(closeButton1,1,2);

        layout.getChildren().addAll(label1, textArea,label2, textArea2,closeButton1);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return worker;
    }

}
