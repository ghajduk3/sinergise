package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.io.utils.exceptions.NonExistingWriter;

import java.text.DecimalFormat;

public abstract class Writer {
    /**
     *
     * @param geometry
     * @return
     * @throws NonExistingWriter
     */
    public abstract String write(Geometry geometry) throws NonExistingWriter;

    /**
     *
     * @param X
     * @param Y
     * @return
     */
    public String format(Double X, Double Y ) {
        return this.formatCoordinate(X) + " " + this.formatCoordinate(Y);
    }

    /**
     *
     * @param coordinate
     * @return
     */
    private String formatCoordinate(Double coordinate) {
        DecimalFormat df = new DecimalFormat("#.##");
        if (Double.isNaN(coordinate)) return "NaN";
        if (Double.isInfinite(coordinate)) {
            return coordinate > 0 ? "Infinity" : "-Infinity";
        }
        return df.format(coordinate);
    }

}
