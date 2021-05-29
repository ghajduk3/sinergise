package com.sinergise.io.utils.exceptions;

import java.text.ParseException;

public class NumberParseException extends ParseException {

    public NumberParseException(String message, int offset){
        super(message, offset);
    }
}
