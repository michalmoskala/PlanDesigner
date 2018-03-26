package Display;

import Types.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InitialSetupWindow {
    static private Month month;


    public static Month display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Dzien tygodnia");
        window.setMinWidth(300);
        window.setWidth(300);
        window.setHeight(300);

        Label label1 = new Label("Od jakiego dnia zaczyna sie ten miesiac?");
        Label label2 = new Label("Ile dni ma ten miesiac?");

        window.setOnCloseRequest(e-> month =null);

        ArrayList<String> arrayListDays = new ArrayList<>();
        arrayListDays.add("Poniedzialek");
        arrayListDays.add("Wtorek");
        arrayListDays.add("Sroda");
        arrayListDays.add("Czwartek");
        arrayListDays.add("Piatek");
        arrayListDays.add("Sobota");
        arrayListDays.add("Niedziela");


        ObservableList<String> optionsDays = FXCollections.observableArrayList(arrayListDays);
        ComboBox<String> comboDays=new ComboBox<>(optionsDays);


        ComboBox<Integer> comboMonth=ViewHelpers.prepareComboBoxInteger(31,1,0,28);

        Button closeButton = new Button ("OK");
        closeButton.setOnAction(e-> {
            window.close();
            month = new Month(comboDays.getSelectionModel().getSelectedIndex(),comboMonth.getValue());
        });

        Button closeButton2 = new Button ("Wczytaj");
        closeButton2.setOnAction(e-> {
            window.close();
            month = null;
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,comboDays,label2,comboMonth,closeButton,closeButton2);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return month;
    }

}
