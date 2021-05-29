package com.sinergise.test.io.unit.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.writer.writers.MultiLineStringWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WKTMultiLineStringWriterTest {

    MultiLineStringWriter multiLineStringWriter = new MultiLineStringWriter();

    @Test
    @DisplayName("Test empty multi LineString")
    public void writeEmptyMultiLineString() {
        Assertions.assertEquals(Constants.MULTILINESTRING + " " + Constants.EMPTY, multiLineStringWriter.write(new MultiLineString()));
    }

    @Test
    @DisplayName("Test with one linestring")
    public void writeOneLineString() {
        LineString l1 = new LineString(new double[]{10, 10, 20, 20, 10, 40});
        Assertions.assertEquals(Constants.MULTILINESTRING + " ((10 10, 20 20, 10 40))", multiLineStringWriter.write(new MultiLineString(new LineString[]{l1})));
    }

    @Test
    @DisplayName("Test with multiple linestrings")
    public void writeMultipleLineStrings() {
        LineString l1 = new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10});
        LineString l2 = new LineString(new double[]{20, 30, 35, 35, 30, 20, 20, 30});
        LineString l3 = new LineString(new double[]{2, 3, 3, 3, 3, 2, 2, 3});

        MultiLineString multiLineString = new MultiLineString(new LineString[]{l1, l2, l3});
        Assertions.assertEquals(Constants.MULTILINESTRING + " ((30 10, 40 40, 20 40, 10 20, 30 10), (20 30, 35 35, 30 20, 20 30), (2 3, 3 3, 3 2, 2 3))", multiLineStringWriter.write(multiLineString));
    }
}
