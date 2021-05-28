package com.sinergise.io.writer.writers;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;
import com.sinergise.io.utils.Constants;

import javax.sound.sampled.Line;
import java.util.Iterator;

public class MultiLineStringWriter implements Writer<MultiLineString>{

    public String write(MultiLineString multiLineString){
        StringBuffer wktString = new StringBuffer(Constants.MULTILINESTRING + " ");
        if (multiLineString.isEmpty()){
            wktString.append(Constants.EMPTY);
            return wktString.toString();
        }
        wktString.append(this.writeMultiLineString(multiLineString));
        return wktString.toString();
    }

    protected String writeMultiLineString(MultiLineString multiLineString){ ;
        String wktMultiLineString = Constants.LEFT_PARENTHESES;

        for (int index=0; index < multiLineString.size(); index++){
            wktMultiLineString += LineStringWriter.writeCoordinateSequence(multiLineString.get(index));
            if (index != multiLineString.size()-1){
                wktMultiLineString += ", ";
            }
        }
        wktMultiLineString += Constants.RIGHT_PARENTHESES;

        return wktMultiLineString;
    }
}
