package com.fasih.thoughtworkstest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WoodchuckTest {

    @Test
    public void howMuchWoodCanAWoodchuckChuck() {
        CommandProvider provider = new CommandProvider();
        provider.getCommandFor("glob is I").execute();
        provider.getCommandFor("prok is V").execute();
        provider.getCommandFor("glob glob Silver is 34 Credits").execute();
        assertEquals("We should have no idea", "I have no idea what you are talking about",
            provider.getCommandFor("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?")
                    .execute());
    }
}
