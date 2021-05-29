package com.sinergise.test.io.reader.unit;

import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.writer.writers.PointWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WKTPointWriterTest {
    PointWriter pointWriter = new PointWriter();

    @Test
    @DisplayName("Test empty point")
    public void writeEmptyPoint() {
        Assertions.assertEquals(Constants.POINT + " " + Constants.EMPTY, pointWriter.write(new Point()));
    }

    @Test
    @DisplayName("Test decimal coordinates")
    public void writeDecimalPoint() {
        Assertions.assertEquals(Constants.POINT + " (6 0.6)", pointWriter.write(new Point(6.0, 0.6)));
    }

    @Test
    @DisplayName("Test number coordinates")
    public void writeNumberPoint() {
        Assertions.assertEquals(Constants.POINT + " (1 0)", pointWriter.write(new Point(1.0, 0.0)));
    }
}
