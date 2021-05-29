package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;
import com.sinergise.io.utils.exceptions.NonExistingWriter;
import com.sinergise.io.writer.writers.PolygonWriter;
import com.sinergise.io.writer.writers.Writer;

public class WKTWriter {
    /**
     *
     * @param geometry
     * @return
     * @throws NonExistingWriter
     */
    public String write(Geometry geometry) throws NonExistingWriter {
        Writer writer = WKTWriterFactory.getWriter(geometry);
        return writer.write(geometry);
    }
}