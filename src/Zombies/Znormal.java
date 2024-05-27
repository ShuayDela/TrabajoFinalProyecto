package Zombies;

public class Znormal extends Zombies {
    /**
     * Constructor de la clase Znormal (zombi normal) hereda de zombies
     * @param fila posicionamiento
     * @param columna posicionamiento
     */
    public Znormal (int fila,int columna){
        super (fila,columna);
        logo = "️🧟‍♂️";
        hp = 3;
        daño = 2;
    }
}
