package edu.poli.automatas.logica.afn;

/**
 * Created by juanmartinez on 5/06/15.
 */
public class TransicionAFN {

    private String simbolo;
    private EstadoAFN estado;

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public EstadoAFN getEstado() {
        return estado;
    }

    public void setEstado(EstadoAFN estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Transicion{" +
                "simbolo='" + simbolo + '\'' +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransicionAFN)) return false;

        TransicionAFN that = (TransicionAFN) o;

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
