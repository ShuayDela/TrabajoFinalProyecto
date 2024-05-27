package Juego;

import Plantas.Plantas;
import Utilidades.Util;
import Plantas.*;
import Zombies.*;
import Plantas.Lanzaguisantes;
import java.io.*;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Mapa {
    private int killszombies; //contador de muertos de zombies
    private int plantasplantadas; // contador de plantas plantadas
    private int killplantas; //contador de plantas muertas
    private String nombre; //el nombre del jugador
    private int soles; //los soles en el juego
    private int filas; // el numero de filas que tendra el mapa
    private int columnas; // el numero de columnas que tendra el mapa
    private boolean finalizarjuego; // booleano para saber si el juego ya ha terminado
    private ArrayList <CortaCesped> cortaCespeds; //una lista de cortacesped (son 5 , 1 por cada fila)
    private ArrayList<Plantas> plantas ; // lista de plantas en el juego
    private ArrayList<Zombies> zombies; // lista de zombies en el juego
    private int turno; // el numero de turnos en el que esta el jugador
    public Dificultad dificultad; // La dificultad del que elija el jugador

    /**
     * Constructor de la clase Mapa.
     * Inicializa los atributos y configura el mapa del juego
     */
    public Mapa (){
        filas = 5;
        columnas = 15;
        turno = 1;
        soles = 0;
        killplantas = 0;
        killszombies = 0;
        plantasplantadas = 0;
        cortaCespeds = new ArrayList<>();
        plantas = new ArrayList<>();
        zombies = new ArrayList<>();
        cortaCespeds.add(new CortaCesped(0,0));
        cortaCespeds.add(new CortaCesped(0,1));
        cortaCespeds.add(new CortaCesped(0,2));
        cortaCespeds.add(new CortaCesped(0,3));
        cortaCespeds.add(new CortaCesped(0,4));
        finalizarjuego = false;
    }
    /**
     * Imprime el mapa
     */
    public void imprimirMapa() {
        System.out.println("\u2550" + "\u2550" + "\u2550" +"\u2550"+ "\u2550" + "\u2550" + "\u2550" +"\u2550" +"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550");
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                boolean esPlanta = false;
                for (int k = 0; k < plantas.size() && !esPlanta; k++) {
                    Plantas actual = plantas.get(k); //revisa todas las plantas
                    if (actual.getFila() == i && actual.getColumna() == j) { // si el posicionamiento  de la planta coincide con el i/j se printean
                        System.out.print("  ");
                        if (actual.isVivo()) { // en este caso si esta vivo se muestra su logo
                            System.out.print(actual.getLogo());
                        } else {
                            System.out.print(actual.getLogomuerto()); // si esta muerto se muestra su logo de muerto
                        }
                        esPlanta = true; // si se encontro alguna planta se marca que se encontro una planta
                    }
                }
                if (!esPlanta) { //si no se encontro una planta hace el mismo procedimiento para los zombies
                    boolean esZombi = false;
                    for (int o = 0; o < zombies.size() && !esZombi; o++) {
                        Zombies actual = zombies.get(o);
                        if (actual.getFila() == i && actual.getColumna() == j) {
                            System.out.print("  ");
                            if (actual.getVivo()) {
                                System.out.print(actual.getLogo());
                            } else {
                                System.out.print(actual.getLogomuerto());
                            }
                            esZombi = true;
                        }
                    }
                    if (!esZombi) { //lo mismo para los cortacesped
                        boolean esCortaCesped = false;
                        for (int w = 0; w < cortaCespeds.size() && !esCortaCesped; w++) {
                            CortaCesped actual = cortaCespeds.get(w);
                            if (actual.getFila() == i && actual.getColumna() == j) {
                                if (actual.isActivado()){
                                    System.out.print("|");
                                    esCortaCesped = true;
                                }
                                if (!actual.isActivado()){
                                    System.out.print("º");
                                    esCortaCesped = true;
                                }
                            }
                        }
                        if (!esCortaCesped) { //finalmente si no hay nada simplemente se escribe un -
                            System.out.print("  ");
                            System.out.print("-");
                        }
                    }
                }
            }
            System.out.println();
            System.out.println("\u2550" + "\u2550" + "\u2550" +"\u2550"+ "\u2550" + "\u2550" + "\u2550" +"\u2550" +"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550"+"\u2550");
        }
    }
    /**
     * Quita una planta del mapa en la posición especificada
     * @param fila La fila de la planta a quitar.
     * @param columna La columna de la planta a quitar.
     */

    public void quitarPlanta(int fila , int columna) {
    boolean encontrado = false;
        for (int i = 0; i< plantas.size() && !encontrado; i++){
        Plantas planta = plantas.get(i);
            if (planta.getFila() == fila && planta.getColumna() == columna ){ //busca la planta con los parametros indicados
                plantas.remove(planta); // si lo encuentra lo elimina y marca el encontrado como true
                encontrado = true;
            }
        }
        if (!encontrado){
            System.out.println("No hay ninguna planta en esa posicion");
        }
        else if (encontrado){
            System.out.println("¡Se ha quitado la planta con exito!");
        }
    }
    /**
     * Mueve los zombies una casilla hacia la izquierda en el mapa
     */
    public void moverZombies() {
        for (int i = 0; i < zombies.size(); i++) {
            Zombies actual = zombies.get(i);
            int nuevaColumna = actual.getColumna() - 1; // la columna a la que queremos que se mueva el zombie
            if (nuevaColumna < 0) { // si el zombie llega a la ultima columna ( la casa del jugador )
                boolean activado = false;
                for (CortaCesped corta : cortaCespeds) { // revisa si en la fila en la que este el zombie este un cortacesped si este esta activo hara su funcion de eliminar todos los zombies en la fila
                    if (corta.getFila() == actual.getFila()) {
                        if (corta.isActivado()) {
                            corta.activarcarretilla(zombies);
                            corta.setActivado(false);
                            activado = true; // y se marca que se activo
                        }
                    }
                }
                if (!activado) { // si vuelven los zombies y esta activado el juego finalizara
                    finalizarjuego = true;
                }
                continue;
            }

            boolean hayOtroZ = false; //funcion para el zombie no se mueva si otro zombie esta parado
            for (int j = 0; j < zombies.size() && !hayOtroZ; j++) {
                Zombies otro = zombies.get(j);
                if (otro.getFila() == actual.getFila() && otro.getColumna() == nuevaColumna) {
                    hayOtroZ = true;
                }
            }

            boolean hayPlanta = false; // lo mismo para que se pare si hay una planta el zombie no se mueva
            for (int k = 0; k < plantas.size() && !hayPlanta; k++) {
                Plantas otraplanta = plantas.get(k);
                if (otraplanta.getFila() == actual.getFila() && otraplanta.getColumna() == nuevaColumna) {
                    hayPlanta = true;
                }
            }

            if (!hayOtroZ && !hayPlanta) { // si no hay ni zombie ni planta el zombie se movera a una posicion a la izquierda
                actual.setColumna(nuevaColumna);
            }
        }
    }

    /**
     * Genera zombies en el mapa según la dificultad actual
     */
    public void generarZombieDificultad(){
        int numaleatorio = Util.obtenerEnteroAleatorio(0,4);

        if (dificultad == Dificultad.FACIL || dificultad == Dificultad.NORMAL){
            generarZombie(numaleatorio); // si la dificultad es FACIL/NORMAL solo se generara un zombie
        }
        if (dificultad == Dificultad.DIFICIL){ // si la dificultad es dificil se generaran 2 zombies
            int numaleatorio2 = Util.obtenerEnteroAleatorio(0,4);
            if (numaleatorio2 == numaleatorio){ // esto es para que los zombies no aparezcan en la misma posicion a la vez
                do {
                    numaleatorio2 = Util.obtenerEnteroAleatorio(0,4);
                } while (numaleatorio2 == numaleatorio);
            }
            generarZombie(numaleatorio);
            generarZombie(numaleatorio);
        }
    }
    /**
     * Revisa si el juego ha finalizado y muestra un mensaje si es asi ademas guarda las estadisticas de dicho juego
     */
    public void revisarFinalJuego (){
        if (finalizarjuego ){
            System.out.println("LOS ZOMBIES SE HAN COMIDO TU CEBEBRO");
            estadisticasResultados();
        }
    }
    /**
     * Genera un zombie en el mapa con probabilidades basadas en la dificultad
     * @param numeroaleatorio Número aleatorio para determinar el tipo de zombie
     */
    public void generarZombie (int numeroaleatorio){
        int ruleta = Util.obtenerEnteroAleatorio(0,100);
        Zombies zombi = null;
        if (dificultad == Dificultad.FACIL){
            if (ruleta >= 0 && ruleta <= 60){
                 zombi = new Znormal(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 61 && ruleta <= 90){
                 zombi = new Caracono(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 91 && ruleta <= 100 ){
                 zombi = new Caracubo(numeroaleatorio,getColumnas());
            }
        } else if (dificultad == Dificultad.NORMAL) {
            if (ruleta >= 0 && ruleta <= 35){
                 zombi = new Znormal(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 36 && ruleta <= 70){
                 zombi = new Caracono(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 71 && ruleta <= 100 ){
                 zombi = new Caracubo(numeroaleatorio,getColumnas());
            }
        }
        else if (dificultad == Dificultad.DIFICIL){
            if (ruleta >= 0 && ruleta <= 20){
                 zombi = new Znormal(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 21 && ruleta <= 50){
                 zombi = new Caracono(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 51 && ruleta <= 100 ){
                 zombi = new Caracubo(numeroaleatorio,getColumnas());
            }
        }
        zombies.add (zombi);
    }
    /**
     * Pasa al siguiente turno del juego, realizando todas las acciones necesarias
     */
    public void pasarturno(){ // practicamente llama a las demas funciones que estan creadas y explicadas en el programa
        if (!finalizarjuego){
            generarZombieDificultad();
            moverZombies();
            for (Plantas planta : plantas){
                planta.atacar(zombies);
            }
            for (Zombies zombi : zombies){
                zombi.atacar(plantas);
            }
            revisarZombies();
            revisarPlantas();
            imprimirMapa();
            eliminarMuertos();
            generarSoles();
            turno++;
        }
      revisarFinalJuego();
    }
    /**
     * Revisa si los zombies en el mapa están vivos
     */
    public void revisarZombies (){ // funcion principal que revisa si los zombies estan vivos o no
        for (int i = 0; i < zombies.size(); i++){
            Zombies actual = zombies.get(i);
                if (actual.getHp() <= 0){
                    actual.setVivo(false);
                    killszombies++; // aumenta el contador de zombies muertos
                }
            }
        }
    /**
     * Revisa si los plantas en el mapa están vivos
     */
    public void revisarPlantas(){ //funcion principal que revisa si las plantas estan vivas o no
        for (int i = 0; i< plantas.size(); i++){
            Plantas actual = plantas.get(i);
            if (actual.getHp() <= 0){
                actual.setVivo(false);
                killplantas++; // aumenta el contador de las plantas muertas
            }
        }
    }
    /**
     * Genera soles para el jugador en función del turno y los girasoles creados
     */
    public void generarSoles (){
        int solesturno = 0;
        if (dificultad == Dificultad.FACIL || dificultad == Dificultad.NORMAL){
            if (turno % 2 == 0 | turno == 1){
                solesturno = 50;
            }
            //si la dificultad esta en facil o normal los soles que se daran por cada 2 turnos seran de 50;
        }
        else if (dificultad == Dificultad.DIFICIL) {
            if (turno % 2 == 0 | turno == 1) {
                solesturno = 75;
                //si la dificultad esta en dificil los soles que se daran por cada 2 turnos seran de 75
            }
        }
        int solesturnogirasol = 0;
        for (int i = 0; i< plantas.size(); i++){
            Plantas actual = plantas.get(i);
            if (actual instanceof Girasol){
                solesturnogirasol += ((Girasol) actual).generarSoles(turno);
            } //por cada girasol en la lista de plantas se crearan el numero estimado de soles que cree cada una (depende del turno en el que el girasol haya sido plantado)
        }
        this.soles = soles + solesturno + solesturnogirasol;
    }
    /**
     * Añade una planta al mapa en la posicion que se elija
     * @param nuevo La nueva planta a añadir
     */
    public void anyadirPlanta(Plantas nuevo){
        boolean existe = false;
        for (int i = 0; i < plantas.size() && !existe; i++){
            Plantas actualplanta = plantas.get(i);
            if (actualplanta.getFila() == nuevo.getFila() && actualplanta.getColumna() == nuevo.getColumna()){
                existe = true; // revisa si la planta existe en dicha posicion para que no se añada nada
            }
        }
        for (int i = 0; i< zombies.size() && !existe; i++){
            Zombies actualzombie = zombies.get(i);
            if(actualzombie.getFila() == nuevo.getFila() && actualzombie.getColumna() == nuevo.getColumna()){
                existe = true;  // lo mismo para los zombies ya que una planta no se podra poner si hay un zombie de por medio
            }
        }
        if (existe){
            System.out.println("No se puede añadir ya que esa posicion esta ocupada"); // mensaje de error si ocurre cualquiera de esos dos casos
        }
        if (!existe){
            plantas.add(nuevo);
            plantasplantadas++; // se aumenta el numero de plantas plantadas y se añade la planta
        }
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    /**
     * Abre el menú de control del juego
     * @param scanner Scanner para recibir la entrada del usuario
     */
    public void Menu (Scanner scanner){ //Menu donde se desarrolla el juego.
        boolean finalizarmenu = false;
        imprimirMapa();
        do {
            int soles = this.soles;
            int turno = this.turno;
            System.out.println("Tienes " + soles + " soles disponibles // turno : " + turno);
            //Menu para el usuario
            System.out.println("¿Que quieres hacer?");
            System.out.println("1- Plantar una planta");
            System.out.println("2- Quitar una planta");
            System.out.println("3- Pasar turno");
            System.out.println("4- Salir");
            int opcion = Util.Leernumero(scanner , 1,4);
            if (opcion == 1){
                //Menu de eleccion de que plantas plantar
                System.out.println("¿Que planta quieres plantar?");
                System.out.println("1- Lanzaguisantes (100 soles)");
                System.out.println("2- Girasol (50 soles)");
                System.out.println("3- Nuez (150 soles)");
                int opcion2 = Util.Leernumero(scanner,0,3);
                if (opcion2 ==1  || opcion2 == 2 || opcion2 == 3 ){
                    int [] filacolumna = Util.LeerNumeroFilaColumna(scanner);
                    if (filacolumna == null){
                        continue;
                    }
                    int fila = filacolumna[0];
                    int columna = filacolumna[1];
                    if (opcion2 == 1){
                        Plantas lanza = new Lanzaguisantes(fila,columna);
                        if (lanza.getSoles() > this.soles ){
                            System.out.println("No tienes suficientes soles"); // si no tiene suficientes soles dara error y no plantara la planta
                        }
                        else {
                            this.soles = this.soles - lanza.getSoles();
                            anyadirPlanta(lanza);
                        }
                    } else if (opcion2 == 2) {
                        Plantas girasol = new Girasol(fila,columna,turno);
                        if (girasol.getSoles() > this.soles ){
                            System.out.println("No tienes suficientes soles");
                        }
                        else {
                            this.soles = this.soles - girasol.getSoles();
                            anyadirPlanta(girasol);
                        }
                    }else if (opcion2 == 3){
                        Plantas nuez = new Nuez(fila,columna);
                        if (nuez.getSoles() > this.soles ){
                            System.out.println("No tienes suficientes soles");
                        }
                        else {
                            this.soles = this.soles - nuez.getSoles();
                            anyadirPlanta(nuez);
                        }
                    } else if (opcion2 == 0){

                    }
                    imprimirMapa(); // despues de esta accion se imprimira el mapa
                }
            }
            if(opcion == 2){
                int [] filacolumna = Util.LeerNumeroFilaColumna(scanner);
                if (filacolumna == null){
                    continue;
                }
                int fila = filacolumna[0];
                int columna = filacolumna[1]; // pide la posicion de la planta que quieremos quitar
                quitarPlanta(fila,columna);
                imprimirMapa();
            }
            if (opcion == 3){
                System.out.println("Has pasado de turno!");
                pasarturno(); // pasar de turno
            }
            if (opcion == 4){
                System.out.println("Decidiste salir");
                finalizarjuego = true;
                estadisticasResultados(); // al salir del juego se finaliza del todo y se guardan los resultados
            }
        }while (!finalizarjuego);
    }
    /**
     * Elimina los elementos muertos del mapa (plantas y zombies).
     */
    public void eliminarMuertos() { //recoge una arraylist de zombies vivos y descarta a los que esten muertos lo mismo aplica para las  plantas
        ArrayList<Zombies> zombiesVivos = new ArrayList<>();
        for (Zombies zombie : zombies) {
            if (zombie.getVivo()) {
                zombiesVivos.add(zombie);
            }
        }
        zombies = zombiesVivos;

        ArrayList<Plantas> plantasVivas = new ArrayList<>();
        for (Plantas planta : plantas) {
            if (planta.isVivo()) {
                plantasVivas.add(planta);
            }
        }
        plantas = plantasVivas;
    }
    /**
     * Menu para seleccionar la dificultad a la que el jugador quiere jugar al juego
     */
    public void seleccionarDificultad (){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Elige una dificultad : ");
        System.out.println("1-Facil");
        System.out.println("2-Normal");
        System.out.println("3-Dificil");
        int opcion = Util.Leernumero(teclado,1,3);
        if (opcion == 1 ){
            System.out.println("Has elegido la dificultad FACIL");
            setDificultad(Dificultad.FACIL);
        }
        if (opcion == 2){
            System.out.println("Has elegido la dificultad NORMAL");
            setDificultad(Dificultad.NORMAL);
        }
        if (opcion == 3){
            System.out.println("Has elegido la dificultad DIFICIL");
            setDificultad(Dificultad.DIFICIL);
        }
    }
    /**
     * Guarda las estadísticas del juego en un archivo de texto
     */
    public void estadisticasResultados() {
        LocalDateTime tiempo = LocalDateTime.now();
        try (FileWriter escritor = new FileWriter("estadistica.txt", true)) {
            escritor.write("Nombre: " + nombre);
            escritor.write(", Zombies matados: " + killszombies);
            escritor.write(", Plantas plantadas: " + plantasplantadas);
            escritor.write(", Plantas muertas: " + killplantas);
            escritor.write(", Turnos durados: " + turno);
            escritor.write(", Fecha :" + LocalDate.now() + "a las " + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute());
            escritor.write(System.lineSeparator());
            System.out.println("Resultados guardados correctamente");
        } catch (IOException e) {
            System.out.println("Error al guardar los resultados: " + e.getMessage());
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public int getSoles() {
        return soles;
    }

    public void setSoles(int soles) {
        this.soles = soles;
    }

    public int getTurno() {
        return turno;
    }


    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public boolean isFinalizarjuego() {
        return finalizarjuego;
    }

    public void setFinalizarjuego(boolean finalizarjuego) {
        this.finalizarjuego = finalizarjuego;
    }
}

