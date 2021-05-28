package com.sinergise.io.writer.writers;

import com.sinergise.geometry.Geometry;

public interface Writer<T extends Geometry>{

    public abstract String write(T geometry);

}
