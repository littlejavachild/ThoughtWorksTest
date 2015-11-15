package com.fasih.thoughtworkstest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AddCreditsTest {

    @Test
    public void addCreditUpdatesIntergalacticDictionary() {

        // com.fasih.thoughtworkstest.AddCreditCommand depends on com.fasih.thoughtworkstest.TranslateCommand
        // So we need to simulate a few com.fasih.thoughtworkstest.TranslateCommand first
        CommandProvider provider = new CommandProvider();
        IntergalacticDictionary dict = provider.getDict();
        dict.addTranslation("glob", 'I'); // simulates com.fasih.thoughtworkstest.TranslateCommand
        dict.addTranslation("prok", 'V'); // simulates com.fasih.thoughtworkstest.TranslateCommand
        provider.getCommandFor("glob glob Gold is 57800 Credits").execute();
        provider.getCommandFor("glob glob Silver is 34 Credits").execute();
        assertTrue("Gold should be in dictionary", dict.getCommodities().contains("Gold"));
        assertTrue("Silver should be in dictionary", dict.getCommodities().contains("Silver"));
    }
}
