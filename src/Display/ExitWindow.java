package Display;

import Processing.FileManager;
import Processing.Helpers;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitWindow {
    private static boolean ans;

    public static boolean display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setWidth(300);
        window.setHeight(300);

        Label label1 = new Label(message);

        Button closeButton1 = new Button ("Zapisz zmiany");
        closeButton1.setOnAction(e-> {
            window.close();
            FileManager.save(Helpers.getVersion(),"Workers.txt","Connections.txt",false);
            ans = true;
        });

        Button closeButton2 = new Button ("Wyjdz bez zapisywania");
        closeButton2.setOnAction(e-> {
            window.close();
            ans = true;
        });

        Button closeButton3 = new Button ("Nie zamykaj");
        closeButton3.setOnAction(e-> {
            window.close();
            ans = false;
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, closeButton1,closeButton2,closeButton3);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return ans;
    }

}
