package com.fasih.thoughtworkstest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslateCommand implements Command<Void> {
    private String input = null;
    private final String translationRegex = "^([a-zA-Z]+) is ([IVXLCDM]){1}\\s?$";
    private final Pattern translationPattern = Pattern.compile(translationRegex);
    private IntergalacticDictionary dict = null;

    // ------------------------------------------------------------------------------
    public TranslateCommand(final String input, final IntergalacticDictionary dict) {
        this.input = input;
        this.dict = dict;
    }

    // ------------------------------------------------------------------------------
    @Override
    public Void execute() {
        Matcher matcher = translationPattern.matcher(input);
        if (matcher.matches()) {
            String alienWord = matcher.group(1);
            Character romanNumber = (char) matcher.group(2).charAt(0);
            dict.addTranslation(alienWord, romanNumber);
        }

        return null;
    }

    // ------------------------------------------------------------------------------
    public String getInput() {
        return input;
    }

    // ------------------------------------------------------------------------------
    public IntergalacticDictionary getDict() {
        return dict;
    }
    // ------------------------------------------------------------------------------
}
