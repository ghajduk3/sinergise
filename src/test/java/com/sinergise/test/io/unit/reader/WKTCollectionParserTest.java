package com.sinergise.test.io.unit.reader;

import com.sinergise.geometry.*;
import com.sinergise.io.reader.parsers.CollectionParser;
import com.sinergise.io.utils.ParserUtils;
import com.sinergise.io.utils.exceptions.NumberParseException;
import com.sinergise.io.utils.exceptions.TokenParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Arrays;

public class WKTCollectionParserTest {
    CollectionParser geometryCollectionParser = new CollectionParser();

    @Test
    @DisplayName("Test empty GeometryCollection")
    public void parseEmptyGeometryCollection() throws IOException {
        String wktString = "GEOMETRYCOLLECTION EMPTY";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        GeometryCollection<Geometry> l1 = new GeometryCollection<>();
        GeometryCollection<Geometry> l2 = new GeometryCollection<>();
        try {
            l2 = (GeometryCollection<Geometry>) geometryCollectionParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }

    @Test
    @DisplayName("Test with one LineString")
    public void parseMultiplePoints() throws IOException {
        String wktString = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Point p1 = new Point(40, 10);
        LineString ls1 = new LineString(new double[]{10, 10, 20, 20, 10, 40});
        LineString outer = new LineString(new double[]{40, 40, 20, 45, 45, 30, 40, 40});
        Polygon polygon = new Polygon(outer, new LineString[]{});
        GeometryCollection<Geometry> l1 = new GeometryCollection<Geometry>(Arrays.asList(p1, ls1, polygon));
        GeometryCollection<Geometry> l2 = new GeometryCollection<>();
        try {
            l2 = (GeometryCollection<Geometry>) geometryCollectionParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }


    @Test
    @DisplayName("Test GeometryCollection with incomplete coordinate pair")
    public void parseGeometryCollectiontIncompleteCoord() throws IOException {
        String wktString = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 )))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(NumberParseException.class, () -> {
            geometryCollectionParser.read(tokenizer);
        });
    }

    @Test
    @DisplayName("Test GeometryCollection with token error")
    public void parseMultiPointTokenError() throws IOException {
        String wktString = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(TokenParseException.class, () -> {
            geometryCollectionParser.read(tokenizer);
        });
    }
}
