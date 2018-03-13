package Display;

import Processing.Helpers;
import Types.Worker;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashSet;

public class WorkerSelector {
    private static Worker worker;

    public static Worker updateVacation(HashSet<Worker> workers){
        Stage window = new Stage();
        Label l1=new Label("Kto?");
        Label l2=new Label("Ile dni?");

        ComboBox<String> comboWorkers= ViewHelpers.prepareComboBoxString(workers,l1.getWidth());
        ComboBox<Integer> comboDays= ViewHelpers.prepareComboBoxInteger(25,1,l1.getWidth());


        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wybierz");
        window.setMinWidth(300);
        window.setWidth(300);
        window.setHeight(300);

        Button closeButton1 = new Button ("OK");
        closeButton1.setOnAction(e-> {
            window.close();
            worker= Helpers.findWorker(comboWorkers.getValue());
            int vacation = comboDays.getValue()*455;
            worker.setVacation(vacation);
        });


        GridPane layout = new GridPane();
        closeButton1.setMinWidth(200);


        layout.add(l1,0,0);
        layout.add(comboWorkers,1,0);
        layout.add(l2,0,1);
        layout.add(comboDays,1,1);
        layout.add(closeButton1,0,2,2,2);

        l1.setAlignment(Pos.CENTER);
        layout= ViewHelpers.setupLayout(layout);

        window.setOnCloseRequest(e -> worker = null);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return worker;

    }

    public static Worker updateOffset(HashSet<Worker> workers){
        Stage window = new Stage();
        Label l1=new Label("Kto?");
        Label l2=new Label("h");
        Label l3=new Label("min");
        Label l4=new Label("Ujemne?");
        RadioButton radioButton=new RadioButton();

        window.setOnCloseRequest(e -> worker = null);

        ComboBox<String> comboWorkers = ViewHelpers.prepareComboBoxString(workers,l1.getWidth());
        ComboBox<Integer> comboHours = ViewHelpers.prepareComboBoxInteger(12,1,l1.getWidth());
        ComboBox<Integer> comboMinutes = ViewHelpers.prepareComboBoxInteger(60,5,l1.getWidth());

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wybierz");
        window.setMinWidth(300);
        window.setWidth(300);
        window.setHeight(300);

        Button closeButton1 = new Button ("OK");
        closeButton1.setOnAction(e-> {
            window.close();
            worker= Helpers.findWorker(comboWorkers.getValue());
            int offset = comboHours.getValue()*60+comboMinutes.getValue();
            if (radioButton.isSelected())
                offset=-offset;
            worker.setOffset(offset);
        });

        GridPane layout = new GridPane();
        closeButton1.setMinWidth(200);

        layout.add(l1,0,0);
        layout.add(comboWorkers,1,0);
        layout.add(l2,0,1);
        layout.add(comboHours,1,1);
        layout.add(l3,0,2);
        layout.add(comboMinutes,1,2);
        layout.add(l4,0,3);
        layout.add(radioButton,1,3);
        layout.add(closeButton1,0,4,2,2);

        l1.setAlignment(Pos.CENTER);

        layout = ViewHelpers.setupLayout(layout);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return worker;

    }

    public static Worker removeWorker(HashSet<Worker> workers){

        Stage window = new Stage();
        Label l1=new Label("Kto?");
        Button closeButton1 = new Button ("OK");
        ComboBox<String> comboWorkers=ViewHelpers.prepareComboBoxString(workers,0);

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
        window.setOnCloseRequest(e -> worker = null);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
        return worker;
    }

    public static Worker addWorker(){

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

        window.setOnCloseRequest(e -> worker = null);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return worker;
    }

}
