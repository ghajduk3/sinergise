package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.io.utils.Constants;

public class LineStringWriter extends Writer{

    @Override
    public String write(Geometry lineString) {
        return Constants.LINESTRING + " " + this.writeLineString((LineString) lineString);
    }

    protected String writeLineString(LineString lineString) {
        StringBuilder wktLineString = new StringBuilder(Constants.LEFT_PARENTHESES);

        if (lineString.isEmpty()) {
            wktLineString.append(Constants.EMPTY);
            return wktLineString.toString();
        }

        int numCoordinates = lineString.getNumCoords();
        for (int index = 0; index < numCoordinates; index++) {
            wktLineString.append(String.format("%.0f %.0f", lineString.getX(index), lineString.getY(index)));
            if (index != numCoordinates - 1) {
                wktLineString.append(Constants.COMMA + " ");
            }
        }
        wktLineString.append(Constants.RIGHT_PARENTHESES);
        return wktLineString.toString();
    }
}
