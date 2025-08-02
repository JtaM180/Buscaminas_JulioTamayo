package consolaBuscaminas;

import java.io.Serializable;

public class Casilla implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean mina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAlrededor;

    public Casilla() {
        this.mina = false;
        this.descubierta = false;
        this.marcada = false;
        this.minasAlrededor = 0;
    }

    public boolean tieneMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public void descubrir() {
        this.descubierta = true;
    }

    public boolean estaMarcada() {
        return marcada;
    }

    public void toggleMarca() {
        this.marcada = !this.marcada;
    }

    public int getMinasAlrededor() {
        return minasAlrededor;
    }

    public void setMinasAlrededor(int minas) {
        this.minasAlrededor = minas;
    }
}
