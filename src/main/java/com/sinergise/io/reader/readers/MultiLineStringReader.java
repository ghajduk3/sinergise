package com.sinergise.io.reader.readers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.io.utils.Parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultiLineStringReader extends Reader{

    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        Parser.getNextValidToken(tokenizer);
        List<LineString> lineStrings = new ArrayList<>();
        LineStringReader lineStringReader = new LineStringReader();
        do {
            lineStrings.add(lineStringReader.read(tokenizer));
        } while(Parser.getNextValidToken(tokenizer).equalsIgnoreCase(","));

        return new MultiLineString(lineStrings.toArray(new LineString[0]));
    }
}
