package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

public class LineStringWriter extends Writer {
    private LineString lineString;

    public LineStringWriter(Geometry lineString){
        this.lineString = (LineString) lineString;
    }
    @Override
    public String write() {
        StringBuffer wktString = new StringBuffer();
        if (lineString.isEmpty()){
            return wktString.toString();
        }
        return wktString.toString();
    }
}
