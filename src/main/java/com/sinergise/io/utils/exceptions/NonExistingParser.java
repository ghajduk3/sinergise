package com.sinergise.io.utils.exceptions;

import java.text.ParseException;

public class NonExistingParser extends ParseException {

    public NonExistingParser(String message, int offset){
        super(message, offset);
    }
}
