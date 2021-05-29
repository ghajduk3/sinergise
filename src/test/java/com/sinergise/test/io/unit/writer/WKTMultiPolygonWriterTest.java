package com.sinergise.test.io.unit.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.writer.writers.MultiPolygonWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WKTMultiPolygonWriterTest {
    MultiPolygonWriter multiPolygonWriter = new MultiPolygonWriter();

    @Test
    @DisplayName("Test empty MultiPolygon")
    public void writeEmptyMultiLinePolygon() {
        Assertions.assertEquals(Constants.MULTIPOLYGON + " " + Constants.EMPTY, multiPolygonWriter.write(new MultiPolygon()));
    }

    @Test
    @DisplayName("Test with one polygon")
    public void writeOnePolygon() {
        LineString outer = new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10});
        Polygon p1 = new Polygon(outer, new LineString[]{});
        Assertions.assertEquals(Constants.MULTIPOLYGON + " (((30 10, 40 40, 20 40, 10 20, 30 10)))", multiPolygonWriter.write(new MultiPolygon(new Polygon[]{p1})));
    }

    @Test
    @DisplayName("Test with multiple polygons with only outer rings")
    public void writeMultiplePolygonsWithOuterRings() {
        LineString outer1 = new LineString(new double[]{30,20, 45,40, 10,40, 30,20});
        Polygon p1 = new Polygon(outer1, new LineString[]{});

        LineString outer2 = new LineString(new double[]{15,5, 40,10, 10,20, 5,10, 15,5});
        Polygon p2 = new Polygon(outer2, new LineString[]{});

        Assertions.assertEquals(Constants.MULTIPOLYGON + " (((30 20, 45 40, 10 40, 30 20)), ((15 5, 40 10, 10 20, 5 10, 15 5)))", multiPolygonWriter.write(new MultiPolygon(new Polygon[]{p1,p2})));
    }

    @Test
    @DisplayName("Test with multiple polygons with multiple rings")
    public void writeMultiplePolygonsWithMultipleRings() {
        LineString outer1 = new LineString(new double[]{40,40, 20,45, 45,30, 40,40});
        Polygon p1 = new Polygon(outer1, new LineString[]{});

        LineString outer2 = new LineString(new double[]{20,35, 10,30, 10,10, 30,5, 45,20, 20,35});
        LineString hole2 = new LineString(new double[]{30,20, 20, 15, 20, 25, 30, 20});
        Polygon p2 = new Polygon(outer2, new LineString[]{hole2});

        Assertions.assertEquals(Constants.MULTIPOLYGON + " (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))", multiPolygonWriter.write(new MultiPolygon(new Polygon[]{p1,p2})));
    }
}
