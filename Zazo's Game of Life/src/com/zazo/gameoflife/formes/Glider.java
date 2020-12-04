package com.zazo.gameoflife.formes;

import com.zazo.gameoflife.EcranDeJeu;

public class Glider {


    public static void drawGlider(int x, int y){

        for(int j = -1; j <= 1; j++ )
            for (int i = -1; i <= 1; i++) {
                int positionX = x + i;
                int positionY = y + j;

                if (positionX < 0 || positionY < 0 || positionX >= EcranDeJeu.COLONES || positionY >= EcranDeJeu.RANGEES) {
                    break;
                } else {

                    EcranDeJeu.listeCases[positionX][positionY].resetCellulesMortes();
                    if (j == -1 && i == 0) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(j == 0 && i >= 0) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if( j== 1 && i !=0) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(false);



                }

            }




    }



}
