package com.zazo.gameoflife;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class Case {
    private boolean cellulePresente;
    Rectangle Cellule = new Rectangle(10,10);
    private int cellulesMortes;

    boolean getCellulePresente() { return cellulePresente; }

    public void resetCellulesMortes(){ cellulesMortes = 0; }

    public void setCellulePresente(boolean cellulePresente) {
        if(getCellulePresente() && cellulesMortes < 3){
            cellulesMortes++;
        }
        this.cellulePresente = cellulePresente;
        if(cellulePresente){
            Cellule.setFill(Color.RED);
        }else{
            if(cellulesMortes == 0)Cellule.setFill(Color.rgb(0,0,255,0));
            else if(cellulesMortes == 1)Cellule.setFill(Color.rgb(0,0,255,0.15));
            else if(cellulesMortes == 2)Cellule.setFill(Color.rgb(0,0,255,0.4));
            else Cellule.setFill(Color.rgb(0,0,255,0.8));

        }
    }
}
