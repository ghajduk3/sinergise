package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Constants;

public class PolygonWriter extends LineStringWriter {
    /**
     *
     * @param polygon
     * @return
     */
    public String write(Geometry polygon) {
        return Constants.POLYGON + " " + this.writePolygon((Polygon) polygon);
    }

    /**
     *
     * @param polygon
     * @return
     */
    public String writePolygon(Polygon polygon) {
        StringBuilder wktPolygonString = new StringBuilder();
        if (polygon.isEmpty()) {
            wktPolygonString.append(Constants.EMPTY);
            return wktPolygonString.toString();
        }
        wktPolygonString.append(Constants.LEFT_PARENTHESES);
        wktPolygonString.append(this.writeLineString(polygon.getOuter()));

        int numHoles = polygon.getNumHoles();
        for (int index = 0; index < numHoles; index++) {
            wktPolygonString.append(Constants.COMMA + " ");
            wktPolygonString.append(this.writeLineString(polygon.getHole(index)));
        }
        wktPolygonString.append(Constants.RIGHT_PARENTHESES);
        return wktPolygonString.toString();

    }

}
