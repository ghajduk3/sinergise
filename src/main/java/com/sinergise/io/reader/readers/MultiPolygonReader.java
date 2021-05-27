package com.sinergise.io.reader.readers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultiPolygonReader extends Reader{

    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        Parser.getNextValidToken(tokenizer);
        List<Polygon> polygons = new ArrayList<>();
        PolygonReader polygonReader = new PolygonReader();
        do {
            polygons.add(polygonReader.read(tokenizer));
        } while(Parser.getNextValidToken(tokenizer).equalsIgnoreCase(","));
        return new MultiPolygon(polygons.toArray(new Polygon[0]));
    }
}
