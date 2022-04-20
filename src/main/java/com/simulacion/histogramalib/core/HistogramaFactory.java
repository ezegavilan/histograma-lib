package com.simulacion.histogramalib.core;

import com.simulacion.histogramalib.core.eponencialnegativa.HistogramaExponencialNegativa;
import com.simulacion.histogramalib.core.uniformeab.HistogramaUniformeAB;

public class HistogramaFactory {

    public static Histograma get(DistribucionEnum distribucion, int intervalos) {
        if (distribucion == DistribucionEnum.UNIFORME_AB) {
            return new HistogramaUniformeAB(intervalos);
        } else if (distribucion == DistribucionEnum.EXPONENCIAL_NEGATIVA) {
            return new HistogramaExponencialNegativa(intervalos);
        } else {
            throw new RuntimeException("Histograma no implementado");
        }
    }
}
