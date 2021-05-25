package com.sinergise.io.reader.wktreaders;

import com.sinergise.geometry.Geometry;

import java.util.StringTokenizer;

public abstract class AbstractReader {
    public abstract Geometry read(StringTokenizer tokenizer);
}
