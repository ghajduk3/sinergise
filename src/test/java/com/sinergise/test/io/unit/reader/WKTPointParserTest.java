package com.sinergise.test.io.unit.reader;

import com.sinergise.geometry.Point;
import com.sinergise.io.reader.parsers.PointParser;
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

public class WKTPointParserTest {
    PointParser parser = new PointParser();

    @Test
    @DisplayName("Test empty point")
    public void parseEmptyPoint() throws IOException {
        String wktString = "POINT EMPTY";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Point p1 = new Point();
        Point p2 = new Point();
        try {
            p2 = (Point) parser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(p1, p2);
    }

    @Test
    @DisplayName("Test point with decimal coordinates")
    public void parseDecimalPoint() throws IOException {
        String wktString = "POINT (6.1 3.2)";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Point p1 = new Point(6.1, 3.2);
        Point p2 = new Point();
        try {
            p2 = (Point) parser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(p1, p2);
    }

    @Test
    @DisplayName("Test point with number coordinates")
    public void parseNumberPoint() throws IOException {
        String wktString = "POINT (6 3)";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Point p1 = new Point(6, 3);
        Point p2 = new Point();
        try {
            p2 = (Point) parser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(p1, p2);
    }

    @Test
    @DisplayName("Test point with NaN and Inf coordinates")
    public void parseNaNInfPoint() throws IOException {
        String wktString = "POINT (NaN Infinity)";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Point p1 = new Point(Double.NaN, Double.POSITIVE_INFINITY);
        Point p2 = new Point();
        try {
            p2 = (Point) parser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(p1, p2);
    }

    @Test
    @DisplayName("Test empty string")
    public void parseEmptyString() throws IOException {
        String wktString = "POINT ";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();

        Assertions.assertThrows(TokenParseException.class, () -> {
            parser.read(tokenizer);
        });
    }

    @Test
    @DisplayName("Test invalid number coordinates exception")
    public void parseInvalidNumber() throws IOException {
        String wktString = "POINT (7 G)";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();

        Assertions.assertThrows(NumberParseException.class, () -> {
            parser.read(tokenizer);
        });
    }
}
