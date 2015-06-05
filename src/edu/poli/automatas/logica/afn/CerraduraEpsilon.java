package edu.poli.automatas.logica.afn;

import java.util.List;

/**
 * Created by juanmartinez on 5/06/15.
 */
public class CerraduraEpsilon {

    private List<EstadoAFN> estadosDeArranque;
    private List<EstadoAFN> estadosConEpsilon;

    public List<EstadoAFN> getEstadosConEpsilon() {
        return estadosConEpsilon;
    }

    public void setEstadosConEpsilon(List<EstadoAFN> estadosConEpsilon) {
        this.estadosConEpsilon = estadosConEpsilon;
    }

    public List<EstadoAFN> getEstadosDeArranque() {
        return estadosDeArranque;
    }

    public void setEstadosDeArranque(List<EstadoAFN> estadosDeArranque) {
        this.estadosDeArranque = estadosDeArranque;
    }
}
