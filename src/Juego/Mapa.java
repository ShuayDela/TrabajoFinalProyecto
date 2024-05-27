package Juego;

import Plantas.Plantas;
import Utilidades.Util;
import Plantas.*;
import Zombies.*;
import Plantas.Lanzaguisantes;
import java.io.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Mapa {
    private int killszombies;
    private int plantasplantadas;
    private int killplantas;
    private String nombre;
    public int soles;
    public int filas;
    public int columnas;
    public boolean ilimitado;
    public boolean finalizarjuego;
    public ArrayList <CortaCesped> cortaCespeds;
    public ArrayList<Plantas> plantas ;
    public ArrayList<Zombies> zombies;
    public int turno;
    public Dificultad dificultad;

    public Mapa (){
        filas = 5;
        columnas = 20;
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
    public void imprimirMapa() {
        System.out.println("═══════════════════════════");
        for (int i = 0; i < getFilas(); i++) {
            for (int j = 0; j < getColumnas(); j++) {
                boolean esPlanta = false;
                for (int k = 0; k < plantas.size() && !esPlanta; k++) {
                    Plantas actual = plantas.get(k);
                    if (actual.getFila() == i && actual.getColumna() == j) {
                        System.out.print("  ");
                        if (actual.isVivo()) {
                            System.out.print(actual.getLogo());
                        } else {
                            System.out.print(actual.getLogomuerto());
                        }
                        esPlanta = true;
                    }
                }
                if (!esPlanta) {
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
                    if (!esZombi) {
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
                        if (!esCortaCesped) {
                            System.out.print("  ");
                            System.out.print("-");
                        }
                    }
                }
            }
            System.out.println();
            System.out.println("═══════════════════════════");
        }
    }

    public void quitarPlanta(int fila , int columna) { //funcion para quitar una planta
    boolean encontrado = false;
        for (int i = 0; i< plantas.size() && !encontrado; i++){
        Plantas planta = plantas.get(i);
            if (planta.getFila() == fila && planta.getColumna() == columna ){
                plantas.remove(planta);
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
    public void moverZombies() {
        for (int i = 0; i < zombies.size(); i++) {
            Zombies actual = zombies.get(i);
            int nuevaColumna = actual.getColumna() - 1;

            if (nuevaColumna < 0) {
                boolean activado = false;
                for (CortaCesped corta : cortaCespeds) {
                    if (corta.getFila() == actual.getFila()) {
                        if (corta.isActivado()) {
                            corta.activarcarretilla(zombies);
                            corta.setActivado(false);
                            activado = true;
                        }
                    }
                }
                if (!activado) {
                    finalizarjuego = true;
                }
                continue;
            }

            boolean hayOtroZ = false;
            for (int j = 0; j < zombies.size() && !hayOtroZ; j++) {
                Zombies otro = zombies.get(j);
                if (otro.getFila() == actual.getFila() && otro.getColumna() == nuevaColumna) {
                    hayOtroZ = true;
                }
            }

            boolean hayPlanta = false;
            for (int k = 0; k < plantas.size() && !hayPlanta; k++) {
                Plantas otraplanta = plantas.get(k);
                if (otraplanta.getFila() == actual.getFila() && otraplanta.getColumna() == nuevaColumna) {
                    hayPlanta = true;
                }
            }

            if (!hayOtroZ && !hayPlanta) {
                actual.setColumna(nuevaColumna);
            }
        }
    }


    public void generarZombieDificultad(){ //funcion principal para el cambio de turno donde se generan zombies aleatoriamente (cuando añada mas zombies añadire una probabilidad de que zombiess pueden entrar)
        int numaleatorio = Util.obtenerEnteroAleatorio(0,4);

        if (dificultad == Dificultad.FACIL || dificultad == Dificultad.NORMAL){
            generarZombie(numaleatorio);
        }
        if (dificultad == Dificultad.DIFICIL){
            int numaleatorio2 = Util.obtenerEnteroAleatorio(0,4);
            if (numaleatorio2 == numaleatorio){
                do {
                    numaleatorio2 = Util.obtenerEnteroAleatorio(0,4);
                } while (numaleatorio2 == numaleatorio);
            }
            generarZombie(numaleatorio);
            generarZombie(numaleatorio);
        }
    }

    public void revisarFinalJuego (){
        if (finalizarjuego ){
            System.out.println("LOS ZOMBIES SE HAN COMIDO TU CEBEBRO");
            estadisticasResultados();
        }
    }
    public void generarZombie (int numeroaleatorio){
        int ruleta = Util.obtenerEnteroAleatorio(0,100);
        Zombies zombi = null;
        if (dificultad == Dificultad.FACIL){
            if (ruleta >= 0 && ruleta <= 60){
                 zombi = new Znormal(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 61 && ruleta <= 90){
                 zombi = new CaraCono(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 91 && ruleta <= 100 ){
                 zombi = new Caracubo(numeroaleatorio,getColumnas());
            }
        } else if (dificultad == Dificultad.NORMAL) {
            if (ruleta >= 0 && ruleta <= 35){
                 zombi = new Znormal(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 36 && ruleta <= 70){
                 zombi = new CaraCono(numeroaleatorio,getColumnas());
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
                 zombi = new CaraCono(numeroaleatorio,getColumnas());
            }
            if (ruleta >= 51 && ruleta <= 100 ){
                 zombi = new Caracubo(numeroaleatorio,getColumnas());
            }
        }
        zombies.add (zombi);
    }
    public void pasarturno(){ //funcion principal para que al pasar de turno se hagan las demas funciones principales para el progreso lineal del juego.
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
    public void revisarZombies (){ // funcion principal que revisa si los zombies estan vivos o no
        for (int i = 0; i < zombies.size(); i++){
            Zombies actual = zombies.get(i);
                if (actual.getHp() <= 0){
                    actual.setVivo(false);
                    killszombies++;
                }
            }
        }
    public void revisarPlantas(){ //funcion principal que revisa si las plantas estan vivas o no
        for (int i = 0; i< plantas.size(); i++){
            Plantas actual = plantas.get(i);
            if (actual.getHp() <= 0){
                actual.setVivo(false);
                killplantas++;
            }
        }
    }
    public void generarSoles (){
        int solesturno = 0;
        if (dificultad == Dificultad.FACIL || dificultad == Dificultad.NORMAL){
            if (turno % 2 == 0 | turno == 1){
                solesturno = 25;
            }
        }
        else if (dificultad == Dificultad.DIFICIL) {
            if (turno % 2 == 0 | turno == 1) {
                solesturno = 50;
            }
        }
        int solesturnogirasol = 0;
        for (int i = 0; i< plantas.size(); i++){
            Plantas actual = plantas.get(i);
            if (actual instanceof Girasol){
                solesturnogirasol += ((Girasol) actual).generarSoles(turno);
            }
        }
        this.soles = soles + solesturno + solesturnogirasol;
    }
    public void añadirPlanta (Plantas nuevo){
        boolean existe = false;
        for (int i = 0; i < plantas.size() && !existe; i++){
            Plantas actualplanta = plantas.get(i);
            if (actualplanta.getFila() == nuevo.getFila() && actualplanta.getColumna() == nuevo.getColumna()){
                existe = true;
            }
        }
        for (int i = 0; i< zombies.size() && !existe; i++){
            Zombies actualzombie = zombies.get(i);
            if(actualzombie.getFila() == nuevo.getFila() && actualzombie.getColumna() == nuevo.getColumna()){
                existe = true;
            }
        }
        if (existe){
            System.out.println("No se puede añadir ya que esa posicion esta ocupada");
        }
        if (!existe){
            plantas.add(nuevo);
            plantasplantadas++;
        }
    } //funcion basica que añade las plantas
    public void setTurno(int turno) {
        this.turno = turno;
    }
    public void Menu (Scanner scanner){ //Menu donde se desarrolla el juego.
        boolean finalizarmenu = false;
        imprimirMapa();
        do {
            int soles = this.soles;
            int turno = this.turno;
            System.out.println("Tienes " + soles + " soles disponibles // turno : " + turno);
            System.out.println("¿Que quieres hacer?");
            System.out.println("1- Plantar una planta");
            System.out.println("2- Quitar una planta");
            System.out.println("3- Pasar turno");
            System.out.println("4- Salir");
            int opcion = Util.Leernumero(scanner , 1,4);
            if (opcion == 1){
                System.out.println("¿Que planta quieres plantar?");
                System.out.println("1- Lanzaguisantes (100 soles)");
                System.out.println("2- Girasol (50 soles)");
                int opcion2 = Util.Leernumero(scanner,1,2);
                if (opcion2 ==1  || opcion2 == 2 ){
                    int [] filacolumna = Util.LeerNumeroFilaColumna(scanner);
                    if (filacolumna == null){
                        continue;
                    }
                    int fila = filacolumna[0];
                    int columna = filacolumna[1];
                    if (opcion2 == 1){
                        Plantas lanza = new Lanzaguisantes(fila,columna);
                        añadirPlanta(lanza);
                    } else if (opcion2 == 2) {
                        Plantas girasol = new Girasol(fila,columna,turno);
                        añadirPlanta(girasol);
                    }
                    imprimirMapa();
                }
            }
            if(opcion == 2){
                int [] filacolumna = Util.LeerNumeroFilaColumna(scanner);
                if (filacolumna == null){
                    continue;
                }
                int fila = filacolumna[0];
                int columna = filacolumna[1];
                quitarPlanta(fila,columna);
                imprimirMapa();
            }
            if (opcion == 3){
                System.out.println("Has pasado de turno!");
                pasarturno();
                finalizarmenu = false;
            }
            if (opcion == 4){
                System.out.println("Decidiste salir");
                System.out.println("¿Quieres guardar la partida?");
                System.out.println("1- SI");
                System.out.println("2- NO");
                int opcionsalir = Util.Leernumero(scanner , 1,2);
                if (opcionsalir == 1){
                    System.out.println("Decide el nombre con el que quieras guardar la partida");
                    String nombrepartida = scanner.next();

                }
                else {
                    System.out.println("Saliste");
                    finalizarmenu = true;
                }
            }
        }while (!finalizarmenu);
    }
    public void eliminarMuertos() {
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
    public void estadisticasResultados() {
        try (FileWriter escritor = new FileWriter("estadistica.txt", true)) {
            escritor.write("Nombre: " + nombre);
            escritor.write(", Zombies matados: " + killszombies);
            escritor.write(", Plantas plantadas: " + plantasplantadas);
            escritor.write(", Plantas muertas: " + killplantas);
            escritor.write(", Turnos durados: " + turno);
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

    public boolean isIlimitado() {
        return ilimitado;
    }

    public void setIlimitado(boolean ilimitado) {
        this.ilimitado = ilimitado;
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

