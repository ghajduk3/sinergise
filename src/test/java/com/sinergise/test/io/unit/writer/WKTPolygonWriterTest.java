package com.sinergise.test.io.unit.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.writer.writers.PolygonWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WKTPolygonWriterTest {
    PolygonWriter polygonWriter = new PolygonWriter();

    @Test
    @DisplayName("Test empty polygon")
    public void writeEmptyPolygon() {
        Assertions.assertEquals(Constants.POLYGON + " " + Constants.EMPTY, polygonWriter.write(new Polygon()));
    }

    @Test
    @DisplayName("Test polygon with outer linestring")
    public void writePolygonWithOuterRing() {
        LineString outer = new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10});
        Polygon polygon = new Polygon(outer, new LineString[]{});
        Assertions.assertEquals(Constants.POLYGON + " " + "((30 10, 40 40, 20 40, 10 20, 30 10))", polygonWriter.write(polygon));
    }

    @Test
    @DisplayName("Test polygon with outer and one hole")
    public void writePolygonWithOuterRingSingleHole() {
        LineString outer = new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10});
        LineString hole = new LineString(new double[]{20, 30, 35, 35, 30, 20, 20, 30});
        LineString[] holes = new LineString[]{hole};
        Polygon polygon = new Polygon(outer, holes);
        Assertions.assertEquals(Constants.POLYGON + " " + "((30 10, 40 40, 20 40, 10 20, 30 10), (20 30, 35 35, 30 20, 20 30))", polygonWriter.write(polygon));
    }

    @Test
    @DisplayName("Test polygon with outer and multiple holes")
    public void writePolygonWithOuterRingMultipleHoles() {
        LineString outer = new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10});
        LineString hole = new LineString(new double[]{20, 30, 35, 35, 30, 20, 20, 30});
        LineString hole1 = new LineString(new double[]{2, 3, 3, 3, 3, 2, 2, 3});
        LineString[] holes = new LineString[]{hole, hole1};
        Polygon polygon = new Polygon(outer, holes);
        Assertions.assertEquals(Constants.POLYGON + " " + "((30 10, 40 40, 20 40, 10 20, 30 10), (20 30, 35 35, 30 20, 20 30), (2 3, 3 3, 3 2, 2 3))", polygonWriter.write(polygon));
    }

}
