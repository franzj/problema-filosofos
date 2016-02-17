
package src.filosofos;

import java.util.Random;

public class Filosofo {

  private final int idFilosofo;
  private final int palilloIzquierdo;
  private final int palilloDerecho;
  private final Random tiempoAleatorio = new Random();

  public Filosofo(int idFilosofo, int[][] palillosFilosofo) {
    this.idFilosofo = idFilosofo;
    this.palilloIzquierdo = palillosFilosofo[idFilosofo][0];
    this.palilloDerecho = palillosFilosofo[idFilosofo][1];
  }

  public void comer() {
    try {
      // Simular el tiempo que tarda el filósofo en comer,
      // entre 0.5 y 2.5 segundos:
      long tiempo = tiempoAleatorio.nextInt(2000) + 500;

      System.out.println("Filósofo " + idFilosofo +
        " esta comiendo en " + tiempo + " millis");

      Thread.sleep(tiempo);

      System.out.println("Filósofo " + idFilosofo +
        " ha terminado de comer. Libera los palillos " +
        palilloIzquierdo + " y " + palilloDerecho);

      pensar();

    } catch (InterruptedException ex) {
      System.out.println("Error : " + ex.toString());
    }
  }

  private void pensar() {
    System.out.println("Filósofo " + idFilosofo + " se pone a pensar.");
  }
}
