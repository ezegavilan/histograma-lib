package com.simulacion.histogramalib;

import com.simulacion.histogramalib.core.Histograma;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ezegavilan95
 * Clase Placeholder para la aplicación Main
 */
public class Application {
    public static void main(String[] args) {
        List<Float> muestra = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            muestra.add((float) Math.random());
        }

        Histograma histograma = new Histograma(5);
        histograma.generarHistograma(muestra);

        System.out.println(histograma);
    }
}
