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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TokenString {

    private List<Token> tokenList;
    public int line = 0;

    public TokenString() {
        tokenList = new ArrayList<>();
    }

    public TokenString createCopy() {
        TokenString newString = new TokenString();
        newString.tokenList = new ArrayList<>(this.tokenList);
        newString.line = this.line;
        return newString;
    }

    public TokenString append(TokenString string) {
        tokenList.addAll(string.toList());
        return this;
    }

    public TokenString addToBeginning(TokenString string) {
        tokenList.addAll(0, string.toList());
        return this;
    }

    public TokenString append(Token token) {
        tokenList.add(token);
        return this;
    }

    public TokenString addToBeginning(Token token) {
        tokenList.add(0, token);
        return this;
    }

    public TokenString trimLeft() {
        if (empty()) return this;

        while (tokenList.size() > 0 && tokenList.get(0).type == TokenType.WHITESPACE) {
            tokenList.remove(0);
        }
        return this;
    }

    public TokenString trimRight() {
        if (empty()) return this;

        while (tokenList.get(tokenList.size() - 1).type == TokenType.WHITESPACE) {
            tokenList.remove(tokenList.size() - 1);
        }
        return this;
    }

    public TokenString trim() {
        if (empty()) return this;

        trimLeft();
        trimRight();
        return this;
    }

    public TokenString removeWhitespace() {
        boolean finished = false;

        if (empty()) {
            return this;
        }

        while (!finished) {
            for (int i = 0; i < tokenList.size(); i++) {
                if (tokenList.get(i).type == TokenType.WHITESPACE) {
                    tokenList.remove(i);
                    break;
                } else {
                    if (i == tokenList.size() - 1) {
                        finished = true;
                    }
                }
            }
        }

        return this;
    }

    public boolean match(TokenType... args) {
        if (empty()) {
            return false;
        }
        try {
            for (int i = 0; i < args.length; i++) {
                if (tokenList.get(i).type != args[i]) {
                    return false;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public boolean contentMatch(String... args) {
        for (int i = 0; i < args.length; i++) {
            if (tokenList.get(i).contents.toUpperCase().equals(args[i].toUpperCase()) == false) {
                return false;
            }
        }
        return true;
    }

    public Token consume() {
        return (tokenList.size() > 0) ? tokenList.remove(0) : null;
    }

    public Token peek() {
        return (tokenList.size() > 0) ? tokenList.get(0) : null;
    }

    public Token get(int index) {
        return tokenList.get(index);
    }

    public Token remove(int index) {
        return tokenList.remove(index);
    }

    public Token getFirstExcludingWhitespace() {
        for (int i = 0; i < tokenList.size(); i++) {
            if (tokenList.get(i).type != TokenType.WHITESPACE) {
                return tokenList.get(i);
            }
        }
        return null;
    }

    public boolean empty() {
        return !(tokenList.size() > 0);
    }

    public int size() {
        return tokenList.size();
    }

    public List<Token> toList() {
        return tokenList;
    }

    public TokenString skip(TokenType type) {
        TokenString copy = this.createCopy();
        while (copy.match(type)) {
            copy.consume();
        }
        return copy;
    }

    public Stream<Token> stream() {
        return tokenList.stream();
    }

    @Override
    public String toString() {
        return "TokenString{" +
                "tokenList=" + tokenList +
                ", line=" + line +
                '}';
    }
}
