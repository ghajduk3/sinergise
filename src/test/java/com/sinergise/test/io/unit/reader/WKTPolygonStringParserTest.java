package com.sinergise.test.io.unit.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.reader.parsers.PolygonParser;
import com.sinergise.io.utils.ParserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.text.ParseException;

public class WKTPolygonStringParserTest {
    PolygonParser polygonParser = new PolygonParser();

    @Test
    @DisplayName("Test empty Polygon")
    public void parseEmptyLineString() throws IOException {
        String wktString = "POLYGON EMPTY";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Polygon p1 = new Polygon();
        Polygon p2 = new Polygon();
        try {
            p2 = (Polygon) polygonParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(p1, p2);
    }

    @Test
    @DisplayName("Test polygon with outer ring")
    public void parsePolygonOuterRing() throws IOException {
        String wktString = "POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Polygon p1 = new Polygon(new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10}), new LineString[]{});
        Polygon p2 = new Polygon();
        try {
            p2 = (Polygon) polygonParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(p1, p2);
    }

    @Test
    @DisplayName("Test polygon with outer ring and inner ring")
    public void parsePolygonOuterAndInnerRing() throws IOException {
        String wktString = "POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10), (20 30, 35 35, 30 20, 20 30))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        LineString outer = new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10});
        LineString hole = new LineString(new double[]{20, 30, 35, 35, 30, 20, 20, 30});
        LineString[] holes = new LineString[]{hole};
        Polygon p1 = new Polygon(outer, holes);
        Polygon p2 = new Polygon();
        try {
            p2 = (Polygon) polygonParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(p1, p2);
    }

    @Test
    @DisplayName("Test polygon with outer ring and multiple inner rings")
    public void parsePolygonOuterAndInnerRings() throws IOException {
        String wktString = "POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10), (20 30, 35 35, 30 20, 20 30), (2 3, 3 3, 3 2, 2 3))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        LineString outer = new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10});
        LineString hole = new LineString(new double[]{20, 30, 35, 35, 30, 20, 20, 30});
        LineString hole1 = new LineString(new double[]{2, 3, 3, 3, 3, 2, 2, 3});
        LineString[] holes = new LineString[]{hole, hole1};
        Polygon p1 = new Polygon(outer, holes);
        Polygon p2 = new Polygon();
        try {
            p2 = (Polygon) polygonParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(p1, p2);
    }
}
