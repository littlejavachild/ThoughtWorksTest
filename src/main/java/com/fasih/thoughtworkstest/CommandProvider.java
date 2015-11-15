package com.fasih.thoughtworkstest;

import java.util.regex.Pattern;

public class CommandProvider {
    private final IntergalacticDictionary dict = new IntergalacticDictionary();
    private final LruCache<String, String> cache = new LruCache<String, String>(200);

    // ------------------------------------------------------------------------------
    public Command getCommandFor(final String str) {

        // Look in the cache first
        String entry = null;
        if (entry != null) {
            if (entry.equals(TranslateCommand.class.getName())) {
                return new TranslateCommand(str, dict);
            }

            if (entry.equals(GetValueCommand.class.getName())) {
                return new GetValueCommand(str, dict);
            }

            if (entry.equals(GetCreditsCommand.class.getName())) {
                return new GetCreditsCommand(str, dict);
            }

            if (entry.equals(AddCreditCommand.class.getName())) {
                return new AddCreditCommand(str, dict);
            }
        } else {
            if (isTranslate(str)) {
                cache.put(str, TranslateCommand.class.getName());
                return new TranslateCommand(str, dict);
            }

            if (isGetValue(str)) {
                cache.put(str, GetValueCommand.class.getName());
                return new GetValueCommand(str, dict);
            }

            if (isGetCredits(str)) {
                cache.put(str, GetCreditsCommand.class.getName());
                return new GetCreditsCommand(str, dict);
            }

            if (isAddCredits(str)) {
                cache.put(str, AddCreditCommand.class.getName());
                return new AddCreditCommand(str, dict);
            }
        }

        return new InvalidCommand();
    }

    // ------------------------------------------------------------------------------
    private boolean isTranslate(String str) {
        str = str.trim();

        String[] tokens = str.split("\\s");

        // there should only be 3 tokens
        if (tokens.length > 3) {
            return false;
        }

        // first token should be a word
        if (!Pattern.matches("\\w+", tokens[0])) {
            return false;
        }

        // middle token should be "is"
        if (!tokens[1].equals("is")) {
            return false;
        }

        // last token should be a roman number of size 1
        if (!"IVXLCDM".contains(tokens[2]) && tokens[2].length() == 1) {
            return false;
        }

        return true;
    }

    // ------------------------------------------------------------------------------
    private boolean isGetCredits(String str) {
        str = str.trim();

        String[] tokens = str.split("\\s");
        if (tokens[0].equals("how") && tokens[1].equals("many") && tokens[2].equals("Credits") && tokens[3].equals("is")
                && dict.getCommodities().contains(tokens[tokens.length - 2])
                && areAlienWords(4, tokens.length - 3, tokens) && tokens[tokens.length - 1].equals("?")) {
            return true;
        }

        return false;
    }

    // ------------------------------------------------------------------------------
    private boolean isGetValue(String str) {
        str = str.trim();

        String[] tokens = str.split("\\s");
        if (tokens[0].equals("how") && tokens[1].equals("much") && tokens[2].equals("is")
                && areAlienWords(3, tokens.length - 2, tokens) && tokens[tokens.length - 1].equals("?")) {
            return true;
        }

        return false;
    }

    // ------------------------------------------------------------------------------
    private boolean isAddCredits(String str) {
        str = str.trim();

        String[] tokens = str.split("\\s");
        if (tokens[tokens.length - 1].equals("Credits") && Pattern.matches("\\d+", tokens[tokens.length - 2])
                && tokens[tokens.length - 3].equals("is")
                && !dict.getTranslatedAlienWords().contains(tokens[tokens.length - 4])
                && areAlienWords(0, tokens.length - 5, tokens)) {
            return true;
        }

        return false;
    }

    // ------------------------------------------------------------------------------
    private boolean areAlienWords(final int start, final int end, final String[] words) {
        for (int i = start; i <= end; i++) {
            if (!dict.getTranslatedAlienWords().contains(words[i])) {
                return false;
            }
        }

        return true;
    }

    // ------------------------------------------------------------------------------
    public IntergalacticDictionary getDict() {
        return dict;
    }
    // ------------------------------------------------------------------------------
}
