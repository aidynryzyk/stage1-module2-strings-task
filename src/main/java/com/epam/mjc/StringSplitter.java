package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        for (String delim:
             delimiters) {
            source = source.replaceAll(delim, "###");
        }
        System.out.println(source);
        List<String> words = new ArrayList<>(List.of(source.split("###")));
        words.removeIf(String::isEmpty);
        return words;
    }
}
