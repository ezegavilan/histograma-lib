package com.simulacion.histogramalib.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Esta clase representa un número decimal con 4 decimales de precisión.
 */
public class Decimal {
    private final float value;

    private Decimal(float value) {
        this.value = new BigDecimal(value).setScale(4, RoundingMode.HALF_UP).floatValue();
    }

    public static Decimal of(float value) {
        return new Decimal(value);
    }

    public float value() {
        return value;
    }

    @Override
    public String toString() {
        return "Decimal{" +
                "value=" + value +
                '}';
    }
}
