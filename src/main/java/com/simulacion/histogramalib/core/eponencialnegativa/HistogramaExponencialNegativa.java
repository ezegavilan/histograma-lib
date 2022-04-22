package com.simulacion.histogramalib.core.eponencialnegativa;

import com.simulacion.histogramalib.core.Decimal;
import com.simulacion.histogramalib.core.Histograma;

public class HistogramaExponencialNegativa extends Histograma {

    public HistogramaExponencialNegativa(int cantidadIntervalos) {
        super(cantidadIntervalos);
    }

    @Override
    public float calcularFrecuenciaEsperada(int n, int intervalos, float inf, float sup, float marcaClase, float media, float desvEstandar) {
        float lambda = 1f/media;
        float pac = (float) ((1 - Math.exp(-lambda * sup)) - (1 - Math.exp(-lambda * inf)));
        return Decimal.of(pac * n).value();
    }
}
