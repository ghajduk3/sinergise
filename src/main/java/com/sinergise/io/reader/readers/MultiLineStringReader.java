package com.sinergise.io.reader.readers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.utils.Parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultiLineStringReader extends Reader{

    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = Parser.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new MultiLineString();
        }
        List<LineString> lineStrings = new ArrayList<>();
        LineStringReader lineStringReader = new LineStringReader();
        do {
            lineStrings.add(lineStringReader.read(tokenizer));
        } while(Parser.getNextValidToken(tokenizer).equalsIgnoreCase(","));

        return new MultiLineString(lineStrings.toArray(new LineString[0]));
    }
}
