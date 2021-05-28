package com.sinergise.io.reader.parsers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.io.reader.WKTReaderFactory;
import com.sinergise.io.utils.Constants;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CollectionParser extends Parser {

    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        String nextToken = this.getNextValidToken(tokenizer);
        if (nextToken.equalsIgnoreCase(Constants.EMPTY)){
            return new GeometryCollection<>();
        }

        List elements = new ArrayList();
        do {
            Parser parser = WKTReaderFactory.getReader(tokenizer);
            elements.add(parser.read(tokenizer));
        } while(this.getNextValidToken(tokenizer).equalsIgnoreCase(","));
        return new GeometryCollection(elements);
    }
}
