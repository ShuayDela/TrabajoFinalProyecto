package Plantas;

import Zombies.Zombies;

public class Girasol extends Plantas {
    private int turnoPlantado;
    public Girasol (int fila, int columna,int turnoPlantado){
        super (fila,columna);
        this.turnoPlantado = turnoPlantado;
        hp = 1;
        logo = "G ";
        nombre = "Girasol";
    }
    public int generarSoles(int turnoActual){ //Sistema para que el girasol genere los soles
        if ((turnoActual- turnoPlantado % 2 == 0)){ //girasol da soles ese turno esto lo que hace es que cada dos turnos el girasol de soles , teniendo en cuenta que si un girasol empezo en el turno 3 lo den en el 5 y otro que lo haya empezado en el turno 4 lo de en el 6
            return 25;
        }
        return 0; // girasol no da soles ese turno
    }
    public void atacar(Zombies zombi){ // el girasol no ataca realmente asi que no hay nada en este metodo

    }
    public void imprimir (){
        System.out.println("Girasol (50 soles)");
    }
}
