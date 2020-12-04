import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



class Case {
    private boolean cellulePresente;
    Rectangle Cellule = new Rectangle(10,10);
    private int cellulesMortes;

    boolean getCellulePresente() { return cellulePresente; }

    void resetCellulesMortes(){ cellulesMortes = 0; }

    void setCellulePresente(boolean cellulePresente) {
        if(getCellulePresente() && cellulesMortes < 5){
            cellulesMortes++;
        }
        this.cellulePresente = cellulePresente;
        if(cellulePresente){
            Cellule.setFill(Color.RED);
        }else{

            if(cellulesMortes == 0) Cellule.setFill(Color.rgb(0,0,255,0));
            else if(cellulesMortes == 1) Cellule.setFill(Color.rgb(0,0,255,0.125));
            else if(cellulesMortes == 2)  Cellule.setFill(Color.rgb(0,0,255,0.25));
            else if(cellulesMortes == 3)  Cellule.setFill(Color.rgb(0,0,255,0.375));
            else if(cellulesMortes == 4)  Cellule.setFill(Color.rgb(0,0,255,0.5));
            else if(cellulesMortes == 5)  Cellule.setFill(Color.rgb(0,0,255,0.625));
        }
    }
}
