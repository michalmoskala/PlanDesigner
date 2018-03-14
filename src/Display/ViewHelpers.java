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
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i=0;i<=stop;i+=step){
            arrayList.add(i);
        }

        ObservableList<Integer> options = FXCollections.observableArrayList(arrayList);
        ComboBox<Integer> comboBox=new ComboBox<>(options);
        comboBox.setMinWidth(160-lenLimit);
        return comboBox;
    }

    static ComboBox<String> prepareComboBoxString(HashSet<Worker> workers, double lenLimit)
    {
        ArrayList<Worker> arrayList=new ArrayList<>(workers);

        arrayList.sort(new WorkerComparator());
        ArrayList<String> arrayListStrings = new ArrayList<>();

        for (Worker worker :arrayList){
            arrayListStrings.add(worker.getNick());
        }

        ObservableList<String> options = FXCollections.observableArrayList(arrayListStrings);
        ComboBox<String> comboBox=new ComboBox<>(options);
        if (lenLimit!=0)
            comboBox.setMinWidth(160-lenLimit);
        return comboBox;
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
