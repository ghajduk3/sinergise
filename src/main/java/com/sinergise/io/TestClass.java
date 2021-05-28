package com.sinergise.io;
import com.sinergise.geometry.*;
import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;
import com.sinergise.io.reader.WKTReader;
import com.sinergise.io.writer.WKTWriter;

import javax.sound.sampled.Line;

import java.io.IOException;
import java.text.ParseException;

public class TestClass {

    public static void main(String[] args) throws IOException, ParseException {

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

        String linestring = "LINESTRING (30 10 , 40 50 , 60 30)";
        String point = "POINT (30 10)";
        String polygon2 = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30), (20 30, 35 35, 30 20, 20 30))";
        String multipoint = "MULTIPOINT ((10 40) , (40 30) , (20 20) , (30 10))";
        String multilinestring = "MULTILINESTRING ((10 10, 20 20, 10 40),\n" +
                "(40 40, 30 30, 40 20, 30 10))";
        String multipolygon = "MULTIPOLYGON (((30 20, 45 40, 10 40, 30 20)),\n" +
                "((15 5, 40 10, 10 20, 5 10, 15 5)))";
        String geometrycollection = "GEOMETRYCOLLECTION (POINT (10 30) ,\n" +
                "LINESTRING (10 10, 20 20, 10 40),\n" +
                "POLYGON ((40 40, 20 45, 45 30, 40 40)))";
        String geometrycollectionempty = "GEOMETRYCOLLECTION EMPTY";
        WKTReader reader = new WKTReader();
        Geometry newp = reader.read(point);
        System.out.println(newp);
    }

}
