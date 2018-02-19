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

public class SetupWindow {
    private static Integer dayIndex;

    public static Integer weekday(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Dzien tygodnia");
        window.setMinWidth(300);
        window.setWidth(300);
        window.setHeight(300);

        Label label1 = new Label("Od jakiego dnia zaczyna sie ten miesiac?");


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


        Button closeButton = new Button ("OK");
        closeButton.setOnAction(e-> {
            window.close();
            dayIndex = comboDays.getSelectionModel().getSelectedIndex();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1,comboDays,closeButton);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return dayIndex;
    }

}
