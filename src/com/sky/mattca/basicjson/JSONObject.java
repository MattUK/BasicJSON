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

import java.lang.reflect.Field;
import java.util.List;

public class JSONObject extends JSONElement {

    private List<JSONElement> values;

    public JSONObject(String name) {
        super(name);
    }

    public boolean contains(String valueName) {
        for (JSONElement e: values) {
            if (e.getName().equals(valueName)) {
                return true;
            }
        }
        return false;
    }

    public JSONElement get(String valueName) {
        for (JSONElement e: values) {
            if (e.getName().equals(valueName)) {
                return e;
            }
        }
        return null;
    }

    public void add(JSONElement value) {
        values.add(value);
    }

    public boolean remove(String valueName) {
        int removalIndex = -1;

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getName().equals(valueName)) {
                removalIndex = i;
            }
        }

        if (removalIndex > -1) {
            values.remove(removalIndex);
            return true;
        }
        return false;
    }

    public static JSONElement fromJavaObject(Object object) throws IllegalAccessException {
        for (Field f: object.getClass().getFields()) {
            if (f.getType() == String.class) {
                return new JSONElement(f.getName(), f.get(object));
            } else if (f.getType() == byte.class || f.getType() == short.class || f.getType() == int.class || f.getType() == float.class ||
                    f.getType() == long.class || f.getType() == double.class || f.getType() == char.class) {
                if (f.getType() == byte.class) {
                    return new JSONElement(f.getName(), f.getByte(object));
                } else if (f.getType() == short.class) {
                    return new JSONElement(f.getName(), f.getShort(object));
                } else if (f.getType() == int.class) {
                    return new JSONElement(f.getName(), f.getInt(object));
                } else if (f.getType() == float.class) {
                    return new JSONElement(f.getName(), f.getFloat(object));
                } else if (f.getType() == long.class) {
                    return new JSONElement(f.getName(), f.getLong(object));
                } else if (f.getType() == double.class) {
                    return new JSONElement(f.getName(), f.getDouble(object));
                } else if (f.getType() == char.class) {
                    return new JSONElement(f.getName(), f.getChar(object));
                }
            } else if (f.getType() == Byte.class || f.getType() == Short.class || f.getType() == Integer.class || f.getType() == Float.class ||
                    f.getType() == Long.class || f.getType() == Double.class || f.getType() == Character.class || f.getType() == Number.class) {
                return new JSONElement(f.getName(), f.get(object));
            } else if (f.getType() == boolean.class || f.getType() == Boolean.class) {
                if (f.getType() == boolean.class) {
                    return new JSONElement(f.getName(), f.getBoolean(object));
                } else {
                    return new JSONElement(f.getName(), f.get(object));
                }
            } else if (f.getType() == Object.class) {
                return JSONObject.fromJavaObject(f.get(object));
            } else if (f.getType().isArray()) {
                return new JSONElement(f.getName(), f.get(object));
            }
        }

        return null;
    }

}
