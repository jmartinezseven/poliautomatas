package edu.poli.automatas.logica;

public class Transicion {

    private String simbolo;
    private Estado estado;

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transicion)) return false;

        Transicion that = (Transicion) o;

        if (!estado.equals(that.estado)) return false;
        if (!simbolo.equals(that.simbolo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = simbolo.hashCode();
        result = 31 * result + estado.hashCode();
        return result;
    }
}
