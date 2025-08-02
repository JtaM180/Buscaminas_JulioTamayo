package consolaBuscaminas;

import java.util.Random;

public class Tablero {
    private final int filas = 10;
    private final int columnas = 10;
    private final int minas = 10;
    private Casilla[][] casillas = new Casilla[filas][columnas];

    public Tablero() {
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla();
            }
        }
        colocarMinas();
        contarMinas();
    }

    private void colocarMinas() {
        Random rand = new Random();
        int colocadas = 0;
        while (colocadas < minas) {
            int f = rand.nextInt(filas);
            int c = rand.nextInt(columnas);
            if (!casillas[f][c].tieneMina()) {
                casillas[f][c].setMina(true);
                colocadas++;
            }
        }
    }

    private void contarMinas() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (casillas[f][c].tieneMina()) continue;
                int contador = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int nf = f + i;
                        int nc = c + j;
                        if (nf >= 0 && nf < filas && nc >= 0 && nc < columnas) {
                            if (casillas[nf][nc].tieneMina()) contador++;
                        }
                    }
                }
                casillas[f][c].setMinasAlrededor(contador);
            }
        }
    }

    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public boolean haGanado() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla c = casillas[i][j];
                if (!c.tieneMina() && !c.estaDescubierta()) return false;
            }
        }
        return true;
    }

    public void expandir(int fila, int columna) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nf = fila + i;
                int nc = columna + j;
                if (nf >= 0 && nf < filas && nc >= 0 && nc < columnas) {
                    Casilla casilla = casillas[nf][nc];
                    if (!casilla.estaDescubierta() && !casilla.estaMarcada()) {
                        casilla.descubrir();
                        if (casilla.getMinasAlrededor() == 0) {
                            expandir(nf, nc);
                        }
                    }
                }
            }
        }
    }
}
