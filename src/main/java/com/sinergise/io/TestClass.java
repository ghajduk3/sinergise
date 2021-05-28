package com.sinergise.io;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.reader.WKTReader;
import com.sinergise.io.writer.WKTWriter;

import javax.sound.sampled.Line;

public class TestClass {

    public static void main(String[] args) {

        Point p = new Point(4,6);
        Point p1 = new Point(6,9);
        String point = "POINT (30 10)";
        String point1 = "POINT (50 40)";
        LineString lineString = new LineString(new double[]{35,10,45,45,15,40,10,20,35,10});
        Polygon polygon = new Polygon(lineString, new LineString[]{});
        WKTWriter writer = new WKTWriter();
        String wktString = writer.write(lineString);
        System.out.println(wktString);
        String wktStringPoly = writer.write(polygon);
        System.out.println(wktStringPoly);
        MultiPoint multiPoint = new MultiPoint(new Point[]{p,p1});
        String wktStringMultiPoint = writer.write(multiPoint);
        System.out.println(wktStringMultiPoint);
    }

}
