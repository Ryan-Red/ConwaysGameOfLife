package com.zazo.gameoflife;

import com.zazo.gameoflife.fenetreSuppelementaires.AlertBox;

import java.io.*;

class SaveLoad {
    private static String fileName = "Save_data.txt";

    static void save(){

        try{
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int j = 0; j < EcranDeJeu.sliderRangees.getValue(); j ++){
                for(int i=0; i < EcranDeJeu.sliderColones.getValue(); i++){
                    if(EcranDeJeu.listeCases[i][j].getCellulePresente()){
                        bufferedWriter.write(1);
                    }
                    else bufferedWriter.write(0);
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch (IOException ex){
            AlertBox.alert("Erreur", "Aucun fichier ne fut trouvé",18);
        }


    }

    static void load(){
        String line;

        try{
            FileReader premiereLecture = new FileReader(fileName);
            BufferedReader LongueurFichier = new BufferedReader(premiereLecture);


            FileReader deuxiemeLecture = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(deuxiemeLecture);

            int longeur = 0;
            int largeur = 0;
            while((line = LongueurFichier.readLine()) != null){
                largeur = line.length();
                longeur++;
            }


            EcranDeJeu.resizeGridPane(longeur,largeur);
            EcranDeJeu.RANGEES = longeur;
            EcranDeJeu.COLONES = largeur;


            int j = 0;
            while((line = bufferedReader.readLine()) != null){

                for(int i = 0; i < line.length(); i++){
                    if(line.charAt(i) == 1) {
                        EcranDeJeu.listeCases[i][j].setCellulePresente(true);
                    }
                    else EcranDeJeu.listeCases[i][j].setCellulePresente(false);
                }
                j++;

            }

            EcranDeJeu.sliderColones.setValue(largeur);
            EcranDeJeu.sliderRangees.setValue(longeur);


        }catch (Exception ex){
            AlertBox.alert("Erreur", "Aucun fichier sauveguardé.",18);
        }



    }

}
