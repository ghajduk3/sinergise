package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.io.utils.Constants;

public class MultiPolygonWriter extends PolygonWriter {
    /**
     *
     * @param multiPolygon
     * @return
     */
    @Override
    public String write(Geometry multiPolygon) {
        return Constants.MULTIPOLYGON + " " + this.writeMultiPolygon((MultiPolygon) multiPolygon);
    }

    /**
     *
     * @param multiPolygon
     * @return
     */
    protected String writeMultiPolygon(MultiPolygon multiPolygon) {
        ;
        StringBuilder wktMultiPolygonString = new StringBuilder();
        if (multiPolygon.isEmpty()) {
            wktMultiPolygonString.append(Constants.EMPTY);
            return wktMultiPolygonString.toString();
        }
        wktMultiPolygonString.append(Constants.LEFT_PARENTHESES);

        for (int index = 0; index < multiPolygon.size(); index++) {
            wktMultiPolygonString.append(this.writePolygon(multiPolygon.get(index)));
            if (index != multiPolygon.size() - 1) {
                wktMultiPolygonString.append(Constants.COMMA + " ");
            }
        }
        wktMultiPolygonString.append(Constants.RIGHT_PARENTHESES);
        return wktMultiPolygonString.toString();
    }
}
