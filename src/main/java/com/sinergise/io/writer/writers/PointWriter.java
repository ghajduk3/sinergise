package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

public class PointWriter implements Writer<Point>{

    @Override
    public String write(Point point) {
        StringBuffer wktString = new StringBuffer(Constants.POINT + " ");
        if (point.isEmpty()){
            wktString.append(Constants.EMPTY);
            return wktString.toString();
        }
        wktString.append(writePoint(point));
        return wktString.toString();
    }

    protected String writePoint(Point point){
        return String.format(Constants.LEFT_PARENTHESES + "%.0f %.0f" + Constants.RIGHT_PARENTHESES,point.getX(), point.getY());
    }

}
