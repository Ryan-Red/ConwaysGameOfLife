package com.zazo.gameoflife.formes;

import com.zazo.gameoflife.EcranDeJeu;

public class Gosper {
    public static void drawGosper(int x, int y){

        for(int j = -4; j <= 4; j++ )
            for (int i = -18; i <= 17; i++) {
                int positionX = x + i;
                int positionY = y + j;

                if (positionX < 0 || positionY < 0 || positionX >= EcranDeJeu.COLONES || positionY >= EcranDeJeu.RANGEES) {
                    break;
                } else {
                    EcranDeJeu.listeCases[positionX][positionY].resetCellulesMortes();


                    if(j == -4 && (i == 6)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(j == -3 && (i == 4 || i == 6)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(j == -2 && (i == -6|| i == -5 || i == 2 || i == 3 || i == 16 || i == 17)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(j == -1 && (i == -7|| i == -3 || i == 2 || i == 3|| i == 16 || i == 17)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(j == 0 && (i == -18|| i == -17 || i == -8 || i == -2|| i == 2 || i == 3)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(j == 1 && (i == -18|| i == -17 || i == -8 || i == -4 || i == -2|| i == -1 || i == 4 || i == 6)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(j == 2 && (i == -8 ||  i == -2 || i == 6)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(j == 3 && (i == -7|| i == -3)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                    else if(j == 4 && (i == -6|| i == -5)) EcranDeJeu.listeCases[positionX][positionY].setCellulePresente(true);
                }

            }




    }
}
