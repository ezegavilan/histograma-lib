package com.simulacion.histogramalib.core.poisson;

import com.simulacion.histogramalib.core.Histograma;

import java.util.List;

public class HistogramaPoisson extends Histograma {

    public HistogramaPoisson(int cantidadIntervalos) {
        super(cantidadIntervalos);
    }

    @Override
    public float calcularFrecuenciaEsperada(int n, int intervalos, float inf, float sup, float marcaClase, float media, float desvEstandar) {
        // media = lambda / valor = marcaClase (para simplificar)
        int valor = (int) marcaClase;
        float lambda = media;
        double factorial = this.factorial(valor);
        float p = (float) ((float) (Math.pow(lambda, valor) * Math.exp(-lambda)) / factorial);

        float fe = p * n;
        return Math.round(fe);
    }

    @Override
    protected void crearIntervalos(List<Float> muestra, int n) {
        int intervalo, frecuenciaEsperada;
        float valor;
        IntervaloPoisson nuevoIntervalo;
        int cantIntervalos = calcularCantidadIntervalos(muestra);

        valor = this.min(muestra);
        for (int i = 0; i < cantIntervalos; i++) {
            intervalo = i + 1;

            frecuenciaEsperada = (int) this.calcularFrecuenciaEsperada(n, cantIntervalos, 0f, 0f, valor, this.calcularMedia(muestra), 0f);
            nuevoIntervalo = new IntervaloPoisson(intervalo, (int) valor, frecuenciaEsperada);
            this.intervalos.add(nuevoIntervalo);
            valor++;
        }
    }

    @Override
    protected void cargarObservaciones(List<Float> muestra) {
        IntervaloPoisson intervaloActual;
        int n = muestra.size();
        int valor, observado;

        for (int i = 0; i < intervalos.size(); i++) {
            intervaloActual = (IntervaloPoisson) intervalos.get(i);
            for (int j = 0; j < n; j++) {
                observado = muestra.get(j).intValue();
                valor = intervaloActual.getValor();

                if (observado == valor) {
                    intervaloActual.registrarObservacion();
                }
            }
        }
    }

    private double factorial(int n) {
        double factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial = factorial * i;
        }
        return factorial;
    }

    private int calcularCantidadIntervalos(List<Float> muestra) {
        int min = (int) this.min(muestra);
        int max = (int) this.max(muestra);
        return max - min + 1;
    }

}
