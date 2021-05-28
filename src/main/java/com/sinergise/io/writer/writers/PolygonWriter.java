package com.sinergise.io.writer.writers;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.utils.Constants;

public class PolygonWriter implements Writer<Polygon>{

    @Override
    public String write(Polygon polygon) {
        StringBuffer wktString = new StringBuffer(Constants.POLYGON + " ");
        if (polygon.isEmpty()){
            wktString.append(Constants.EMPTY);
            return wktString.toString();
        }
        wktString.append(this.writePolygon(polygon));
        return wktString.toString();
    }

    public static String writePolygon(Polygon polygon){
        String wktPolygon = Constants.LEFT_PARENTHESES;
//        Add Outer linestring
        wktPolygon += LineStringWriter.writeCoordinateSequence(polygon.getOuter());
        int numHoles = polygon.getNumHoles();
        for(int index=0; index<numHoles; index++){
            wktPolygon += Constants.COMMA + " ";
            wktPolygon += LineStringWriter.writeCoordinateSequence(polygon.getHole(index));
        }
        wktPolygon += Constants.RIGHT_PARENTHESES;
        return wktPolygon;

    }

}
