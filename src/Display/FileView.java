package Display;


import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileView {

    public static File load(){
        Stage window = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wczytaj");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        return fileChooser.showOpenDialog(window);
    }

    public static File save(){
        Stage window = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Plan.txt");

        return fileChooser.showSaveDialog(window);
    }


}
