package com.sinergise.test.io.unit.reader;

import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import com.sinergise.io.reader.parsers.MultiPointParser;
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

public class WKTMultiPointParserTest {
    MultiPointParser multiPointParser = new MultiPointParser();

    @Test
    @DisplayName("Test empty MultiPoint")
    public void parseEmptyMultiPointString() throws IOException {
        String wktString = "MULTIPOINT EMPTY";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        MultiPoint l1 = new MultiPoint();
        MultiPoint l2 = new MultiPoint();
        try {
            l2 = (MultiPoint) multiPointParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }

    @Test
    @DisplayName("Test with one LineString")
    public void parseMultiplePoints() throws IOException {
        String wktString = "MULTIPOINT ((10 10), (20 20), (10 40))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        MultiPoint l1 = new MultiPoint(new Point[]{new Point(10, 10), new Point(20, 20), new Point(10, 40)});
        MultiPoint l2 = new MultiPoint();
        try {
            l2 = (MultiPoint) multiPointParser.read(tokenizer);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(l1, l2);
    }


    @Test
    @DisplayName("Test MultiPoint with incomplete coordinate pair")
    public void parseMultiPointIncompleteCoord() throws IOException {
        String wktString = "MULTIPOINT ((1 5), (4 5), (9))";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(NumberParseException.class, () -> {
            multiPointParser.read(tokenizer);
        });
    }

    @Test
    @DisplayName("Test MultiPoint with token error")
    public void parseMultiPointTokenError() throws IOException {
        String wktString = "MULTIPOINT ((1 5), (4 5), (9 10";
        StringReader reader = new StringReader(wktString);
        StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
        tokenizer.nextToken();
        Assertions.assertThrows(TokenParseException.class, () -> {
            multiPointParser.read(tokenizer);
        });
    }
}
