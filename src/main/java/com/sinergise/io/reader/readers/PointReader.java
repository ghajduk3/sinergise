package com.sinergise.io.reader.readers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.utils.Parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;

public class PointReader extends Reader {

    @Override
    public Point read(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = Parser.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new Point();
        }
        double[] coordinates = Parser.getCoordinate(tokenizer);
        Parser.getNextValidToken(tokenizer);

        return new Point(coordinates[0], coordinates[1]);
    }
}
