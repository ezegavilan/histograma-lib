package com.simulacion.histogramalib.core;

public class Intervalo {
    private final int intervalo;
    private final float inferior;
    private final float superior;
    private final float marcaClase;
    private float frecuenciaEsperada;
    private int frecuencia;
    private float proporcion;
    private int frecuenciaAcumulada;
    private float proporcionAcumulada;

    public Intervalo(int intervalo, float inferior, float superior, float marcaClase, float frecuenciaEsperada) {
        this.intervalo = intervalo;
        this.inferior = inferior;
        this.superior = superior;
        this.marcaClase = marcaClase;
        this.frecuenciaEsperada = frecuenciaEsperada;
        this.frecuencia = 0;
        this.proporcion = 0f;
        this.frecuenciaAcumulada = 0;
        this.proporcionAcumulada = 0f;
    }

    public void registrarObservacion() {
        this.frecuencia++;
    }

    public void registrarProporcion(int n) {
        this.proporcion = (float) this.frecuencia / n;
    }

    public void registrarFrecuenciaAcumulada(int frecuencaAcumulada) {
        this.frecuenciaAcumulada = frecuencaAcumulada;
    }

    public void registrarProporcionAcumulada(float proporcionAcumulada) {
        this.proporcionAcumulada = proporcionAcumulada;
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

    public float getMarcaClase() {
        return marcaClase;
    }

    public float getFrecuenciaEsperada() {
        return this.frecuenciaEsperada;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public float getProporcion() {
        return proporcion;
    }

    public int getFrecuenciaAcumulada() {
        return frecuenciaAcumulada;
    }

    public float getProporcionAcumulada() {
        return proporcionAcumulada;
    }

    @Override
    public String toString() {
        return "Intervalo{" +
                "intervalo=" + intervalo +
                ", inferior=" + inferior +
                ", superior=" + superior +
                ", marcaClase=" + marcaClase +
                ", frecuenciaEsperada=" + frecuenciaEsperada +
                ", frecuencia=" + frecuencia +
                ", proporcion=" + proporcion +
                ", frecuenciaAcumulada=" + frecuenciaAcumulada +
                ", proporcionAcumulada=" + proporcionAcumulada +
                '}';
    }
}
