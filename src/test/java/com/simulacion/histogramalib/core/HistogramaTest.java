package com.simulacion.histogramalib.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistogramaTest {

    List<Float> muestra;
    Random random;
    Histograma histograma;

    @BeforeEach
    void setUp() {
        random = new Random();
        random.setSeed(11);
        muestra = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            muestra.add(Decimal.of(random.nextFloat()).value());
        }
        histograma = new Histograma(5);
        histograma.generarHistograma(muestra);

    }

    @Test
    public void frecuenciaAcumuladaShouldBeNTest() {
        int indexUltimoIntervalo = histograma.getIntervalos().size() - 1;
        Intervalo ultimoIntervalo = histograma.getIntervalos().get(indexUltimoIntervalo);

        assertEquals(muestra.size(), ultimoIntervalo.getFrecuenciaAcumulada());
    }

    @Test
    public void primerFrecuenciaIgualPrimerFrecuenciaAcumuladaTest() {
        Intervalo primerIntervalo = histograma.getIntervalos().get(0);

        assertEquals(primerIntervalo.getFrecuencia(), primerIntervalo.getFrecuenciaAcumulada());
    }

    @Test
    public void proporcionAcumuladaShouldBe1Test() {
        int indexUltimoIntervalo = histograma.getIntervalos().size() - 1;
        Intervalo ultimoIntervalo = histograma.getIntervalos().get(indexUltimoIntervalo);

        assertEquals(1.0f, ultimoIntervalo.getProporcionAcumulada());
    }

    @Test
    public void primerFrecuenciaTest() {
        // dada la semilla sabemos de antemano cuales son los rnd, y la frecuencia de los intervalos
        Intervalo primerIntervalo = histograma.getIntervalos().get(0);

        System.out.println(histograma);
        assertEquals(51, primerIntervalo.getFrecuencia());
    }

    @Test
    public void primerIntervaloInfShouldBeTest() {
        Intervalo primerIntervalo = histograma.getIntervalos().get(0);
        float inferior = primerIntervalo.getInferior();
        assertEquals(0.0039f, inferior);
    }

    @Test
    public void primerIntervaloSupShouldBe() {
        Intervalo primerIntervalo = histograma.getIntervalos().get(0);
        float superior = primerIntervalo.getSuperior();
        assertEquals(0.2026f, superior);
    }
}