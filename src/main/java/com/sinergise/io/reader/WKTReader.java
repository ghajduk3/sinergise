package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;

import java.util.StringTokenizer;

public class WKTReader {

    public Geometry read (String wktString) {
        StringTokenizer tokenizer = new StringTokenizer(wktString, "(), ", true);
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        System.out.println(tokenizer.nextToken());
        return null;
    }
}
