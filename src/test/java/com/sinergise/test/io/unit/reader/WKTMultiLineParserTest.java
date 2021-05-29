package com.sinergise.test.io.unit.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.io.reader.parsers.MultiLineStringParser;
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

public class WKTMultiLineParserTest {

    MultiLineStringParser multiLineStringParser = new MultiLineStringParser();

    @Test
    @DisplayName("Test empty MultiLineString")
    public void parseEmptyMultiLineString() throws IOException {
        String wktString = "MULTILINESTRING EMPTY";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        MultiLineString l1 = new MultiLineString();
        MultiLineString l2 = new MultiLineString();
        try {
            l2 = (MultiLineString) multiLineStringParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }

    @Test
    @DisplayName("Test with one LineString")
    public void parseSingleLineString() throws IOException {
        String wktString = "MULTILINESTRING ((10 10, 20 20, 10 40))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        LineString ls1 = new LineString(new double[]{10, 10, 20, 20, 10, 40});
        MultiLineString l1 = new MultiLineString(new LineString[]{ls1});
        MultiLineString l2 = new MultiLineString();
        try {
            l2 = (MultiLineString) multiLineStringParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }

    @Test
    @DisplayName("Test with one multiple LineString")
    public void parseMultipleLineString() throws IOException {
        String wktString = "MULTILINESTRING ((30 10, 40 40, 20 40, 10 20, 30 10), (20 30, 35 35, 30 20, 20 30), (2 3, 3 3, 3 2, 2 3))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        LineString ls1 = new LineString(new double[]{30, 10, 40, 40, 20, 40, 10, 20, 30, 10});
        LineString ls2 = new LineString(new double[]{20, 30, 35, 35, 30, 20, 20, 30});
        LineString ls3 = new LineString(new double[]{2, 3, 3, 3, 3, 2, 2, 3});
        MultiLineString l1 = new MultiLineString(new LineString[]{ls1, ls2, ls3});
        MultiLineString l2 = new MultiLineString();
        try {
            l2 = (MultiLineString) multiLineStringParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }

    @Test
    @DisplayName("Test MultiLineString with incomplete coordinate pair")
    public void parseMultiLineStringIncompleteCoord() throws IOException {
        String wktString = "MULTILINESTRING ((1 5, 4 5, 9))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(NumberParseException.class, () -> {
            multiLineStringParser.read(tokenizer);
        });
    }

    @Test
    @DisplayName("Test LineString with token error")
    public void parseMultiLineStringTokenError() throws IOException {
        String wktString = "MULTILINESTRING ((1 5, 4 5, 9 10)";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(TokenParseException.class, () -> {
            multiLineStringParser.read(tokenizer);
        });
    }

}
