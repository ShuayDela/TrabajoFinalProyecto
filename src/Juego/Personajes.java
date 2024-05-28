package Juego;
/**
 * Clase Personjes es los atributos que tienen en comun todos los personajes del juego
 * No inicializa nada solo pasa los atributos a sus hijos
 */
abstract public class Personajes {
    protected String logo; // lo que representa al personaje ya sea zombie o planta
    protected boolean vivo; // si el zombie esta vivo
    protected int fila; // el posicionamiento del personaje
    protected int columna; // el posicionamiento del personaje
    protected int hp; // los puntos de vida del personaje
    protected int danyo; // el daño que hace el personaje
    protected String logomuerto; // el simbolo de cuando el personaje este muerto
}
