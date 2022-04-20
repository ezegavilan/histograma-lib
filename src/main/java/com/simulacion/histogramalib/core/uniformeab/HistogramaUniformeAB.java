package com.simulacion.histogramalib.core.uniformeab;

import com.simulacion.histogramalib.core.Decimal;
import com.simulacion.histogramalib.core.Histograma;

public class HistogramaUniformeAB extends Histograma {

    public HistogramaUniformeAB(int cantidadIntervalos) {
        super(cantidadIntervalos);
    }

    @Override
    public float calcularFrecuenciaEsperada(int n, int intervalos, float inf, float sup, float media) {
        return Decimal.of((float) n/intervalos).value();
    }
}
