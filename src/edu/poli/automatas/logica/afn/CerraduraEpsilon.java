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

    public String getNombreEstado(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for(int i = 0; i < estadosDeArranque.size() -2 ; i++){
            buffer.append(estadosDeArranque.get(i).getNombre());
            buffer.append(",");
        }
        buffer.append(estadosDeArranque.get(estadosDeArranque.size()-1));
        buffer.append("}u");
        buffer.append("{");
        for(int i = 0; i < estadosConEpsilon.size() -2 ; i++){
            buffer.append(estadosConEpsilon.get(i).getNombre());
            buffer.append(",");
        }
        buffer.append(estadosConEpsilon.get(estadosConEpsilon.size()-1));
        buffer.append("}");
        return buffer.toString();
    }
}
