package com.sinergise.io.reader.readers;

import com.sinergise.geometry.Geometry;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;

public abstract class Reader {
    public abstract Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException;
}
