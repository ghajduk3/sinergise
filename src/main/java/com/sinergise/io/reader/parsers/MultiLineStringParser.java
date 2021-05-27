package com.sinergise.io.reader.parsers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.io.utils.Constants;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultiLineStringParser extends Parser {

    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = this.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new MultiLineString();
        }
        List<LineString> lineStrings = new ArrayList<>();
        LineStringParser lineStringReader = new LineStringParser();
        do {
            lineStrings.add(lineStringReader.read(tokenizer));
        } while(this.getNextValidToken(tokenizer).equalsIgnoreCase(","));

        return new MultiLineString(lineStrings.toArray(new LineString[0]));
    }
}
