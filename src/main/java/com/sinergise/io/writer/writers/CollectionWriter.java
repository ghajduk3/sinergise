package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.io.utils.Constants;
import com.sinergise.io.utils.exceptions.NonExistingWriter;
import com.sinergise.io.writer.WKTWriterFactory;

public class CollectionWriter extends Writer {
    /**
     *
     * @param geometry
     * @return
     * @throws NonExistingWriter
     */
    @Override
    public String write(Geometry geometry) throws NonExistingWriter {
        return Constants.GEOMETRYCOLLECTION + " " + this.writeGeometryCollection((GeometryCollection) geometry);
    }

    /**
     *
     * @param geometryCollection
     * @return
     * @throws NonExistingWriter
     */
    protected String writeGeometryCollection(GeometryCollection geometryCollection) throws NonExistingWriter {
        StringBuilder wktGeometryCollectionString = new StringBuilder();
        if (geometryCollection.isEmpty()) {
            wktGeometryCollectionString.append(Constants.EMPTY);
            return wktGeometryCollectionString.toString();
        }
        wktGeometryCollectionString.append(Constants.LEFT_PARENTHESES);

        for (int index = 0; index < geometryCollection.size(); index++) {
            Geometry currentGeometry = geometryCollection.get(index);
            Writer writer = WKTWriterFactory.getWriter(currentGeometry);
            wktGeometryCollectionString.append(writer.write(currentGeometry));
            if (index != geometryCollection.size() - 1) {
                wktGeometryCollectionString.append(Constants.COMMA + " ");
            }
        }
        wktGeometryCollectionString.append(Constants.RIGHT_PARENTHESES);

        return wktGeometryCollectionString.toString();
    }
}
