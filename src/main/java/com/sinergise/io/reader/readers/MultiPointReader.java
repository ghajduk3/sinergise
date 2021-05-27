package com.sinergise.io.reader.readers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultiPointReader extends Reader{

    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        Parser.getNextValidToken(tokenizer);
        PointReader pointReader = new PointReader();
        List<Point> points = new ArrayList<>();

        do {
            points.add(pointReader.read(tokenizer));
        } while(Parser.getNextValidToken(tokenizer).equalsIgnoreCase(","));

        return new MultiPoint(points.toArray(new Point[0]));
    }
}
