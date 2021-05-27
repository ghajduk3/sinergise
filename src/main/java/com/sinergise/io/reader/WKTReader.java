package com.sinergise.io.reader;

import com.sinergise.geometry.*;
import com.sinergise.io.reader.parsers.Parser;
import com.sinergise.io.utils.ParserUtils;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.text.ParseException;

public class WKTReader {

    public Geometry read(String wktString) throws IOException, ParseException {
        try (StringReader reader = new StringReader(wktString)) {
            StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
            Parser wktParser = WKTReaderFactory.getReader(tokenizer);
            return wktParser.read(tokenizer);
        }
    }


}
