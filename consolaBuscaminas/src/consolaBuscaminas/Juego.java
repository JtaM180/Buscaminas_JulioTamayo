package consolaBuscaminas;

import java.util.Scanner;

public class Juego {
    private Tablero tablero;
    private TableroVista vista;
    private Scanner scanner;

    public Juego() {
        tablero = new Tablero();
        vista = new TableroVista();
        scanner = new Scanner(System.in);
    }

    public void jugar() {
        while (true) {
            vista.mostrar(tablero);
            System.out.print("Ingrese coordenada (ej: A5) o F A5 para marcar: ");
            String input = scanner.nextLine().toUpperCase();

            try {
                boolean marcar = input.startsWith("F ");
                if (marcar) input = input.substring(2).trim();

                if (input.length() < 2) throw new EntradaInvalidaException("Entrada inválida.");
                char letra = input.charAt(0);
                int fila = letra - 'A';
                int columna = Integer.parseInt(input.substring(1)) - 1;

                if (fila < 0 || fila >= 10 || columna < 0 || columna >= 10) {
                    throw new EntradaInvalidaException("Coordenadas fuera de rango.");
                }

                Casilla c = tablero.getCasilla(fila, columna);
                if (marcar) {
                    c.toggleMarca();
                } else {
                    if (c.estaDescubierta()) throw new CasillaYaDescubiertaException("Ya está descubierta.");
                    c.descubrir();
                    if (c.tieneMina()) {
                        vista.mostrar(tablero);
                        System.out.println("¡Perdiste!");
                        return;
                    } else if (c.getMinasAlrededor() == 0) {
                        tablero.expandir(fila, columna);
                    }
                }

                if (tablero.haGanado()) {
                    vista.mostrar(tablero);
                    System.out.println("¡Ganaste!");
                    return;
                }

            } catch (EntradaInvalidaException | CasillaYaDescubiertaException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Juego().jugar();
    }
}
