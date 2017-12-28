import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.HashSet;


public class Main extends Application implements EventHandler<ActionEvent>{

    private static Label bottomText;
    private Label[][] labels;
    private int numberOfDays = 31;
    private int width=1200;
    private int height=800;
    private myButton[][]buttons;
    private GridPane gridPane;
    private ToolBar toolBar;
    private BorderPane borderPane;
    HashSet<Connection> connections=new HashSet<>();
    private Button add,remove;
    static myHashSet<Worker> workers=new myHashSet<>();
    static String bottomString;

    static void updateString(){
        bottomString=workers.toString();
        bottomText.setText(bottomString);

    }

    private void ButtonsSetup(){
        final WorkerHandler workerHandler=new WorkerHandler();

        buttons = new myButton[3][numberOfDays];

        for(int j=0;j<3;j++){
            for(int i=0;i<numberOfDays;i++){
                buttons[j][i]=new myButton();
                buttons[j][i].setMinWidth(width / numberOfDays);
                GridPane.setConstraints(buttons[j][i],i,2*j+1);
                buttons[j][i].setOnAction(this);
                buttons[j][i].setText("v");
                buttons[j][i].column=i;
                buttons[j][i].row=j;
            }
        }

        add=new Button("Dodaj");
        remove=new Button("Usun");

        add.setOnAction(workerHandler);
        remove.setOnAction(workerHandler);


    }

    private void cleanLabels(){
        for (int j = 1; j < 4; j++) {
            for (int i = 0; i < numberOfDays; i++) {

                labels[j][i].setText("");

            }
        }
    }

    private void LabelsSetup(){
        labels = new Label[4][numberOfDays];

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

    }

    private void GridPaneSetup(){
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));

    }

    private void BorderPaneSetup(){
        borderPane=new BorderPane();

        toolBar = new ToolBar(add, remove);

        borderPane.setTop(toolBar);
        borderPane.setCenter(gridPane);

        //bottomString=new String();
        bottomText=new Label(bottomString);
        borderPane.setBottom(bottomText);

    }

    private void GridPaneAddChildren(){

        for (int i = 0; i < 4; i++) {
            gridPane.getChildren().addAll(labels[i]);
        }
        for(int i=0;i<3;i++) {
            gridPane.getChildren().addAll(buttons[i]);
        }

    }

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


        GridPaneSetup();
        ButtonsSetup();
        LabelsSetup();

        GridPaneAddChildren();

        gridPane.setGridLinesVisible(true);

        BorderPaneSetup();

        Scene scene1 = new Scene(borderPane, width + 25, height + 25);
        window.setScene(scene1);
        window.show();

    }

    @Override
    public void handle(ActionEvent event) {
        myButton mB= (myButton) event.getSource();


        Connection input=ShiftSelector.display(mB);
        connections.add(input);

        for (Worker worker:workers) {
            worker.minutes=0;
            for (Connection connection:connections) {
                if (connection.worker==worker)
                    worker.minutes+=connection.minutes;
            }

        }
        updateString();
        cleanLabels();

        for (Connection connection:connections) {
            labels[connection.shift.row+1][connection.shift.column].setText(labels[connection.shift.row+1][connection.shift.column].getText().concat(connection.display())+"\n");
        }

    }



}
