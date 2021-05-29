package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.io.utils.Constants;

public class MultiPointWriter extends PointWriter {

    public String write(Geometry multiPoint) {
        return Constants.MULTIPOINT + " " + this.writeMultiPoint((MultiPoint) multiPoint);
    }

    protected String writeMultiPoint(MultiPoint multiPoint) {
        StringBuilder wktMultiPointString = new StringBuilder();
        if (multiPoint.isEmpty()) {
            wktMultiPointString.append(Constants.EMPTY);
            return wktMultiPointString.toString();
        }

        wktMultiPointString.append(Constants.LEFT_PARENTHESES);

        for (int index = 0; index < multiPoint.size(); index++) {
            wktMultiPointString.append(this.writePoint(multiPoint.get(index)));
            if (index != multiPoint.size() - 1) {
                wktMultiPointString.append(Constants.COMMA + " ");
            }
        }
        wktMultiPointString.append(Constants.RIGHT_PARENTHESES);
        return wktMultiPointString.toString();
    }
}
