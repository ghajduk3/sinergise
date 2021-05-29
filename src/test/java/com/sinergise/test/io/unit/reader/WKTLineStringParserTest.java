package com.sinergise.test.io.unit.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.io.reader.parsers.LineStringParser;
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

public class WKTLineStringParserTest {
    LineStringParser lineStringParser = new LineStringParser();

    @Test
    @DisplayName("Test empty LineString")
    public void parseEmptyLineString() throws IOException {
        String wktString = "LINESTRING EMPTY";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        LineString l1 = new LineString();
        LineString l2 = new LineString();
        try {
            l2 = (LineString) lineStringParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }

    @Test
    @DisplayName("Test LineString with one coordinate pair")
    public void parseLineStringOneCoord() throws IOException {
        String wktString = "LINESTRING (1 5)";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        LineString l1 = new LineString(new double[]{1, 5});
        LineString l2 = new LineString();
        try {
            l2 = (LineString) lineStringParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }

    @Test
    @DisplayName("Test LineString with multiple coordinate pairs")
    public void parseLineStringMultipleCoord() throws IOException {
        String wktString = "LINESTRING (1 5, 4 5, 9 15)";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        LineString l1 = new LineString(new double[]{1, 5, 4, 5, 9, 15});
        LineString l2 = new LineString();
        try {
            l2 = (LineString) lineStringParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }

    @Test
    @DisplayName("Test LineString with incomplete coordinate pair")
    public void parseLineStringIncompleteCoord() throws IOException {
        String wktString = "LINESTRING (1 5, 4 5, 9)";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(NumberParseException.class, () -> {
            lineStringParser.read(tokenizer);
        });
    }

    @Test
    @DisplayName("Test LineString with token error")
    public void parseLineStringTokenError() throws IOException {
        String wktString = "LINESTRING (1 5, 4 5";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(TokenParseException.class, () -> {
            lineStringParser.read(tokenizer);
        });
    }

}

