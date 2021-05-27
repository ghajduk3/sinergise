package com.sinergise.io.reader.readers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.utils.Parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.List;

public class LineStringReader extends Reader{

    @Override
    public LineString read(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = Parser.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new LineString();
        }
        List<Double> coordinates = Parser.getCoordinates(tokenizer);
//        System.out.println(getNextWord(tokenizer));
        return new LineString(Parser.convertToArray(coordinates));
    }
}
