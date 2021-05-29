package com.sinergise.io.reader.parsers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Constants;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultiPolygonParser extends PolygonParser {

    /**
     *
     * @param tokenizer
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = this.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new MultiPolygon();
        }
        List<Polygon> polygons = new ArrayList<>();
        do {
            polygons.add(super.readPolygon(tokenizer));
        } while(this.getNextValidToken(tokenizer).equalsIgnoreCase(","));
        return new MultiPolygon(polygons.toArray(new Polygon[0]));
    }
}
