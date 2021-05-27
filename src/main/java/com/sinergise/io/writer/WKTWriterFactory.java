package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;
import com.sinergise.io.writer.writers.PointWriter;
import com.sinergise.io.writer.writers.Writer;

public class WKTWriterFactory {
    public static Writer getWriter(Geometry geometry){
        if (geometry instanceof Point){
            return new PointWriter(geometry);
        }
        return null;
    }
}
