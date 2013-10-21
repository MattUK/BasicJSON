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

package com.sky.mattca.basicjson;

import java.util.ArrayList;
import java.util.List;

public class Handler {

    public static class BuildError {
        public int errorID;
        public int line, position;
        public String[] values;

        public BuildError(int errorID, int line, int position) {
            this.errorID = errorID;
            this.line = line;
            this.position = position;
            this.values = new String[0];
        }

        public BuildError(int errorID, int line, int position, String... values) {
            this(errorID, line, position);
            this.values = values;
        }
    }

    public static final String[] errorMessages = {
            "%d errors were found during build, check output for details.",
            "Unterminated string literal",
            "Unexpected '.'",
            "Unexpected character '%s'",
            "Expected digit to follow decimal point",
            "Incorrect usage of '=', use ':' for assignment",
            "Expected identifier",
            "Expected 'true', 'false', or 'null', found %s",
            "Expected JSON value, found %s"
    };

    private static List<BuildError> errors = new ArrayList<>();

    public static void reportError(BuildError error) {
        errors.add(error);
    }

    public static void reportWarning(String warning, int line, int position) {
        System.out.println("Warning: " + warning + " found at position " + position + ", line " + line + ".");
    }

    public static void reportStatus(String status) {
        System.out.println("Status: " + status);
    }

    private static void printValueError(BuildError error) {
        String copy = new String(errorMessages[error.errorID]);
        int i = 0;
        while (copy.indexOf("%s") != -1) {
            String s1 = copy.substring(0, copy.indexOf("%s"));
            String s2 = copy.substring(copy.indexOf("%s") + 2);
            copy = s1 + error.values[i] + s2;

            i++;
        }
        System.out.printf("Error: " + copy + ". Found near position %d, line %d.\n", error.position, error.line + 1);
    }

    public static void abortIfErrors() {
        if (errors.size() > 0) {
            for (BuildError error: errors) {
                if (error.values.length > 0) {
                    printValueError(error);
                } else {
                    System.out.printf("Error: " + errorMessages[error.errorID] + ". Found near position %d, line %d.\n", error.position, error.line + 1);
                }
            }
            System.out.printf(errorMessages[0], errors.size());

            System.exit(-1);
        }
    }

}
