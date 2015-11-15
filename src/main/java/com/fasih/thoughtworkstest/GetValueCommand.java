package com.fasih.thoughtworkstest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetValueCommand implements Command<String> {
    private String input = null;
    private IntergalacticDictionary dict = null;
    private final String getValueRegex = "^how much is ((?:\\w+| )+)\\s*\\?$";
    private final Pattern getValuePattern = Pattern.compile(getValueRegex);

    // ------------------------------------------------------------------------------
    public GetValueCommand(final String input, final IntergalacticDictionary dict) {
        this.input = input;
        this.dict = dict;
    }

    // ------------------------------------------------------------------------------
    @Override
    public String execute() {
        Matcher matcher = getValuePattern.matcher(input);
        if (matcher.matches()) {
            String alienNumber = matcher.group(1).replaceAll("\\s$", "");
            if (dict.isValidAlienNumber(alienNumber)) {
                double decimal = dict.toDecimal(alienNumber);
                if (decimal != -1) {
                    return getFormattedOutput(alienNumber, decimal);
                }
            }
        }

        return new InvalidCommand().execute();
    }

    // ------------------------------------------------------------------------------
    private String getFormattedOutput(final String alienNumber, final double decimal) {
        int asInt = (int) decimal;
        boolean isInt = asInt == decimal;
        if (isInt) {
            return alienNumber + " is " + asInt;
        }

        return alienNumber + " is " + decimal;
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
