package com.zazo.gameoflife.formes;

import com.zazo.gameoflife.EcranDeJeu;

public class Cercle {
    public static void drawCercle(int x, int y){

        for(int j = -5; j <= 5; j++ )
            for (int i = -5; i <= 5; i++) {
                int positionX = x + i;
                int positionY = y + j;

                if (positionX < 0 || positionY < 0 || positionX >= EcranDeJeu.COLONES || positionY >= EcranDeJeu.RANGEES) {
                    break;
                } else {

                    EcranDeJeu.listeCases[positionX][positionY].resetCellulesMortes();
                    if(Math.abs(j) == 5 && (Math.abs(i) == 1 || i ==0)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(Math.abs(j) == 4 && (Math.abs(i) == 2 || Math.abs(i) == 3))EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if((Math.abs(j) == 3|| Math.abs(j) == 2) && Math.abs(i) == 4)EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if((Math.abs(j) == 1 || j == 0) && Math.abs(i) == 5)EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);

                }

            }




    }
}
