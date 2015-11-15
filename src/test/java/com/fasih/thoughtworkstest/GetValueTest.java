package com.fasih.thoughtworkstest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GetValueTest {

    @Test
    public void getValue() {

        // GetValue depends on com.fasih.thoughtworkstest.TranslateCommand
        // So we need to simulate a few com.fasih.thoughtworkstest.TranslateCommand first
        CommandProvider provider = new CommandProvider();
        IntergalacticDictionary dict = provider.getDict();
        dict.addTranslation("glob", 'I'); // simulates com.fasih.thoughtworkstest.TranslateCommand
        dict.addTranslation("prok", 'V'); // simulates com.fasih.thoughtworkstest.TranslateCommand
        assertEquals("1 expected", "glob is 1", provider.getCommandFor("how much is glob ?").execute());
        assertEquals("2 expected", "glob glob is 2", provider.getCommandFor("how much is glob glob ?").execute());
        assertEquals("3 expected", "glob glob glob is 3",
            provider.getCommandFor("how much is glob glob glob ?").execute());
        assertEquals("4 expected", "glob prok is 4", provider.getCommandFor("how much is glob prok ?").execute());
        assertEquals("5 expected", "prok is 5", provider.getCommandFor("how much is prok ?").execute());
    }
}
