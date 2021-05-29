package com.sinergise.io.reader;

import com.sinergise.io.reader.parsers.*;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.utils.exceptions.NonExistingParser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;

public class WKTReaderFactory {
    /**
     *
     * @param tokenizer
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static Parser getReader(StreamTokenizer tokenizer) throws IOException, ParseException {
        tokenizer.nextToken();
        String readerType = tokenizer.sval;
        switch (readerType){
            case Constants.POINT : return new PointParser();
            case Constants.LINESTRING: return new LineStringParser();
            case Constants.POLYGON: return new PolygonParser();
            case Constants.MULTIPOINT: return new MultiPointParser();
            case Constants.MULTIPOLYGON: return new MultiPolygonParser();
            case Constants.MULTILINESTRING: return new MultiLineStringParser();
            case Constants.GEOMETRYCOLLECTION: return new CollectionParser();
        }
        throw new NonExistingParser("Unknown geometry type", tokenizer.lineno());
    }
}
