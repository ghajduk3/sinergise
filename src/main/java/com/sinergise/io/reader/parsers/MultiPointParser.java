package com.sinergise.io.reader.parsers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultiPointParser extends PointParser {

    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = this.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new MultiPoint();
        }
        List<Point> points = new ArrayList<>();

        do {
            points.add(super.readPoint(tokenizer));
        } while(this.getNextValidToken(tokenizer).equalsIgnoreCase(Constants.COMMA));

        return new MultiPoint(points.toArray(new Point[0]));
    }
}
