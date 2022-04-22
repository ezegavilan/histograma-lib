package com.simulacion.histogramalib.pruebabondad.chicuadrado;

import com.simulacion.histogramalib.core.Decimal;
import com.simulacion.histogramalib.core.DistribucionEnum;
import com.simulacion.histogramalib.core.Histograma;
import com.simulacion.histogramalib.core.HistogramaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HistogramaChiCuadradoTest {
    Histograma histogramaUniformeAB;
    Histograma histogramaNormal;

    @BeforeEach
    void setUp() {
        Random random = new Random();
        random.setSeed(111);
        List<Float> muestra = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            muestra.add(Decimal.of(random.nextFloat()).value());
        }

        histogramaUniformeAB = HistogramaFactory.get(DistribucionEnum.UNIFORME_AB, 5);
        histogramaUniformeAB.generarHistograma(muestra);

        histogramaNormal = HistogramaFactory.get(DistribucionEnum.NORMAL, 5);
        histogramaNormal.generarHistograma(muestra);
    }

    @Test
    public void pasaPruebaBondadUniformeTest() {
        HistogramaChiCuadrado histogramaChiCuadrado = new HistogramaChiCuadrado();
        histogramaChiCuadrado.generarHistogramaChiCuadrado(histogramaUniformeAB);

        float chiCalculado = histogramaChiCuadrado.getChiCuadradoCalculado();
        int gradosLibertad = histogramaChiCuadrado.calcularGradosLibertad(DistribucionEnum.UNIFORME_AB);

        PruebaBondadChiCuadrado pruebaBondadChiCuadrado = new PruebaBondadChiCuadrado();
        assertTrue(pruebaBondadChiCuadrado.validarHipotesis(chiCalculado, gradosLibertad));
    }

    @Test
    public void noPasaPruebaBondadNormalTest() {
        HistogramaChiCuadrado histogramaChiCuadrado = new HistogramaChiCuadrado();
        histogramaChiCuadrado.generarHistogramaChiCuadrado(histogramaNormal);

        float chiCalculado = histogramaChiCuadrado.getChiCuadradoCalculado();
        int gradosLibertad = histogramaChiCuadrado.calcularGradosLibertad(DistribucionEnum.NORMAL);

        PruebaBondadChiCuadrado pruebaBondadChiCuadrado = new PruebaBondadChiCuadrado();
        assertFalse(pruebaBondadChiCuadrado.validarHipotesis(chiCalculado, gradosLibertad));
    }
}