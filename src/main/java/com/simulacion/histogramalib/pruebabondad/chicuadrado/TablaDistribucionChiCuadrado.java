package com.simulacion.histogramalib.pruebabondad.chicuadrado;

import java.util.HashMap;
import java.util.Map;

public class TablaDistribucionChiCuadrado {
    private final Map<Integer, Float> tabla;

    public TablaDistribucionChiCuadrado() {
        this.tabla = new HashMap<>();

        tabla.put(-1, 999999f);
        tabla.put(1, 7.60f);
        tabla.put(2, 10.59f);
        tabla.put(3, 12.92f);
        tabla.put(4, 14.82f);
        tabla.put(5, 16.76f);
        tabla.put(6, 18.55f);
        tabla.put(7, 20.27f);
        tabla.put(8, 21.94f);
        tabla.put(9, 23.56f);
        tabla.put(10, 25.15f);
        tabla.put(11, 26.71f);
        tabla.put(12, 28.25f);
        tabla.put(13, 29.88f);
        tabla.put(14, 31.38f);
        tabla.put(15, 32.86f);
    }

    public float valorChiTabulado(int gradosLibertad) {
        if (gradosLibertad > 15) {
            return -1F;
        }
        return tabla.get(gradosLibertad);
    }
}
