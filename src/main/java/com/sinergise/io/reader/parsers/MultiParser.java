package com.sinergise.io.reader.parsers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultiParser<P extends Parser, G extends Geometry> extends Parser{
    @Override
    public Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException {
        return null;
    }

//    private P parser;
//    private final List<G> geometries = new ArrayList<>();
//    protected String entityClass;
//
//    public MultiParser(P parser){
//        this.parser = parser;
//    }
//
//
//    @Override
//    public G read(StreamTokenizer tokenizer) throws IOException, ParseException {
//        G myInstance = (G) Class.forName(this.entityClass).getDeclaredConstructor().newInstance();
////        PointParser pointParser = new PointParser();
////        List<Point> points = new ArrayList<>();
//
//        do {
//            geometries.add(parser.read(tokenizer));
//        } while(this.getNextValidToken(tokenizer).equalsIgnoreCase(Constants.COMMA));
//
//        return new MultiPoint(geometries.toArray(new Point[0]));
//    }
}
