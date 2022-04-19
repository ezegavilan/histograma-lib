package com.simulacion.histogramalib;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author ezegavilan95
 * Clase Placeholder para la aplicación Main
 */
public class Application {
    public static void main(String[] args) {
        /*List<Float> muestra = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            muestra.add((float) Math.random());
        }

        Histograma histograma = new Histograma(5);
        histograma.generarHistograma(muestra);

        System.out.println(histograma);*/
        float fl = 0.12165618f;
        System.out.println(fl);

        BigDecimal flBd = new BigDecimal(fl).setScale(4, RoundingMode.HALF_EVEN);
        System.out.println(flBd.floatValue());
    }
}
