package com.sinergise.io.writer.writers;

import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Constants;

import java.util.Iterator;

public class MultiPointWriter implements Writer<MultiPoint> {


    public String write(MultiPoint multiPoint){
        System.out.println("Isasa");
        StringBuffer wktString = new StringBuffer(Constants.MULTIPOINT + " ");
        if (multiPoint.isEmpty()){
            wktString.append(Constants.EMPTY);
            return wktString.toString();
        }
        wktString.append(this.writeMultiPoint(multiPoint));
        return wktString.toString();
    }

    protected String writeMultiPoint(MultiPoint multiPoint){
        PointWriter pointWriter = new PointWriter();
        String wktMultiPoint = Constants.LEFT_PARENTHESES;
        Iterator<Point> multiPointIterator = multiPoint.iterator();

        while(multiPointIterator.hasNext()){
            Object point =  multiPointIterator.next();
            wktMultiPoint += pointWriter.writePoint((Point) point);
            wktMultiPoint += ", ";
        }
        wktMultiPoint += Constants.RIGHT_PARENTHESES;

        return wktMultiPoint;
    }
}
