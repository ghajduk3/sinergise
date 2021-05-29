package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.io.reader.parsers.Parser;
import com.sinergise.io.utils.ParserUtils;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.text.ParseException;

public class WKTReader {
    /**
     *
     * @param wktString
     * @throws IOException
     * @throws ParseException
     */
    public Geometry read(String wktString) throws IOException, ParseException {
        try (StringReader reader = new StringReader(wktString)) {
            StreamTokenizer tokenizer = ParserUtils.constructTokenizer(reader);
            Parser wktParser = WKTReaderFactory.getReader(tokenizer);
            return wktParser.read(tokenizer);
        }
    }
}
