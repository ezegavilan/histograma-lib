package com.simulacion.histogramalib.pruebabondad.chicuadrado;

public class PruebaBondadChiCuadrado {

    public boolean validarHipotesis(float chiCuadradoCalculado, int gradosLibertad) {
        TablaDistribucionChiCuadrado tablaChiCuadrado = new TablaDistribucionChiCuadrado();
        float chiCuadradoTabulado = tablaChiCuadrado.valorChiTabulado(gradosLibertad);

        if (chiCuadradoTabulado < 0) {
            return false;
        }

        return chiCuadradoCalculado <= chiCuadradoTabulado;
    }
}
