package com.sinergise.io.utils.exceptions;

import java.text.ParseException;

public class TokenParseException extends ParseException {

    public TokenParseException(String message, int offset){
        super(message, offset);
    }
}
