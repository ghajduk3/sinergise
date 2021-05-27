package com.sinergise.io;
import com.sinergise.geometry.Point;
import com.sinergise.io.reader.WKTReader;
import com.sinergise.io.writer.WKTWriter;

public class TestClass {

    public static void main(String[] args) {

        Point p = new Point(4,6);
        String point = "POINT (30 10)";

        WKTWriter writer = new WKTWriter();
        String wktString = writer.write(p);
        System.out.println(wktString);
    }

}
