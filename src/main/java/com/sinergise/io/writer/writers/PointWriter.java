package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

public class PointWriter extends Writer{
    /**
     *
     * @param point
     * @return
     */
    @Override
    public String write(Geometry point) {
        return Constants.POINT + " " + this.writePoint((Point) point);
    }

    /**
     *
     * @param point
     * @return
     */
    protected String writePoint(Point point) {
        StringBuilder wktPointString = new StringBuilder();
        if (point.isEmpty()) {
            wktPointString.append(Constants.EMPTY);
            return wktPointString.toString();
        }
        wktPointString.append(Constants.LEFT_PARENTHESES).append(this.format(point.getX(), point.getY())).append(Constants.RIGHT_PARENTHESES);
        return wktPointString.toString();
    }
}
