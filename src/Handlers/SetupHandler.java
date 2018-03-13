package Handlers;

import Processing.Data;
import Display.MainView;
import Processing.Helpers;
import Types.Connection;
import Display.WeekdaySetupWindow;
import Processing.FileManager;
import Processing.myButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import static Display.ShiftSelector.display;

public class SetupHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

        Button a= (Button) event.getSource();

        if(a.getText().equals("v"))
        {
            myButton mB = (myButton) event.getSource();


            Connection input = display(mB, Data.workers);

            if(input!=null) {
                Data.connections.add(input);

                MainView.updateAllWorkers();

                if (!Data.connections.isEmpty())
                   MainView.updateLabels();
            }

            FileManager.save("--------","bworkers.txt","bcons.txt",true);
        }

        else if(a.getText().equals("Wybierz dzien tygodnia"))
        {
            Integer b= WeekdaySetupWindow.weekday();
            Data.setWeekday(b);

        }

        else if(a.getText().equals("Zapisz"))
        {
            FileManager.save(Helpers.getVersion(),"Workers.txt","Connections.txt",false);
            MainView.updateAllWorkers();
        }

        else if(a.getText().equals("Wczytaj"))
        {
            FileManager.load();
        }
        else if(a.getText().equals("Zmien"))
        {
            Data.isSetup=!Data.isSetup;

            if (!Data.connections.isEmpty())
                MainView.updateLabels();
        }

    }

}
