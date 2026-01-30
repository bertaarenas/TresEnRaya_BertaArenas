import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // Configuracion inicial
        int medida = 3;
        int partidasGanar = 1;

        // Array nombres jugadores
        String[] nombres = new String[2];

        int victoriasP1 = 0;
        int victoriasP2 = 0;

        boolean salir = false;
        while (!salir) {
            // Menu
            System.out.println("Bienvenidos al Tres en raya!");
            System.out.println("1. Jugar");
            System.out.println("2. Configuración");
            System.out.println("3. Salir");
            System.out.println("Escoja un número según su elección: ");

            int eleccion = scanner.nextInt();

            int rondasGanadasP1 = 0;
            int rondasGanadasP2 = 0;

            switch (eleccion) {
                case 1:
                    // nombres de los jugadores
                    for (int i = 0; i < nombres.length; i++) {
                        System.out.println("Ingresa el nombre del jugador " + i + ": ");
                        nombres[i] = scanner.next();
                    }

                    while (rondasGanadasP1 < partidasGanar && rondasGanadasP2 < partidasGanar) {
                        // Medida de la matriz
                        char[][] tablero = new char[medida][medida];
                        // Tablero
                        for (int i = 0; i < medida; i++) {
                            for (int j = 0; j < medida; j++) {
                                tablero[i][j] = '-';
                            }
                        }

                        System.out.println("Genial! A continuación se eligirá aleatorismente el jugador que comenzara.");

                        boolean ganador = false;
                        int jugadas = 0;
                        int turno = rand.nextInt(2);
                        String jugadorComienza = nombres[turno];

                        while (!ganador && jugadas < (medida * medida)) {

                            System.out.println("Tablero:");
                            for (int i = 0; i < medida; i++) {
                                for (int j = 0; j < medida; j++) {
                                    System.out.print(" " + tablero[i][j] + " ");
                                }
                                System.out.println();
                            }
                            // le pide al usuario fila columna
                            System.out.println("Turno de: " + jugadorComienza);
                            System.out.println("Fila (0-" + (medida - 1) + "): ");
                            int fila = scanner.nextInt();
                            System.out.println("Columna (0-" + (medida - 1) + "): ");
                            int columna = scanner.nextInt();

                            if (tablero[fila][columna] == '-') {
                                String ganadorActual = jugadorComienza;

                                if (jugadorComienza.equals(nombres[0])) {
                                    tablero[fila][columna] = 'X';
                                    jugadorComienza = nombres[1];
                                } else {
                                    tablero[fila][columna] = 'O';
                                    jugadorComienza = nombres[0];
                                }
                                jugadas++;

                                // Mira si hay un ganador
                                // FILAS
                                for (int i = 0; i < medida; i++) {
                                    int cuenta = 0;
                                    for (int j = 0; j < medida; j++) {
                                        if (tablero[i][j] != '-' && tablero[i][j] == tablero[i][0]) cuenta++;
                                    }
                                    if (cuenta == medida) ganador = true;
                                }
                                // COLUMNAS
                                for (int i = 0; i < medida; i++) {
                                    int cuenta = 0;
                                    for (int j = 0; j < medida; j++) {
                                        if (tablero[j][i] != '-' && tablero[j][i] == tablero[0][i]) cuenta++;
                                    }
                                    if (cuenta == medida) ganador = true;
                                }
                                // DIAGONAL
                                int d1 = 0, d2 = 0;
                                for (int i = 0; i < medida; i++) {
                                    if (tablero[i][i] != '-' && tablero[i][i] == tablero[0][0]) d1++;
                                    if (tablero[i][medida - 1 - i] != '-' && tablero[i][medida - 1 - i] == tablero[0][medida - 1]) d2++;
                                }
                                if (d1 == medida || d2 == medida) ganador = true;

                                if (ganador) {
                                    System.out.println("¡Felicidades " + ganadorActual + "! Has ganado.");
                                    if (ganadorActual.equals(nombres[0])) rondasGanadasP1++;
                                    else rondasGanadasP2++;
                                }
                            } else {
                                System.out.println("La casilla esta ocupada.");
                            }
                        }
                    }
                    if (rondasGanadasP1 > rondasGanadasP2) victoriasP1++;
                    else victoriasP2++;
                    break;

                case 2:
                    System.out.println("Bienvenido a configuración.");
                    System.out.println("Introduzca el tamaño del tablero (3, 4, 8...): ");
                    medida = scanner.nextInt();
                    System.out.println("Al mejor de?");
                    partidasGanar = scanner.nextInt();
                    System.out.println("Genial!");
                    break;

                case 3:
                    System.out.println("Has salido. Victorias totales: " + nombres[0] + ": " + victoriasP1 + " " + nombres[1] + ": " + victoriasP2);
                    salir = true;
                    break;
            }
        }
    }
}