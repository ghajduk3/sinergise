package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.io.utils.Constants;

public class MultiLineStringWriter extends LineStringWriter {

    public String write(Geometry multiLineString) {
        return Constants.MULTILINESTRING + " " + this.writeMultiLineString((MultiLineString) multiLineString);
    }

    protected String writeMultiLineString(MultiLineString multiLineString) {
        ;
        StringBuilder wktMultiLineString = new StringBuilder();
        if (multiLineString.isEmpty()) {
            wktMultiLineString.append(Constants.EMPTY);
            return wktMultiLineString.toString();
        }
        wktMultiLineString.append(Constants.LEFT_PARENTHESES);

        for (int index = 0; index < multiLineString.size(); index++) {
            wktMultiLineString.append(this.writeLineString(multiLineString.get(index)));
            if (index != multiLineString.size() - 1) {
                wktMultiLineString.append(Constants.COMMA + " ");
            }
        }
        wktMultiLineString.append(Constants.RIGHT_PARENTHESES);

        return wktMultiLineString.toString();
    }
}
