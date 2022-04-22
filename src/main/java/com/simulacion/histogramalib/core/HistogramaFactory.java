package com.simulacion.histogramalib.core;

import com.simulacion.histogramalib.core.eponencialnegativa.HistogramaExponencialNegativa;
import com.simulacion.histogramalib.core.normal.HistogramaNormal;
import com.simulacion.histogramalib.core.poisson.HistogramaPoisson;
import com.simulacion.histogramalib.core.uniformeab.HistogramaUniformeAB;

public class HistogramaFactory {

    public static Histograma get(DistribucionEnum distribucion, int intervalos) {
        if (distribucion == DistribucionEnum.UNIFORME_AB) {
            return new HistogramaUniformeAB(intervalos);
        } else if (distribucion == DistribucionEnum.EXPONENCIAL_NEGATIVA) {
            return new HistogramaExponencialNegativa(intervalos);
        } else if (distribucion == DistribucionEnum.NORMAL) {
            return new HistogramaNormal(intervalos);
        } else if (distribucion == DistribucionEnum.POISSON) {
            return new HistogramaPoisson(intervalos);
        } else {
            throw new RuntimeException("Histograma no implementado");
        }
    }
}
