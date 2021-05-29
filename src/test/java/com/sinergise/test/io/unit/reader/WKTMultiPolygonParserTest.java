package com.sinergise.test.io.unit.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.reader.parsers.MultiPolygonParser;
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

public class WKTMultiPolygonParserTest {
    MultiPolygonParser multiPolygonParser = new MultiPolygonParser();

    @Test
    @DisplayName("Test empty MultiPolygon")
    public void parseEmptyMultiPolygonString() throws IOException {
        String wktString = "MULTIPOLYGON EMPTY";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        MultiPolygon l1 = new MultiPolygon();
        MultiPolygon l2 = new MultiPolygon();
        try {
            l2 = (MultiPolygon) multiPolygonParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }

    @Test
    @DisplayName("Parse Polygons with outer rings")
    public void parseMultiplePolygonsWithOuterRings() throws IOException {
        String wktString = "MULTIPOLYGON (((30 20, 45 40, 10 40, 30 20)), ((15 5, 40 10, 10 20, 5 10, 15 5)))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        LineString outer1 = new LineString(new double[]{30, 20, 45, 40, 10, 40, 30, 20});
        Polygon p1 = new Polygon(outer1, new LineString[]{});
        LineString outer2 = new LineString(new double[]{15, 5, 40, 10, 10, 20, 5, 10, 15, 5});
        Polygon p2 = new Polygon(outer2, new LineString[]{});
        MultiPolygon multiPolygon = new MultiPolygon(new Polygon[]{p1, p2});
        MultiPolygon multiPolygon1 = new MultiPolygon();
        try {
            multiPolygon1 = (MultiPolygon) multiPolygonParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(multiPolygon, multiPolygon1);
    }

    @Test
    @DisplayName("Parse Polygons with outer and inner rings")
    public void parseMultiplePolygonsWithOuterInnerRings() throws IOException {
        String wktString = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        LineString outer1 = new LineString(new double[]{40, 40, 20, 45, 45, 30, 40, 40});
        Polygon p1 = new Polygon(outer1, new LineString[]{});
        LineString outer2 = new LineString(new double[]{20, 35, 10, 30, 10, 10, 30, 5, 45, 20, 20, 35});
        LineString hole2 = new LineString(new double[]{30, 20, 20, 15, 20, 25, 30, 20});
        Polygon p2 = new Polygon(outer2, new LineString[]{hole2});
        MultiPolygon multiPolygon = new MultiPolygon(new Polygon[]{p1, p2});
        MultiPolygon multiPolygon1 = new MultiPolygon();
        try {
            multiPolygon1 = (MultiPolygon) multiPolygonParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(multiPolygon, multiPolygon1);
    }

    @Test
    @DisplayName("Test MultiPolygon with incomplete coordinate pair")
    public void parseMultiPolygonIncompleteCoord() throws IOException {
        String wktString = "POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10), (20 30, 35 35, 30 20, 20))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(NumberParseException.class, () -> {
            multiPolygonParser.read(tokenizer);
        });
    }

    @Test
    @DisplayName("Test MultiPolygon with token error")
    public void parseMultiMultiPolygonTokenError() throws IOException {
        String wktString = "POLYGON 30 10, 40 40, 20 40, 10 20, 30 10), (20 30, 35 35, 30 20, 20 30)";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(TokenParseException.class, () -> {
            multiPolygonParser.read(tokenizer);
        });
    }
}
