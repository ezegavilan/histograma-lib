package com.simulacion.histogramalib.pruebabondad.chicuadrado;

import com.simulacion.histogramalib.core.DistribucionEnum;
import com.simulacion.histogramalib.core.Histograma;
import com.simulacion.histogramalib.core.Intervalo;
import com.simulacion.histogramalib.core.poisson.HistogramaPoisson;
import com.simulacion.histogramalib.core.poisson.IntervaloPoisson;

import java.util.ArrayList;
import java.util.List;

public class HistogramaChiCuadrado {
    private final List<IntervaloChiCuadrado> intervalos;
    private int cantidadIntervalos;

    public HistogramaChiCuadrado() {
        this.intervalos = new ArrayList<>();
    }

    public void generarHistogramaChiCuadrado(Histograma histograma) {
        this.cantidadIntervalos = histograma.getCantidadIntervalos();

        List<Intervalo> intervalosHistograma = histograma.getIntervalos();
        Intervalo currentIntervalo;
        IntervaloChiCuadrado intervaloChiCuadrado;

        int intervalo, frecuenciaObservada;
        float frecuenciaEsperada;
        float inferior, superior;
        float chiCuadradoAcumuladoActual = 0f;
        intervalo = 1;
        for (int i = 0; i < intervalosHistograma.size(); i++) {
            currentIntervalo = intervalosHistograma.get(i);
            frecuenciaEsperada = currentIntervalo.getFrecuenciaEsperada();
            if (histograma instanceof HistogramaPoisson) frecuenciaEsperada = ((IntervaloPoisson) currentIntervalo).getFrecuenciaEsperadaPoisson();
            frecuenciaObservada = currentIntervalo.getFrecuencia();
            inferior = currentIntervalo.getInferior();
            superior = currentIntervalo.getSuperior();


            // si fe es menor a 5, compactamos los intervalos hasta que la suma de fe sea >= 5
            // ajustamos el inf y sup de los intervalos
            int cont = i;
            while (cont < intervalosHistograma.size() && frecuenciaEsperada < 5) {
                cont++;
                if (cont >= intervalosHistograma.size()) break;
                frecuenciaEsperada += intervalosHistograma.get(cont).getFrecuenciaEsperada();
                frecuenciaObservada += intervalosHistograma.get(cont).getFrecuencia();
                superior = intervalosHistograma.get(cont).getSuperior();
                i = cont;

                if (frecuenciaEsperada >= 5 && intervalosHistograma.get(cont + 1).getFrecuenciaEsperada() >= 5) break;
            }

            intervaloChiCuadrado = new IntervaloChiCuadrado(intervalo, inferior, superior, frecuenciaObservada, frecuenciaEsperada);

            intervaloChiCuadrado.registrarChiCuadrado();
            chiCuadradoAcumuladoActual += intervaloChiCuadrado.getChiCuadrado();

            intervaloChiCuadrado.registrarChiCuadradoAcumulado(chiCuadradoAcumuladoActual);
            intervalos.add(intervaloChiCuadrado);
            intervalo++;
        }
        this.cantidadIntervalos = intervalo - 1;
    }

    public float getChiCuadradoCalculado() {
        int ultimoIndice = intervalos.size() - 1;
        return intervalos.get(ultimoIndice).getChiCuadradoAcumulado();
    }

    public int calcularGradosLibertad(DistribucionEnum distribucion) {
        int m;
        switch (distribucion) {
            case UNIFORME_AB:
                m = 0;
                break;
            case EXPONENCIAL_NEGATIVA:
                m = 1;
                break;
            case POISSON:
                m = 1;
                break;
            case NORMAL:
                m = 2;
                break;
            default:
                m = -1;
                break;
        }
        return this.cantidadIntervalos - 1 - m;
    }

    @Override
    public String toString() {
        return "HistogramaChiCuadrado{" +
                "intervalos=" + intervalos +
                ", cantidadIntervalos=" + cantidadIntervalos +
                '}';
    }
}
