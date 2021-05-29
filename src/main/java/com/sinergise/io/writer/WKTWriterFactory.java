package com.sinergise.io.writer;

import com.sinergise.geometry.*;
import com.sinergise.io.utils.exceptions.NonExistingWriter;
import com.sinergise.io.writer.writers.*;

public class WKTWriterFactory {
    /**
     *
     * @param geometry
     * @return
     * @throws NonExistingWriter
     */
    public static Writer getWriter(Geometry geometry) throws NonExistingWriter {
        if (geometry instanceof Point) {
            return new PointWriter();
        } else if (geometry instanceof LineString) {
            return new LineStringWriter();
        } else if (geometry instanceof Polygon) {
            return new PolygonWriter();
        } else if (geometry instanceof MultiPoint) {
            return new MultiPointWriter();
        } else if (geometry instanceof MultiPolygon) {
            return new MultiPolygonWriter();
        } else if (geometry instanceof MultiLineString) {
            return new MultiLineStringWriter();
        } else if (geometry instanceof GeometryCollection) {
            return new CollectionWriter();
        }
        throw new NonExistingWriter();
    }
}
