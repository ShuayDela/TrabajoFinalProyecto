package Plantas;
import Zombies.Zombies;
public class Lanzaguisantes extends Plantas {

    public Lanzaguisantes(int fila,int columna){
        super (fila,columna);
        logo = "L|";
        soles = 100;
        hp = 5;
        daño = 2;
    }
    public void imprimir (){
        System.out.println("Lanzaguisantes (100 soles)");
    }
}
