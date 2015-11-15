package com.fasih.thoughtworkstest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MissingTranslationTest {

    @Test
    public void missingTranslationGeneratesError() {

        // com.fasih.thoughtworkstest.AddCreditCommand depends on com.fasih.thoughtworkstest.TranslateCommand
        // If we dont have translation for an alien word, say "pish",
        // then we should get an com.fasih.thoughtworkstest.InvalidCommand in return
        CommandProvider provider = new CommandProvider();
        IntergalacticDictionary dict = provider.getDict();
        dict.addTranslation("glob", 'I'); // simulates com.fasih.thoughtworkstest.TranslateCommand
        dict.addTranslation("prok", 'V'); // simulates com.fasih.thoughtworkstest.TranslateCommand

        // simulates not having "pish" in dictionary
        // dict.addTranslation("pish",'X');
        provider.getCommandFor("glob glob Gold is 57800 Credits").execute();
        provider.getCommandFor("glob glob Silver is 34 Credits").execute();
        assertTrue("Gold should be in dictionary", dict.getCommodities().contains("Gold"));
        assertTrue("Silver should be in dictionary", dict.getCommodities().contains("Silver"));
        assertEquals("com.fasih.thoughtworkstest.InvalidCommand expected", "com.fasih.thoughtworkstest.InvalidCommand",
            provider.getCommandFor("pish pish Apple is 200 Credits").getClass().getName());
    }
}
