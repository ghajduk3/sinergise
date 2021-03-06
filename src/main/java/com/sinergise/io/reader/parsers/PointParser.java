package com.sinergise.io.reader.parsers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;

public class PointParser extends Parser {

    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        return this.readPoint(tokenizer);
    }

    /**
     *
     * @param tokenizer
     * @return
     * @throws IOException
     * @throws ParseException
     */
    protected Point readPoint(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = this.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new Point();
        }
        double[] coordinates = this.getCoordinate(tokenizer);
        this.getNextValidToken(tokenizer);

        return new Point(coordinates[0], coordinates[1]);
    }
}
