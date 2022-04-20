package com.simulacion.histogramalib.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HistogramaTest {

    List<Float> muestra;
    Random random;
    Histograma histoUniforme;
    Histograma histoExpNegativa;
    Histograma histoNormal;

    @BeforeEach
    void setUp() {
        random = new Random();
        random.setSeed(11);
        muestra = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            muestra.add(Decimal.of(random.nextFloat()).value());
        }

        histoNormal = HistogramaFactory.get(DistribucionEnum.NORMAL, 5);
        histoNormal.generarHistograma(muestra);

        histoExpNegativa = HistogramaFactory.get(DistribucionEnum.EXPONENCIAL_NEGATIVA, 5);
        histoExpNegativa.generarHistograma(muestra);

        histoUniforme = HistogramaFactory.get(DistribucionEnum.UNIFORME_AB, 5);
        histoUniforme.generarHistograma(muestra);
    }

    @Test
    public void frecuenciaAcumuladaShouldBeNTest() {
        int indexUltimoIntervalo = histoUniforme.getIntervalos().size() - 1;
        Intervalo ultimoIntervalo = histoUniforme.getIntervalos().get(indexUltimoIntervalo);

        assertEquals(muestra.size(), ultimoIntervalo.getFrecuenciaAcumulada());
    }

    @Test
    public void primerFrecuenciaIgualPrimerFrecuenciaAcumuladaTest() {
        Intervalo primerIntervalo = histoUniforme.getIntervalos().get(0);

        assertEquals(primerIntervalo.getFrecuencia(), primerIntervalo.getFrecuenciaAcumulada());
    }

    @Test
    public void proporcionAcumuladaShouldBe1Test() {
        int indexUltimoIntervalo = histoUniforme.getIntervalos().size() - 1;
        Intervalo ultimoIntervalo = histoUniforme.getIntervalos().get(indexUltimoIntervalo);

        assertEquals(1.0f, ultimoIntervalo.getProporcionAcumulada());
    }

    @Test
    public void primerFrecuenciaTest() {
        // dada la semilla sabemos de antemano cuales son los rnd, y la frecuencia de los intervalos
        Intervalo primerIntervalo = histoUniforme.getIntervalos().get(0);

        System.out.println(histoUniforme);
        assertEquals(51, primerIntervalo.getFrecuencia());
    }

    @Test
    public void primerIntervaloInfShouldBeTest() {
        Intervalo primerIntervalo = histoUniforme.getIntervalos().get(0);
        float inferior = primerIntervalo.getInferior();
        assertEquals(0.0039f, inferior);
    }

    @Test
    public void primerIntervaloSupShouldBe() {
        Intervalo primerIntervalo = histoUniforme.getIntervalos().get(0);
        float superior = primerIntervalo.getSuperior();
        assertEquals(0.2026f, superior);
    }

    @Test
    public void histogramaExponencialNegativaFrecuenciaEsperadaTest() {
        float primerFE = histoExpNegativa.getIntervalos().get(0).getFrecuenciaEsperada();
        float segundoFE = histoExpNegativa.getIntervalos().get(1).getFrecuenciaEsperada();
        assertTrue(primerFE > segundoFE);
    }

    @Test
    public void histogramaUniformeFrecuenciaEsperadaTest() {
        float primerFE = histoUniforme.getIntervalos().get(0).getFrecuenciaEsperada();
        float segundoFE = histoUniforme.getIntervalos().get(1).getFrecuenciaEsperada();
        assertEquals(primerFE, segundoFE);
    }

    @Test
    public void histogramaNormalFrecuenciaEsperadaTest() {
        float primerFE = histoNormal.getIntervalos().get(0).getFrecuenciaEsperada();
        float medioFE = histoNormal.getIntervalos().get(2).getFrecuenciaEsperada();
        float ultimoFE = histoNormal.getIntervalos().get(4).getFrecuenciaEsperada();

        assertTrue(primerFE < medioFE);
        assertTrue(ultimoFE < medioFE);
    }
}