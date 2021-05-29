package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

public class PointWriter extends Writer{

    @Override
    public String write(Geometry point) {
        return Constants.POINT + " " + this.writePoint((Point) point);
    }

    protected String writePoint(Point point) {
        StringBuilder wktPointString = new StringBuilder();
        if (point.isEmpty()) {
            wktPointString.append(Constants.EMPTY);
            return wktPointString.toString();
        }
        wktPointString.append(String.format(Constants.LEFT_PARENTHESES + "%.0f %.0f" + Constants.RIGHT_PARENTHESES, point.getX(), point.getY()));
        return wktPointString.toString();
    }
}
