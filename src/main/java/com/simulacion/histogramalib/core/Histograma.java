package com.simulacion.histogramalib.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Histograma {
    private final List<Intervalo> intervalos;
    private final int cantidadIntervalos;
    private final Decimal DIFF = Decimal.of(0.0001f);

    public Histograma(int cantidadIntervalos) {
        this.intervalos = new ArrayList<>();
        this.cantidadIntervalos = cantidadIntervalos;
    }

    public void generarHistograma(List<Float> muestra) {
        int n = muestra.size();
        crearIntervalos(muestra, n);

        cargarObservaciones(muestra);
    }

    private void crearIntervalos(List<Float> muestra, int n) {
        float tamIntervalo = this.calcularRangoIntervalos(muestra, cantidadIntervalos);
        float frecuenciaEsperada = this.calcularFrecuenciaEsperada(n, cantidadIntervalos);

        Intervalo intervaloNuevo;
        int intervalo;
        float inf, sup, marcaClase;

        inf = this.min(muestra);
        sup = inf + tamIntervalo - DIFF.value();
        for (int i = 0; i < cantidadIntervalos; i++) {
            intervalo = i + 1;
            if (intervalo == 1) {
                marcaClase = calcularMarcaClase(inf, sup);

                intervaloNuevo = new Intervalo(intervalo, inf, sup, marcaClase, frecuenciaEsperada);
                intervalos.add(intervaloNuevo);
                continue;
            }

            inf = calcularLimiteInferior(inf, tamIntervalo);
            sup = calcularLimiteSuperior(inf, tamIntervalo);
            marcaClase = calcularMarcaClase(inf, sup);

            if (intervalo == cantidadIntervalos) {
                sup = this.max(muestra);
            }
            intervaloNuevo = new Intervalo(intervalo, inf, sup, marcaClase, frecuenciaEsperada);

            intervalos.add(intervaloNuevo);
        }
    }

    private void cargarObservaciones(List<Float> muestra) {
        Intervalo intervaloActual;

        int totalObservados = muestra.size();
        float rnd, inf, sup;

        float ultimoSuperior = intervalos.get(intervalos.size()-1).getSuperior() + DIFF.value();
        int frAcumuladaActual = 0;
        float propAcumuladaActual = 0f;
        for (int i = 0; i < intervalos.size(); i++) {
            intervaloActual = intervalos.get(i);
            for (int j = 0; j < muestra.size(); j++) {
                rnd = muestra.get(j);
                inf = intervaloActual.getInferior();
                sup = intervaloActual.getSuperior();

                if (rnd >= inf && rnd <= sup && rnd != ultimoSuperior) {
                    intervaloActual.registrarObservacion();
                }
            }

            frAcumuladaActual += intervaloActual.getFrecuencia();
            intervaloActual.registrarProporcion(totalObservados);

            propAcumuladaActual += intervaloActual.getProporcion();
            intervaloActual.registrarFrecuenciaAcumulada(frAcumuladaActual);
            intervaloActual.registrarProporcionAcumulada(propAcumuladaActual);
        }
    }

    private float calcularFrecuenciaEsperada(int n, int k) {
        return Decimal.of((float) n/k).value();
    }

    private float calcularRangoIntervalos(List<Float> muestra, int cantidadIntervalos) {
        float min, max, diff;
        max = this.max(muestra);
        min = this.min(muestra);
        diff = max - min;

        return Decimal.of(diff / cantidadIntervalos).value();
    }

    private float max(List<Float> muestra) {
        return Decimal.of(Collections.max(muestra)).value();
    }

    private float min(List<Float> muestra) {
        return Decimal.of(Collections.min(muestra)).value();
    }

    public List<Intervalo> getIntervalos() {
        return intervalos;
    }

    private float calcularMarcaClase(float inf, float sup) {
        return Decimal.of((inf + sup) / 2).value();
    }

    private float calcularLimiteInferior(float infAnterior, float rango) {
        return Decimal.of(infAnterior + rango).value();
    }

    private float calcularLimiteSuperior(float infActual, float rango) {
        return Decimal.of(infActual + rango - DIFF.value()).value();
    }

    @Override
    public String toString() {
        return "Histograma{" +
                "intervalos=" + intervalos +
                ", cantidadIntervalos=" + cantidadIntervalos +
                '}';
    }
}
