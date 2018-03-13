package Display;

import Processing.WorkerComparator;
import Types.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashSet;

class ViewHelpers {
    static ComboBox<Integer> prepareComboBoxInteger(int stop, int step, double lenLimit)
    {
        ArrayList<Integer> arrayListHours = new ArrayList<>();

        for (int i=0;i<=stop;i+=step){
            arrayListHours.add(i);
        }

        ObservableList<Integer> optionsHours = FXCollections.observableArrayList(arrayListHours);
        ComboBox<Integer> comboHours=new ComboBox<>(optionsHours);
        comboHours.setMinWidth(160-lenLimit);
        return comboHours;
    }

    static ComboBox<String> prepareComboBoxString(HashSet<Worker> workers, double lenLimit)
    {
        ArrayList<Worker> arrayListWorkers=new ArrayList<>(workers);

        arrayListWorkers.sort(new WorkerComparator());
        ArrayList<String> arrayListStrings = new ArrayList<>();

        for (Worker worker :arrayListWorkers){
            arrayListStrings.add(worker.getNick());
        }

        ObservableList<String> optionsWorkers = FXCollections.observableArrayList(arrayListStrings);
        ComboBox<String> comboWorkers=new ComboBox<>(optionsWorkers);
        if (lenLimit!=0)
            comboWorkers.setMinWidth(160-lenLimit);
        return comboWorkers;
    }

    static GridPane setupLayout(GridPane layout)
    {
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        layout.autosize();

        return layout;

    }
}
