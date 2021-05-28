package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

import javax.sound.sampled.Line;

public class LineStringWriter implements Writer<LineString> {

    @Override
    public String write(LineString lineString) {
        StringBuffer wktString = new StringBuffer(Constants.LINESTRING + " ");
        if (lineString.isEmpty()){
            wktString.append(Constants.EMPTY);
            return wktString.toString();
        }
        wktString.append(writeCoordinateSequence(lineString));

        return wktString.toString();
    }

    public static String writeCoordinateSequence(LineString lineString){
        String wktCoordinateSequence = Constants.LEFT_PARENTHESES;
        int numCoordinates = lineString.getNumCoords();
        for (int index = 0; index < numCoordinates; index++){
            wktCoordinateSequence += String.format("%.0f %.0f", lineString.getX(index), lineString.getY(index));
            if(index != numCoordinates -1){
                wktCoordinateSequence += ", ";
            }
        }
        wktCoordinateSequence += Constants.RIGHT_PARENTHESES;
        return wktCoordinateSequence;
    }
}
