package com.sinergise.io;
import com.sinergise.geometry.*;
import com.sinergise.io.reader.WKTReader;
import com.sinergise.io.writer.WKTWriter;

import javax.sound.sampled.Line;

public class TestClass {

    public static void main(String[] args) {

        Point p = new Point(4,6);
        Point p1 = new Point(6,9);
        LineString lineString = new LineString(new double[]{35,10,45,45,15,40,10,20,35,10});
        LineString hol1 = new LineString(new double[]{20,30, 35,35, 30,20, 20,30});

        Polygon polygon = new Polygon(lineString, new LineString[]{});
        Polygon polygon1 = new Polygon(lineString, new LineString[]{hol1});
        MultiPolygon multiPolygon = new MultiPolygon(new Polygon[]{polygon1,polygon});
        WKTWriter writer = new WKTWriter();

        String wktpoint = writer.write(p);
        System.out.println(wktpoint);

        String wktlinestring = writer.write(lineString);
        System.out.println(wktlinestring);

        String wktpolygon = writer.write(polygon);
        System.out.println(wktpolygon);

        String wktpolygon1 = writer.write(polygon1);
        System.out.println(wktpolygon1);

        String wktmultipolygon = writer.write(multiPolygon);
        System.out.println(wktmultipolygon);
    }

}
