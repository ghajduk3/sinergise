package com.sinergise.io.reader.readers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.io.reader.WKTReaderFactory;
import com.sinergise.io.utils.Parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CollectionReader extends Reader{
    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        Parser.getNextValidToken(tokenizer);

        List elements = new ArrayList();
        do {
            Reader reader = WKTReaderFactory.getReader(tokenizer);
            elements.add(reader.read(tokenizer));
        } while(Parser.getNextValidToken(tokenizer).equalsIgnoreCase(","));
        return new GeometryCollection(elements);
    }
}
