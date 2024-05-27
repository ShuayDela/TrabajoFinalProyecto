package Zombies;

public class Caracubo extends Zombies {
    /**
     * Constructor de la clase Caracubo hereda de zombies
     * @param fila posicionamiento
     * @param columna posicionamiento
     */
    public Caracubo (int fila,int columna){
        super (fila,columna);
        logo = "U";
        hp = 7;
        danyo = 3;
    }
}
