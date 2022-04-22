package com.simulacion.histogramalib.core.poisson;

import com.simulacion.histogramalib.core.Intervalo;

public class IntervaloPoisson extends Intervalo {
    private final int intervalo;
    private final int valor;
    private final int frecuenciaEsperadaPoisson;
    private int frecuencia;

    public IntervaloPoisson(int intervalo, int valor, int frecuenciaEsperadaPoisson) {
        super(0, 0 ,0 ,0 ,0);
        this.intervalo = intervalo;
        this.valor = valor;
        this.frecuenciaEsperadaPoisson = frecuenciaEsperadaPoisson;
    }

    public void registrarObservacion() {
        this.frecuencia++;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public int getValor() {
        return valor;
    }

    public int getFrecuenciaEsperadaPoisson() {
        return frecuenciaEsperadaPoisson;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    @Override
    public String toString() {
        return "IntervaloPoisson{" +
                "intervalo=" + intervalo +
                ", valor=" + valor +
                ", frecuenciaEsperada=" + frecuenciaEsperadaPoisson +
                ", frecuencia=" + frecuencia +
                '}';
    }
}
