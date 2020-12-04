package com.zazo.gameoflife;

import com.zazo.gameoflife.fenetreSuppelementaires.AlertBox;
import javafx.concurrent.Task;

public class SimulationTask extends Task<Void> {


    @Override
    protected Void call(){

        try{
            EcranDeJeu.simulation(false);

            if(EcranDeJeu.loop){
                this.succeeded();
            }

        }catch (Exception e){
            AlertBox.alert("Erreur", "Une erreure s'est produite, veuillez fermer le programme.",18);
        }



        return null;
    }

    @Override
    protected void failed() {
        super.failed();
    }
    @Override
    protected void succeeded(){

    }
}
