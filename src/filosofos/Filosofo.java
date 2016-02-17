
package src.filosofos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {

  private final int idFilosofo;
  private final Semaphore[] palillos_semaforo;
  private final int palilloIzquierdo;
  private final int palilloDerecho;
  private final Random tiempoAleatorio = new Random();

  public Filosofo(int idFilosofo, Semaphore[] palillos_semaforo,
    int[][] palillosFilosofo)
  {
    this.idFilosofo = idFilosofo;
    this.palillos_semaforo = palillos_semaforo;
    this.palilloIzquierdo = palillosFilosofo[idFilosofo][0];
    this.palilloDerecho = palillosFilosofo[idFilosofo][1];
  }

  protected void comer(){
    try {
      // Simular el tiempo que tarda el filósofo en comer,
      // entre 0.5 y 1.5 segundos:
      long tiempo = tiempoAleatorio.nextInt(1000) + 500;

      System.out.println("Filósofo " + idFilosofo +
      " esta comiendo en " + tiempo + " millis palillos " +
      palilloIzquierdo + " - " + palilloDerecho);

      this.sleep(tiempo);

      System.out.println("Filósofo " + idFilosofo +
      " ha terminado de comer. Libera los palillos " +
      palilloIzquierdo + " y " + palilloDerecho);

    } catch (InterruptedException ex) {
      System.out.println("Error : " + ex.toString());
    }
    // Libreramos el semáforo palillo izquierdo y derecho
    palillos_semaforo[palilloDerecho].release();
    palillos_semaforo[palilloIzquierdo].release();
  }

  protected void colaPalillo() {
    if (palillos_semaforo[palilloIzquierdo].tryAcquire()) {
      if (palillos_semaforo[palilloDerecho].tryAcquire()) {
        // Pudo coger ambos palillos y se pone a comer
        comer();

      } else {
        System.out.println("Filosofo " + idFilosofo +
          " se pone a la cola del palillo " + palilloDerecho);

        try {
          // Se pone en cola del siguiente palillo y espera entre 0.5 y 1 segundos
          this.sleep(tiempoAleatorio.nextInt(500) + 500);

        } catch (InterruptedException e) { }

        if (palillos_semaforo[palilloDerecho].tryAcquire()) {
          // Pudo coger el palillos faltante y se pone a comer
          comer();

        } else {
          // Libreramos el palillo izquierdo al no poder coger el derecho
          palillos_semaforo[palilloIzquierdo].release();
          System.out.println("Filósofo " + idFilosofo + " está hambriento");
        }
      }
    } else {
      System.out.println("Filósofo " + idFilosofo + " está hambriento");
    }
  }

  protected void pensar() {
    long tiempo = tiempoAleatorio.nextInt(1000) + 500;

    System.out.println("Filósofo " + idFilosofo +
      " se pone a pensar " + tiempo + " millis.");

    try{
      // Simular el tiempo que tarda el filósofo en pensar,
      // entre 0.5 y 1.5 segundos:
      this.sleep(tiempo);

    } catch (InterruptedException e) {
      System.out.println("Error en el metodo pensar(): " + e.toString());
    }
  }

  @Override
  public void run() {
    // Bucle infinito donde el filósofo se pone a pensar y comer
    while (true) {
      pensar();
      colaPalillo();
    }
  }
}
