package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.io.utils.Constants;

public class LineStringWriter extends Writer{
    /**
     *
     * @param lineString
     * @return
     */
    @Override
    public String write(Geometry lineString) {
        return Constants.LINESTRING + " " + this.writeLineString((LineString) lineString);
    }

    /**
     *
     * @param lineString
     * @return
     */
    protected String writeLineString(LineString lineString) {
        StringBuilder wktLineString = new StringBuilder();

        if (lineString.isEmpty()) {
            wktLineString.append(Constants.EMPTY);
            return wktLineString.toString();
        }

        wktLineString.append(Constants.LEFT_PARENTHESES);
        int numCoordinates = lineString.getNumCoords();
        for (int index = 0; index < numCoordinates; index++) {
            wktLineString.append(this.format(lineString.getX(index), lineString.getY(index)));
            if (index != numCoordinates - 1) {
                wktLineString.append(Constants.COMMA + " ");
            }
        }
        wktLineString.append(Constants.RIGHT_PARENTHESES);
        return wktLineString.toString();
    }
}
