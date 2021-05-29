package com.sinergise.test.io.writer.unit;

import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.writer.writers.MultiPointWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WKTMultiPointWriterTest {
    MultiPointWriter multiPointWriter = new MultiPointWriter();

    @Test
    @DisplayName("Test empty multi point")
    public void writeEmptyMultiPoint() {
        Assertions.assertEquals(Constants.MULTIPOINT + " " + Constants.EMPTY, multiPointWriter.write(new MultiPoint()));
    }

    @Test
    @DisplayName("Test with one point")
    public void writeOnePoint() {
        Assertions.assertEquals(Constants.MULTIPOINT + " ((6 0.6))", multiPointWriter.write(new MultiPoint(new Point[]{new Point(6.0, 0.6)})));
    }

    @Test
    @DisplayName("Test with multiple points")
    public void writeMultiplePoints() {
        Point p1 = new Point(10, 40);
        Point p2 = new Point(40, 30);
        Point p3 = new Point(20, 20);
        Point p4 = new Point(30, 10);
        MultiPoint multiPoint = new MultiPoint(new Point[]{p1, p2, p3, p4});
        Assertions.assertEquals(Constants.MULTIPOINT + " ((10 40), (40 30), (20 20), (30 10))", multiPointWriter.write(multiPoint));
    }
}
