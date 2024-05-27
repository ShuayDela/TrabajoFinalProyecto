package Plantas;

public class Girasol extends Plantas {
    private int turnoPlantado; // el turno en el que fue plantado el girasol
    /**
     * Constructor de la clase Girasol hereda de plantas
     * @param fila La fila en la que se colocará el Girasol
     * @param columna La columna en la que se colocará el Girasol
     */
    public Girasol (int fila, int columna,int turnoPlantado){
        super (fila,columna);
        this.turnoPlantado = turnoPlantado;
        hp = 2;
        logo = "G";
        danyo = 0;
    }
    /**
     * Genera soles según el turno actual del juego
     * @param turnoActual El turno actual del juego
     * @return La cantidad de soles generados por el girasol en el turno actual
     */
    public int generarSoles(int turnoActual){ //Sistema para que el girasol genere los soles
        if (((turnoActual - turnoPlantado) % 2 == 0 && turnoActual > turnoPlantado)){ //genera 25 soles cada dos turnos despues de ser plantado
        }
        return 0; // girasol no da soles ese turno
    }
}
