package com.sky.mattca.basicjson.Tests;

import com.sky.mattca.basicjson.Lexer.Lexer;
import com.sky.mattca.basicjson.Lexer.TokenString;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Matt
 * Date: 13/10/13
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
public class LexerTest {
    @Test
    public void testStart() throws Exception {
        Lexer lexer = new Lexer();
        String[] testStrings = ("{\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"age\": 25,\n" +
                "    \"address\": {\n" +
                "        \"streetAddress\": \"21 2nd Street\",\n" +
                "        \"city\": \"New York\",\n" +
                "        \"state\": \"NY\",\n" +
                "        \"postalCode\": 10021\n" +
                "    },\n" +
                "    \"phoneNumbers\": [\n" +
                "        {\n" +
                "            \"type\": \"home\",\n" +
                "            \"number\": \"212 555-1234\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"fax\",\n" +
                "            \"number\": \"646 555-4567\"\n" +
                "        }\n" +
                "    ]\n" +
                "}").split("\n");
        for (String s: testStrings) {
            System.out.println(s);
        }

        Arrays.asList(testStrings).forEach(lexer::addSourceLine);

        List<TokenString> stringList = lexer.start();
        for (int i = 0; i < stringList.size(); i++) {
            stringList.set(i, stringList.get(i).removeWhitespace());
        }

        stringList.forEach(System.out::println);
    }
}
