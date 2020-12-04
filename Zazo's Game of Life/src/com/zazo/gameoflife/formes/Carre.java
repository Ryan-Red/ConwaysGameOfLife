package com.zazo.gameoflife.formes;

import com.zazo.gameoflife.EcranDeJeu;

public class Carre {
    public static void drawCarre(int x, int y){

        for(int j = -5; j <= 5; j++ )
            for (int i = -5; i <= 5; i++) {
                int positionX = x + i;
                int positionY = y + j;

                if (positionX < 0 || positionY < 0 || positionX >= EcranDeJeu.COLONES || positionY >= EcranDeJeu.RANGEES) {
                    break;
                } else {
                    EcranDeJeu.listeCases[positionX][positionY].resetCellulesMortes();
                    if(Math.abs(j) == 5) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(Math.abs(i) == 5)EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);

                }

            }

    }

}
