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

public class JSONElement {

    public enum ElementType {
        STRING,
        NUMBER,
        OBJECT,
        BOOLEAN,
        NULL,
        ARRAY
    }

    private String name;
    private ElementType type;
    private Object value;

    /**
     * Create a new JSON element with a name and value.
     * @param name The name of the new element.
     * @param value The value assigned to the previous name.
     */
    public JSONElement(String name, Object value) {
        setName(name);
        set(value);
    }

    /**
     * Create a new JSON element with just a name.
     * @param name The name of the new element.
     */
    protected JSONElement(String name) {
        setName(name);
        type = ElementType.OBJECT;
        set(null);
    }

    /**
     * @return The name of this JSON element.
     */
    public String getName() {
        return name;
    }

    public ElementType getElementType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public<T> void set(T value) throws ClassCastException {
        if (value instanceof String) {
            type = ElementType.STRING;
            this.value = value;
        } else if (value instanceof Number) {
            type = ElementType.NUMBER;
            this.value = ((Number) value).doubleValue();
        } else if (value instanceof JSONObject) {
            type = ElementType.OBJECT;
            this.value = value;
        } else if (value instanceof JSONElement[]) {
            type = ElementType.ARRAY;
            this.value = value;
        } else if (value instanceof Boolean) {
            type = ElementType.BOOLEAN;
            this.value = value;
        } else if (value == null) {
            type = ElementType.NULL;
        } else {
            throw new ClassCastException("Cannot convert '" + value.getClass().getName() + "' to 'String', 'Number', 'Boolean', 'JSON Object', or 'JSON Array'.");
        }
    }

    public<T> T get() throws ClassCastException {
        if (type == ElementType.NULL) {
            return null;
        }
        return (T)value;
    }

    public String asString() throws ClassCastException {
        if (type == ElementType.STRING) {
            return get();
        } else {
            throw new ClassCastException("Cannot convert '" + type + "' to 'String'.");
        }
    }

    public double asNumber() throws ClassCastException {
        if (type == ElementType.NUMBER) {
            return get();
        } else {
            throw new ClassCastException("Cannot convert '" + type + "' to 'Number'.");
        }
    }

    public JSONObject asObject() throws ClassCastException {
        if (type == ElementType.OBJECT) {
            return get();
        } else {
            throw new ClassCastException("Cannot convert '" + type + "' to 'Object (JSONElement)'.");
        }
    }

    public JSONElement[] asArray() throws ClassCastException {
        if (type == ElementType.ARRAY) {
            return get();
        } else {
            throw new ClassCastException("Cannot convert '" + type + "' to 'Array'.");
        }
    }

    public boolean asBoolean() throws ClassCastException {
        if (type == ElementType.BOOLEAN) {
            return get();
        } else {
            throw new ClassCastException("Cannot convert '" + type + "' to 'Boolean'.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JSONElement that = (JSONElement) o;

        if (!name.equals(that.name)) return false;
        if (type != that.type) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JSONElement{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
