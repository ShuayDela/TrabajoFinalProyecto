package Zombies;

public class Caracono extends Zombies{
    /**
     * Constructor de la clase Caraconohereda de zombies
     * @param fila posicionamiento
     * @param columna posicionamiento
     */
    public Caracono(int fila, int columna){
        super (fila,columna);
        logo = "C";
        hp = 6;
        danyo = 3;
    }
}
