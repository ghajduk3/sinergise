package com.sinergise.io.reader.parsers;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Constants;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PolygonParser extends Parser {

    @Override
    public Polygon read(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = this.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new Polygon();
        }
        LineStringParser lineStringReader = new LineStringParser();
        LineString outer = lineStringReader.read(tokenizer);
        List<LineString> holes = new ArrayList<>();
        while(this.getNextValidToken(tokenizer).equalsIgnoreCase(",")) {
            holes.add(lineStringReader.read(tokenizer));
        }
        return new Polygon(outer,holes.toArray(new LineString[0]));
    }
}
