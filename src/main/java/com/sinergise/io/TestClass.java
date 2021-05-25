package com.sinergise.io;
import com.sinergise.geometry.Point;
import com.sinergise.io.reader.WKTReader;

public class TestClass {

    public static void main(String[] args) {

        Point p = new Point(4,6);
        String point = "POINT (30 10)";
        WKTReader reader = new WKTReader();
        reader.read(point);
    }

}
