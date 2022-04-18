package com.simulacion.histogramalib.core;

import java.util.ArrayList;
import java.util.List;

public class Histograma {
    private final List<Intervalo> intervalos;
    private final int cantidadIntervalos;

    public Histograma(int cantidadIntervalos) {
        this.intervalos = new ArrayList<>();
        this.cantidadIntervalos = cantidadIntervalos;
    }

    public void generarHistograma(List<Float> muestra) {
        int n = muestra.size();
        crearIntervalos(cantidadIntervalos, n);

        cargarObservaciones(muestra);
    }

    private void crearIntervalos(int cantidadIntervalos, int n) {
        float tamIntervalo = 1F/cantidadIntervalos;
        float frecuenciaEsperada = this.calcularFrecuenciaEsperada(n, cantidadIntervalos);

        Intervalo intervaloNuevo;
        int intervalo;
        float inf, sup, marcaClase;

        inf = 0.0f;
        sup = tamIntervalo - 0.00001f;
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
            marcaClase = calcularMarcaClase(inf ,sup);

            intervaloNuevo = new Intervalo(intervalo, inf, sup, marcaClase, frecuenciaEsperada);

            intervalos.add(intervaloNuevo);
        }
    }

    private float calcularFrecuenciaEsperada(int n, int k) {
        return (float) n/k;
    }

    private void cargarObservaciones(List<Float> muestra) {
        Intervalo intervaloActual;

        int totalObservados = muestra.size();
        float rnd, inf, sup;

        int frAcumuladaActual = 0;
        float propAcumuladaActual = 0f;
        for (int i = 0; i < intervalos.size(); i++) {
            intervaloActual = intervalos.get(i);
            for (int j = 0; j < muestra.size(); j++) {
                rnd = muestra.get(j);
                inf = intervaloActual.getInferior();
                sup = intervaloActual.getSuperior();

                if (rnd >= inf && rnd <= sup && rnd != 1) {
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

    public List<Intervalo> getIntervalos() {
        return intervalos;
    }

    public int getCantidadIntervalos() {
        return cantidadIntervalos;
    }

    private float calcularMarcaClase(float inf, float sup) {
        return (inf + sup) / 2;
    }

    private float calcularLimiteInferior(float infAnterior, float rango) {
        return infAnterior + rango;
    }

    private float calcularLimiteSuperior(float infActual, float rango) {
        return infActual + rango - 0.00001f;
    }

    @Override
    public String toString() {
        return "Histograma{" +
                "intervalos=" + intervalos +
                ", cantidadIntervalos=" + cantidadIntervalos +
                '}';
    }
}
