package com.simulacion.histogramalib.core.normal;

import com.simulacion.histogramalib.core.Decimal;
import com.simulacion.histogramalib.core.Histograma;

public class HistogramaNormal extends Histograma {

    public HistogramaNormal(int cantidadIntervalos) {
        super(cantidadIntervalos);
    }

    @Override
    public float calcularFrecuenciaEsperada(int n, int intervalos, float inf, float sup, float marcaClase, float media, float desvEstandar) {
        float p = (float) (((Math.exp(-0.5*(Math.pow((marcaClase-media)/desvEstandar, 2))))/(desvEstandar*Math.sqrt(2*Math.PI)))*(sup-inf));
        return Decimal.of(p * n).value();
    }
}
