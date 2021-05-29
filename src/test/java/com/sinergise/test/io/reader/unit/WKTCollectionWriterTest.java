package com.sinergise.test.io.reader.unit;

import com.sinergise.geometry.*;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.writer.writers.CollectionWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class WKTCollectionWriterTest {
    CollectionWriter collectionWriter = new CollectionWriter();

    @Test
    @DisplayName("Test empty GeometryCollection")
    public void writeEmptyGeometryCollection() {
        Assertions.assertEquals(Constants.GEOMETRYCOLLECTION + " " + Constants.EMPTY, collectionWriter.write(new GeometryCollection<>()));
    }

    @Test
    @DisplayName("Test with one linestring")
    public void writeOneLineString() {
        Point p1 = new Point(40, 10);
        LineString l1 = new LineString(new double[]{10, 10, 20, 20, 10, 40});
        LineString outer = new LineString(new double[]{40, 40, 20, 45, 45, 30, 40, 40});
        Polygon polygon = new Polygon(outer, new LineString[]{});
        Assertions.assertEquals(Constants.GEOMETRYCOLLECTION + " (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))", collectionWriter.write(new GeometryCollection<Geometry>(Arrays.asList(p1, l1, polygon))));
    }
}
