package com.sinergise.io.writer;

import com.sinergise.geometry.*;
import com.sinergise.io.writer.writers.*;

public class WKTWriterFactory {
    public static Writer getWriter(Geometry geometry){
        if (geometry instanceof Point){
            return new PointWriter();
        }
        else if (geometry instanceof LineString) {
            return new LineStringWriter();
        }
        else if (geometry instanceof Polygon) {
            return new PolygonWriter();
        }
        else if (geometry instanceof MultiPoint) {
            return new MultiPointWriter();
        }
        return null;
    }
}
