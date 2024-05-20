package com.vitcode.iprayertimes.dateconverter;

public class NewMoon extends MoonPhases {
    EclipticPosition eclipPos = new EclipticPosition();

    public double calculatePhase(double T) {
        double l_Sun = EclipticPosition.getMiniSunLongitude(T - 1.5818693436763253E-7d);
        double[] moonLongLat = EclipticPosition.getMiniMoon(T);
        double l_Moon = moonLongLat[0];
        double d = moonLongLat[1];
        return Modulo(3.141592653589793d + (l_Moon - l_Sun), 6.283185307179586d) - 3.141592653589793d;
    }

    private double Modulo(double x, double y) {
        return Frac(x / y) * y;
    }

    private double Frac(double x) {
        return x - Math.floor(x);
    }
}
