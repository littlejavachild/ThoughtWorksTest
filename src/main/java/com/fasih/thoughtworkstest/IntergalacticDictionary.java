package com.fasih.thoughtworkstest;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class IntergalacticDictionary {
    private final Map<Character, Integer> romanToDecimal = new HashMap<Character, Integer>();
    private final Map<String, Character> alienToRoman = new HashMap<String, Character>();
    private final Map<String, Double> commodityCredits = new HashMap<String, Double>();
    private final String romanNumberRegex = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    private final Pattern romanNumberPattern = Pattern.compile(romanNumberRegex);

    {
        romanToDecimal.put('I', 1);
        romanToDecimal.put('V', 5);
        romanToDecimal.put('X', 10);
        romanToDecimal.put('L', 50);
        romanToDecimal.put('C', 100);
        romanToDecimal.put('D', 500);
        romanToDecimal.put('M', 1000);
    }

    // ------------------------------------------------------------------------------
    public void addTranslation(final String alien, final Character roman) {
        if (alien == null || roman == null) {
            return;
        }

        alienToRoman.put(alien, roman);
    }

    // ------------------------------------------------------------------------------
    public void addCredits(final String commodity, final Double credits) {
        if (commodity == null || credits == null) {
            return;
        }

        commodityCredits.put(commodity, credits);
    }

    // ------------------------------------------------------------------------------
    public Character getTranslation(final String alien) {
        if (alien == null) {
            return null;
        }

        return alienToRoman.get(alien);
    }

    // ------------------------------------------------------------------------------
    public Set<String> getTranslatedAlienWords() {
        return Collections.unmodifiableSet(alienToRoman.keySet());
    }

    // ------------------------------------------------------------------------------
    public Map<String, Character> getTranslatedWords() {
        return Collections.unmodifiableMap(alienToRoman);
    }

    // ------------------------------------------------------------------------------
    public Map<String, Double> getCreditsMap() {
        return Collections.unmodifiableMap(commodityCredits);
    }

    // ------------------------------------------------------------------------------
    public Set<String> getCommodities() {
        return Collections.unmodifiableSet(commodityCredits.keySet());
    }

    // ------------------------------------------------------------------------------
    public boolean isValidAlienNumber(String alienNumber) {

        /**
         * LOGIC:
         * a number "glob prok pish" is only valid if
         * the dictionary has seen "glob", "prok", and "pish" before
         */
        // replace any trailing spaces
        alienNumber = alienNumber.replaceAll("\\s$", "");

        // get the words as a set
        Set<String> words = new HashSet<String>();
        Collections.addAll(words, alienNumber.split("\\s"));

        // see if it is valid
        return getTranslatedAlienWords().containsAll(words);
    }

    // ------------------------------------------------------------------------------
    public boolean isValidRomanNumber(final String romanNumber) {
        return romanNumberPattern.matcher(romanNumber).matches();
    }

    // ------------------------------------------------------------------------------
    public String toRomanNumber(final String alienNumber) {
        if (isValidAlienNumber(alienNumber)) {
            String romanNumber = new String(alienNumber);
            Set<String> alienWords = getTranslatedAlienWords();
            for (String alienWord : alienWords) {
                romanNumber = romanNumber.replace(alienWord, getTranslation(alienWord) + "");
            }

            romanNumber = romanNumber.replaceAll("\\s", "");
            if (isValidRomanNumber(romanNumber)) {
                return romanNumber;
            }

            return null;
        }

        return null;
    }

    // ------------------------------------------------------------------------------
    public double toDecimal(final String alienNumber) {
        String roman = toRomanNumber(alienNumber);
        if (roman != null) {
            roman = roman.toUpperCase();

            int decimal = 0;
            int prev = 0;
            for (int i = roman.length() - 1; i >= 0; i--) {
                int temp = romanToDecimal.get(roman.charAt(i));
                if (temp < prev) {
                    decimal -= temp;
                } else {
                    decimal += temp;
                }

                prev = temp;
            }

            return decimal;
        }

        return -1.0;
    }
    // ------------------------------------------------------------------------------
}
