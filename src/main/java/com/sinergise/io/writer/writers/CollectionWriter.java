package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.writer.WKTWriterFactory;

public class CollectionWriter implements Writer<GeometryCollection> {
    @Override
    public String write(GeometryCollection geometry) {
        StringBuffer wktString = new StringBuffer(Constants.LINESTRING + " ");
        if (geometry.isEmpty()){
            wktString.append(Constants.EMPTY);
            return wktString.toString();
        }
        wktString.append(this.writeGeometryCollection(geometry));

        return wktString.toString();
    }

    protected String writeGeometryCollection(GeometryCollection geometryCollection){
        String wktGeometryCollectinString = Constants.LEFT_PARENTHESES;

        for (int index=0; index < geometryCollection.size(); index++){
            Geometry currentGeometry = geometryCollection.get(index);
            Writer writer = WKTWriterFactory.getWriter(currentGeometry);
            wktGeometryCollectinString += writer.write(currentGeometry);
            if (index != geometryCollection.size()-1){
                wktGeometryCollectinString += ", ";
            }
        }
        wktGeometryCollectinString += Constants.RIGHT_PARENTHESES;

        return wktGeometryCollectinString;
    }
}
