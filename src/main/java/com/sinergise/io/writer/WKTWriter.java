package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Point;

public class WKTWriter {
    public String write(Geometry geom) {
        //TODO: Implement this
        return write(new GeometryCollection<>(new Geometry[]{new Point(4, 6), new LineString(new double[]{4, 6, 7, 10})}));
    }
}
