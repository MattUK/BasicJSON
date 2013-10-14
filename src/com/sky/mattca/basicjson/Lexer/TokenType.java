/***
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Matthew William Carter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.sky.mattca.basicjson.Lexer;

public enum TokenType {
    WHITESPACE,
    INTEGER_LITERAL,
    FLOAT_LITERAL,
    STRING_LITERAL,
    ASSIGNMENT(":"),
    SEPARATOR(","),
    OBJECT_OPEN("{"),
    OBJECT_CLOSE("}"),
    ARRAY_OPEN("["),
    ARRAY_CLOSE("]"),
    TRUE("true"),
    FALSE("false"),
    NULL("null"),
    NONE;

    public String tokenValue;
    public boolean allowMatching;

    TokenType() {
        allowMatching = false;
    }

    TokenType(String tokenValue) {
        this.tokenValue = tokenValue;
        allowMatching = true;
    }

    public boolean match(String value) {
        if (allowMatching && value.length() >= length()) {
            String toMatch = value.substring(0, length());
            return (tokenValue.equals(toMatch));
        }
        return false;
    }

    public int length() {
        if (allowMatching) {
            return tokenValue.length();
        }
        return 0;
    }

}
