package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

public class PointWriter extends Writer{

    private Point point;

    public PointWriter(Geometry point){
        this.point = (Point) point;
    }
    @Override
    public String write() {
        StringBuffer wktString = new StringBuffer();
        if (point.isEmpty()){
            return wktString.toString();
        }
        wktString.append(String.format(Constants.POINT + " " + Constants.LEFT_PARENTHESES + "%.0f %.0f" + Constants.RIGHT_PARENTHESES,this.point.getX(), this.point.getY()));

        return wktString.toString();
    }
}
