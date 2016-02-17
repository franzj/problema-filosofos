
package src.filosofos;

class Main {
  public static final String solucion = "serial";

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
    // Array de filósofos
    Filosofo filosofo[] = new Filosofo[nroFilosofos];

    // Creamos alos filósofos del comedor con su respectivos palillos
    for (int i = 0; i < nroFilosofos; i++) {
      filosofo[i] = new Filosofo(i, palillos);
    }

    // Iniciando la cena
    while (true) {
      for (int i = 0; i < nroFilosofos; i++) {
        filosofo[i].comer();
      }
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
