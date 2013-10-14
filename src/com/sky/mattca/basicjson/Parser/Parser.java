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

package com.sky.mattca.basicjson.Parser;

import com.sky.mattca.basicjson.JSONElement;
import com.sky.mattca.basicjson.JSONObject;
import com.sky.mattca.basicjson.Lexer.TokenString;
import com.sky.mattca.basicjson.Lexer.TokenType;

public class Parser {

    private TokenString tokenStream;

    public Parser() {

    }

    private Object parseValue() {
        switch (tokenStream.peek().type) {
            case STRING_LITERAL:
                if (tokenStream.match(TokenType.STRING_LITERAL)) {
                    return tokenStream.consume().contents;
                }
            case INTEGER_LITERAL:
                break;
            case FLOAT_LITERAL:
                break;
            case TRUE:
                break;
            case FALSE:
                break;
            case NULL:
                break;
            default:
                break;
        }
        return null;
    }

    private JSONElement parseArray(JSONObject parent) {
        return null;
    }

    private JSONElement parseObject(JSONObject parent) {
        return null;
    }

}
