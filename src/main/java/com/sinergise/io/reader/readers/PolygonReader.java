package com.sinergise.io.reader.readers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PolygonReader extends Reader {

    @Override
    public Polygon read(StreamTokenizer tokenizer) throws IOException, ParseException {
        Parser.getNextValidToken(tokenizer);
        LineStringReader lineStringReader = new LineStringReader();
        LineString outer = lineStringReader.read(tokenizer);
        List<LineString> holes = new ArrayList<>();
        while(Parser.getNextValidToken(tokenizer).equalsIgnoreCase(",")) {
            holes.add(lineStringReader.read(tokenizer));
        }
        return new Polygon(outer,holes.toArray(new LineString[0]));
    }
}
