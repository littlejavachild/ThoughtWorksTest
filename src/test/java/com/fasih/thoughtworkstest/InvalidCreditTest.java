package com.fasih.thoughtworkstest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InvalidCreditTest {

    @Test
    public void InvalidCredit() {

        // GetCreditCommand depends on com.fasih.thoughtworkstest.TranslateCommand and
        // com.fasih.thoughtworkstest.AddCreditCommand
        // So we need to simulate them first
        CommandProvider provider = new CommandProvider();
        IntergalacticDictionary dict = provider.getDict();
        dict.addTranslation("glob", 'I'); // simulates com.fasih.thoughtworkstest.TranslateCommand
        dict.addTranslation("prok", 'V'); // simulates com.fasih.thoughtworkstest.TranslateCommand
        dict.addCredits("Silver", 100.0); // simulates com.fasih.thoughtworkstest.AddCreditCommand

        // glob glob glob glob (IIII) is an invalid number
        assertEquals("We should have no idea", "I have no idea what you are talking about",
            provider.getCommandFor("how many Credits is glob glob glob glob Silver ?").execute());
    }
}
