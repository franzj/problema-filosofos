
package src.filosofos;

import java.util.concurrent.Semaphore;

class Main {
  public static final String solucion = "varios-turnos";

  public static void help(){
    System.out.println("\tSolución '" + solucion + "'\n");
    System.out.println("Usage:  java -jar filosofos.jar <n>" +
      "\n\tn = numero de filósofos");
  }

  public static int[][] generarPalillos(int nroFilosofos){
    int palillos[][] = new int[nroFilosofos][2];

    palillos[0][0] = nroFilosofos - 1;
    palillos[0][1] = 0;
    for (int i = 1; i < nroFilosofos; i++) {
      palillos[i][0] = i - 1; //Palillo izquierdo
      palillos[i][1] = i; //Palillo derecho
    }
    return palillos;
  }

  public static void construir(int nroFilosofos){
    // Generamos los palillos del comedor
    int palillos[][] = generarPalillos(nroFilosofos);
    // Array de objetos semáforo que representan alos palillos
    Semaphore palillos_semaforo[] = new Semaphore[nroFilosofos];

    // Creamos los semáforos, hay el mismo número de palillos que comenzales
    for (int i = 0; i < nroFilosofos; i++) {
      // Sólo 1 permiso porque cada palillo-semaforo
      // puede tenerlo un filósofo a la vez
      palillos_semaforo[i] = new Semaphore(1);
    }

    // Creamos a los filósofos del comedor con su respectivos palillos
    for (int i = 0; i < nroFilosofos; i++) {
      new Filosofo(i, palillos_semaforo, palillos).start(); // iniciamos el hilo
    }
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      help();
    }
    else {
      try {
        construir(Integer.parseInt(args[0]));
      }
      catch (Exception e) {
        help();
      }
    }
  }
}
