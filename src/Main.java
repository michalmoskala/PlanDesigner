import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent>{

    Scene scene1;
    String string;
    Label[][] labels;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

        window.setOnCloseRequest(e -> {
                    e.consume();
                    if(Alert.display("100%?", "Czy chcesz zamknac aplikacje?"))
                        window.close();

                }
        );
        window.setTitle("Plan Designer");

        int numberOfDays = 31;
        int width=1200;
        int height=800;

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        labels = new Label[4][numberOfDays];
        myButton[][]buttons = new myButton[3][numberOfDays];

        for(int j=0;j<3;j++){
            for(int i=0;i<numberOfDays;i++){
                buttons[j][i]=new myButton();
                buttons[j][i].setMinWidth(width / numberOfDays);
                GridPane.setConstraints(buttons[j][i],i,2*j+1);
            }
        }

        for (int i = 0; i < numberOfDays; i++) {
            labels[0][i] = new Label(Integer.toString(i + 1));
            labels[1][i] = new Label();
            labels[2][i] = new Label();
            labels[3][i] = new Label();

        }


        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < numberOfDays; i++) {
                GridPane.setConstraints(labels[j][i], i, 2*j);
                labels[j][i].setMinSize(width / numberOfDays, height/4);
                labels[j][i].setAlignment(Pos.CENTER);

            }
        }


            for (int i = 0; i < numberOfDays; i++) {
                labels[0][i].setMinSize(width / numberOfDays, 30);


            }
        for (int i = 0; i < 4; i++) {
            gridPane.getChildren().addAll(labels[i]);
        }
        for(int i=0;i<3;i++) {
            gridPane.getChildren().addAll(buttons[i]);
        }

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < numberOfDays; i++) {
                buttons[j][i].setOnAction(this);
                buttons[j][i].setText("v");
                buttons[j][i].column=i;
                buttons[j][i].row=j;
            }
        }
        gridPane.setGridLinesVisible(true);
        scene1 = new Scene(gridPane, width+25, height);

        window.setScene(scene1);
        window.show();

    }

    @Override
    public void handle(ActionEvent event) {
        myButton mB= (myButton) event.getSource();
        System.out.println(mB.column);
        System.out.println(mB.row);


        String input=ShiftSelector.display();
        if(!input.isEmpty())
        labels[mB.row+1][mB.column].setText(input);

    }



}
