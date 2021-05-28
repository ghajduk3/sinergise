package com.sinergise.io.writer.writers;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Constants;

public class MultiPolygonWriter implements Writer<MultiPolygon> {

    public String write(MultiPolygon multiPolygon){
        StringBuffer wktString = new StringBuffer(Constants.MULTIPOLYGON + " ");
        if (multiPolygon.isEmpty()){
            wktString.append(Constants.EMPTY);
            return wktString.toString();
        }
        wktString.append(this.writeMultiPolygon(multiPolygon));
        return wktString.toString();
    }

    protected String writeMultiPolygon(MultiPolygon multiPolygon){ ;
        String wktMultiPolygonString = Constants.LEFT_PARENTHESES;

        for (Polygon polygon : multiPolygon) {
            wktMultiPolygonString += PolygonWriter.writePolygon(polygon);
            wktMultiPolygonString += ", ";
        }
        wktMultiPolygonString += Constants.RIGHT_PARENTHESES;

        return wktMultiPolygonString;
    }
}