package com.sinergise.test.io.integration;

import com.sinergise.geometry.Geometry;
import com.sinergise.io.reader.WKTReader;
import com.sinergise.io.utils.exceptions.NonExistingParser;
import com.sinergise.io.utils.exceptions.NonExistingWriter;
import com.sinergise.io.writer.WKTWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

public class WKTGeometryIntegrationTest {
    WKTWriter writer = new WKTWriter();
    WKTReader reader = new WKTReader();

    @Test()
    @DisplayName("Test empty Point")
    public void integrateEmptyPoint() throws IOException, ParseException, NonExistingWriter {
        String wktString = "POINT EMPTY";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);

        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test Point")
    public void integratePoint() throws IOException, ParseException, NonExistingWriter {
        String wktString = "POINT (6 10)";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);
        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test empty LineString")
    public void integrateEmptyLineString() throws IOException, ParseException, NonExistingWriter {
        String wktString = "LINESTRING EMPTY";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);

        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test LineString")
    public void integrateLineString() throws IOException, ParseException, NonExistingWriter {
        String wktString = "LINESTRING (30 10, 10 30, 40 40)";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);
        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test empty Polygon")
    public void integrateEmptyPolygon() throws IOException, ParseException, NonExistingWriter {
        String wktString = "POLYGON EMPTY";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);

        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test Polygon")
    public void integratePolygon() throws IOException, ParseException, NonExistingWriter {
        String wktString = "POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);
        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test empty MultiPoint")
    public void integrateEmptyMultiPoint() throws IOException, ParseException, NonExistingWriter {
        String wktString = "MULTIPOINT EMPTY";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);

        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test MultiPoint")
    public void integrateMultiPoint() throws IOException, ParseException, NonExistingWriter {
        String wktString = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);
        Assertions.assertEquals(wktString, wktWriteString);
    }

    @Test()
    @DisplayName("Test empty MultiLineString")
    public void integrateEmptyMultiLineString() throws IOException, ParseException, NonExistingWriter {
        String wktString = "MULTILINESTRING EMPTY";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);

        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test MultiLineString")
    public void integrateMultiLineString() throws IOException, ParseException, NonExistingWriter {
        String wktString = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);
        Assertions.assertEquals(wktString, wktWriteString);
    }

    @Test()
    @DisplayName("Test empty MultiPolygon")
    public void integrateEmptyMultiPolygon() throws IOException, ParseException, NonExistingWriter {
        String wktString = "MULTIPOLYGON EMPTY";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);

        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test MultiPolygon")
    public void integrateMultiPolygon() throws IOException, ParseException, NonExistingWriter {
        String wktString = "MULTIPOLYGON (((30 20, 45 40, 10 40, 30 20)), ((15 5, 40 10, 10 20, 5 10, 15 5)))";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);
        Assertions.assertEquals(wktString, wktWriteString);
    }

    @Test()
    @DisplayName("Test empty GeometryCollection")
    public void integrateEmptyGeometryCollection() throws IOException, ParseException, NonExistingWriter {
        String wktString = "GEOMETRYCOLLECTION EMPTY";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);

        Assertions.assertEquals(wktString, wktWriteString);

    }

    @Test()
    @DisplayName("Test GeometryCollection")
    public void integrateGeometryCollection() throws IOException, ParseException, NonExistingWriter {
        String wktString = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
        Geometry point = reader.read(wktString);
        String wktWriteString = writer.write(point);
        Assertions.assertEquals(wktString, wktWriteString);
    }

    @Test()
    @DisplayName("Test non existing Geometry type Reader")
    public void integrateNonExistingGeometry() throws IOException, ParseException {
        String wktString = "NEWGEO EMPTY";
        Assertions.assertThrows(NonExistingParser.class, () -> {
            reader.read(wktString);
        }
        );
    }
}
