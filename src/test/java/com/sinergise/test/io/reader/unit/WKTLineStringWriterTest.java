package com.sinergise.test.io.reader.unit;

import com.sinergise.geometry.LineString;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.writer.writers.LineStringWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WKTLineStringWriterTest {

    LineStringWriter lineStringWriter = new LineStringWriter();

    @Test
    @DisplayName("Test empty linestring")
    public void writeEmptyLineString() {
        Assertions.assertEquals(Constants.LINESTRING + " " + Constants.EMPTY, lineStringWriter.write(new LineString()));
    }

    @Test
    @DisplayName("Test LineString format")
    public void writeLineStringNumber() {
        LineString lineString = new LineString(new double[]{30, 10, 10, 30, 40, 40});
        Assertions.assertEquals(Constants.LINESTRING + " (30 10, 10 30, 40 40)", lineStringWriter.write(lineString));
    }

    @Test
    @DisplayName("Test LineString format")
    public void writeLineStringDecimal() {
        LineString lineString = new LineString(new double[]{30.1, 10.2, 10.0, 30.12, 40, 40});
        Assertions.assertEquals(Constants.LINESTRING + " (30.1 10.2, 10 30.12, 40 40)", lineStringWriter.write(lineString));
    }

    @Test
    @DisplayName("Test LineString incomplete coordinate pairs")
    public void writeLineStringIncompleteCoords() {
        LineString lineString = new LineString(new double[]{30.1, 10.2, 10.0, 30.12, 40});
        Assertions.assertEquals(Constants.LINESTRING + " (30.1 10.2, 10 30.12)", lineStringWriter.write(lineString));
    }

    @Test
    @DisplayName("Test LineString with one coord pair")
    public void writeLineStringSingleCoordinatePair() {
        LineString lineString = new LineString(new double[]{30.1, 10.2});
        Assertions.assertEquals(Constants.LINESTRING + " (30.1 10.2)", lineStringWriter.write(lineString));
    }

    @Test
    @DisplayName("Test LineString with negative coords")
    public void writeLineStringNegativeCoords() {
        LineString lineString = new LineString(new double[]{-30.10, 10.2});
        Assertions.assertEquals(Constants.LINESTRING + " (-30.1 10.2)", lineStringWriter.write(lineString));
    }

    @Test
    @DisplayName("Test LineString with NaN coords")
    public void writeLineStringNaNCoords() {
        LineString lineString = new LineString(new double[]{Double.NaN, Double.NaN});
        Assertions.assertEquals(Constants.LINESTRING + " (NaN NaN)", lineStringWriter.write(lineString));
    }

}
