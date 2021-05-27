package com.sinergise.io.reader;

import com.sinergise.geometry.GeometryCollection;
import com.sinergise.io.reader.readers.*;
import com.sinergise.io.utils.Parser;
import com.sinergise.io.utils.Constants;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;

public class WKTReaderFactory {

    public static Reader getReader(StreamTokenizer tokenizer) throws IOException, ParseException {
        String readerType = Parser.getNextValidToken(tokenizer);

        switch (readerType){
            case Constants.POINT : return new PointReader();
            case Constants.LINESTRING: return new LineStringReader();
            case Constants.POLYGON: return new PolygonReader();
            case Constants.MULTIPOINT: return new MultiPointReader();
            case Constants.MULTIPOLYGON: return new MultiPolygonReader();
            case Constants.MULTILINESTRING: return new MultiLineStringReader();
            case Constants.GEOMETRYCOLLECTION: return new CollectionReader();
        }
        throw new ParseException("Unknown geometry tupe", tokenizer.lineno());
    }
}
