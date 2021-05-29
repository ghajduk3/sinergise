package com.sinergise.io.reader.parsers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Constants;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PolygonParser extends LineStringParser {

    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        return this.readPolygon(tokenizer);
    }

    protected Polygon readPolygon(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = this.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new Polygon();
        }
        LineString outer = super.readLineString(tokenizer);
        List<LineString> holes = new ArrayList<>();
        while(this.getNextValidToken(tokenizer).equalsIgnoreCase(",")) {
            holes.add(super.readLineString(tokenizer));
        }
        return new Polygon(outer,holes.toArray(new LineString[0]));
    }
}
