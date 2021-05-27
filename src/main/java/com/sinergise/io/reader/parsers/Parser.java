package com.sinergise.io.reader.parsers;

import com.sinergise.geometry.Geometry;
import com.sinergise.io.utils.Constants;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public abstract class Parser {
    public abstract Geometry read(StreamTokenizer tokenizer) throws IOException, ParseException;


    protected String getNextValidToken(StreamTokenizer tokenizer) throws IOException, ParseException {
        int tokenType = tokenizer.nextToken();
        switch (tokenType){
            case StreamTokenizer.TT_WORD:
                String word = tokenizer.sval;
                if (word.equalsIgnoreCase(Constants.EMPTY))
                    return Constants.EMPTY;
                return word;
            case 40:
                return Constants.LEFT_PARENTHESES;
            case 41:
                return Constants.RIGHT_PARENTHESES;
            case 44:
                return Constants.COMMA;
        }
        throw new ParseException("Geometry type expected", tokenizer.lineno());
    }

    protected double getNextNumber(StreamTokenizer tokenizer) throws ParseException, IOException {
        int tokenType = tokenizer.nextToken();

        switch (tokenType) {
            case StreamTokenizer.TT_WORD:
                try {
                    return Double.parseDouble(tokenizer.sval);
                } catch (NumberFormatException e){
                    throw new ParseException("Invalid number parsed : " + tokenizer.sval,tokenizer.lineno());
                }
        }
        throw new ParseException("Coordinate number expected", tokenizer.lineno());
    }

    protected  List<Double> getCoordinates(StreamTokenizer tokenizer) throws IOException, ParseException {
        List<Double> coords = new ArrayList<>();

        do {
            double[] coordinates = getCoordinate(tokenizer);
            coords.add(coordinates[0]);
            coords.add(coordinates[1]);
        } while (getNextValidToken(tokenizer).equalsIgnoreCase(","));

        return coords;
    }

    protected double[] getCoordinate(StreamTokenizer tokenizer) throws IOException, ParseException {
        double coordinateX = getNextNumber(tokenizer);
        double coordinateY = getNextNumber(tokenizer);
        return new double[]{coordinateX, coordinateY};
    }

    protected double[] convertToArray(List<Double> list) {
        double[] converted = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            converted[i] = list.get(i);
        }
        return converted;
    }
}
