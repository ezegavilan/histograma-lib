package com.simulacion.histogramalib.pruebabondad.chicuadrado;

public class IntervaloChiCuadrado {
    private final int intervalo;
    private final float inferior;
    private final float superior;
    private final int frecuenciaObservada;
    private final float frecuenciaEsperada;
    private float chiCuadrado;
    private float chiCuadradoAcumulado;

    public IntervaloChiCuadrado(int intervalo, float inferior, float superior, int frecuenciaObservada, float frecuenciaEsperada) {
        this.intervalo = intervalo;
        this.inferior = inferior;
        this.superior = superior;
        this.frecuenciaObservada = frecuenciaObservada;
        this.frecuenciaEsperada = frecuenciaEsperada;
        this.chiCuadrado = 0f;
        this.chiCuadradoAcumulado = 0f;
    }

    public void registrarChiCuadrado() {
        this.chiCuadrado = calcularChiCuadrado(frecuenciaObservada, frecuenciaEsperada);
    }

    public void registrarChiCuadradoAcumulado(float chiCuadradoAcumulado) {
        this.chiCuadradoAcumulado = chiCuadradoAcumulado;
    }

    private float calcularChiCuadrado(int fo, float fe) {
        return (float) (Math.pow((fo - fe), 2) / fe);
    }

    public int getIntervalo() {
        return intervalo;
    }

    public float getInferior() {
        return inferior;
    }

    public float getSuperior() {
        return superior;
    }

    public int getFrecuenciaObservada() {
        return frecuenciaObservada;
    }

    public float getFrecuenciaEsperada() {
        return frecuenciaEsperada;
    }

    public float getChiCuadrado() {
        return chiCuadrado;
    }

    public float getChiCuadradoAcumulado() {
        return chiCuadradoAcumulado;
    }

    @Override
    public String toString() {
        return "IntervaloChiCuadrado{" +
                "intervalo=" + intervalo +
                ", inferior=" + inferior +
                ", superior=" + superior +
                ", frecuenciaObservada=" + frecuenciaObservada +
                ", frecuenciaEsperada=" + frecuenciaEsperada +
                ", chiCuadrado=" + chiCuadrado +
                ", chiCuadradoAcumulado=" + chiCuadradoAcumulado +
                '}';
    }
}
