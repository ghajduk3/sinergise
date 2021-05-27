package com.sinergise.io.reader;

import com.sinergise.geometry.*;
import com.sinergise.io.reader.readers.Reader;
import com.sinergise.io.utils.Parser;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WKTReader {

    public Geometry read(String wktString) throws IOException, ParseException {
        StringReader reader = new StringReader(wktString);
        try{
            StreamTokenizer tokenizer = Parser.constructTokenizer(reader);
            Reader wktReader = WKTReaderFactory.getReader(tokenizer);
            return wktReader.read(tokenizer);
        } finally {
            reader.close();
        }
    }

}
