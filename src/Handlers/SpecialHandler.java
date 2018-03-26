package Handlers;

import Processing.Data;
import Processing.myLabel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class SpecialHandler implements EventHandler<MouseEvent>{


    @Override
    public void handle(MouseEvent event)
        {
            myLabel mL = (myLabel) event.getSource();
            Data.switchSpecial(mL.getColumn());
        }
    }

