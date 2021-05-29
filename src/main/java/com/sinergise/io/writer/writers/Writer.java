package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;

import java.text.DecimalFormat;

public abstract class Writer {

    public abstract String write(Geometry geometry);

    public String format(Double X, Double Y ) {
        return this.formatCoordinate(X) + " " + this.formatCoordinate(Y);
    }

    private String formatCoordinate(Double coordinate) {
        DecimalFormat df = new DecimalFormat("#.##");
        if (Double.isNaN(coordinate)) return "NaN";
        if (Double.isInfinite(coordinate)) {
            return coordinate > 0 ? "Inf" : "-Inf";
        }
        return df.format(coordinate);
    }

}
