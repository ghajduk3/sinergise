package com.sinergise.io.utils;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ParserUtils {

    public static StreamTokenizer constructTokenizer(StringReader reader) {
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        tokenizer.resetSyntax();
        tokenizer.wordChars('a', 'z');
        tokenizer.wordChars('A', 'Z');
        tokenizer.wordChars(128 + 32, 255);
        tokenizer.wordChars('0', '9');
        tokenizer.wordChars('-', '-');
        tokenizer.wordChars('+', '+');
        tokenizer.wordChars('.', '.');
        tokenizer.whitespaceChars(0, ' ');
        tokenizer.commentChar('#');

        return tokenizer;
    }



}
