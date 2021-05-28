package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;
import com.sinergise.io.writer.writers.Writer;

public class WKTWriter {
    public String write(Geometry geometry) {
        Writer writer = WKTWriterFactory.getWriter(geometry);
        return writer.write(geometry);
    }
}