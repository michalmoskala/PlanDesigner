import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ShiftSelector {
    private static String string;

    static private String formatString(String source){

        while(source.contains(","))
        {
            int i=source.indexOf(",");
            source=source.substring(0,i)+"\n"+source.substring(i+1,source.length());
        }

        return source;
    }

    static String display(){

        Stage window = new Stage();
        TextArea textArea=new TextArea();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wybierz");
        window.setMinWidth(300);
        window.setWidth(300);
        window.setHeight(300);

        Label label1 = new Label("Wpisz numery ludzi na zmianach");
        Button closeButton1 = new Button ("OK");
        closeButton1.setOnAction(e-> {
            window.close();
            string = formatString(textArea.getText());
        });

        HBox mainlayout = new HBox(10);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, textArea,closeButton1);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return string;
    }

}
