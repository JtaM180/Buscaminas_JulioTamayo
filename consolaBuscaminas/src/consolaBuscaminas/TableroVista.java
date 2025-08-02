package consolaBuscaminas;

public class TableroVista {
    public void mostrar(Tablero tablero) {
        System.out.println("    1 2 3 4 5 6 7 8 9 10");
        for (int f = 0; f < tablero.getFilas(); f++) {
            char filaLetra = (char) ('A' + f);
            System.out.print(filaLetra + "   ");
            for (int c = 0; c < tablero.getColumnas(); c++) {
                Casilla casilla = tablero.getCasilla(f, c);
                if (casilla.estaMarcada()) {
                    System.out.print("F ");
                } else if (!casilla.estaDescubierta()) {
                    System.out.print("■ ");
                } else if (casilla.tieneMina()) {
                    System.out.print("X ");
                } else if (casilla.getMinasAlrededor() == 0) {
                    System.out.print("V ");
                } else {
                    System.out.print(casilla.getMinasAlrededor() + " ");
                }
            }
            System.out.println();
        }
    }
}
