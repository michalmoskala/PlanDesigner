package Handlers;

import Display.FileView;
import Processing.Data;
import Display.MainView;
import Processing.Helpers;
import Processing.FileManager;
import Processing.myButton;
import Types.Connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


import java.io.File;

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

                Data.updateAllWorkers();

                if (!Data.connections.isEmpty())
                    MainView.updateLabels();
            }

        }

        else if(a.getText().equals("Zapisz"))
        {
            FileManager.save(Helpers.getVersion(),"Workers.txt",false);
            Data.updateAllWorkers();
        }

        else if(a.getText().equals("Wczytaj"))
        {
//            File file = FileView.load();
//            if (file != null)
//            FileManager.load(file);

        }
        else if(a.getText().equals("Zmien"))
        {
            Data.timeVisible =!Data.timeVisible;

            if (!Data.connections.isEmpty())
                MainView.updateLabels();
        }

    }

}
