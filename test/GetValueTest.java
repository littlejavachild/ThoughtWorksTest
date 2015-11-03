import static org.junit.Assert.*;
import org.junit.Test;

public class GetValueTest{

	@Test
	public void getValue(){
		// GetValue depends on TranslateCommand
		// So we need to simulate a few TranslateCommand first
		CommandProvider provider = new CommandProvider();
		IntergalacticDictionary dict = provider.getDict();
		dict.addTranslation("glob",'I'); // simulates TranslateCommand
		dict.addTranslation("prok",'V'); // simulates TranslateCommand
		assertEquals("1 expected",
					 "glob is 1",
					 provider.getCommandFor("how much is glob ?").execute());
		assertEquals("2 expected",
					 "glob glob is 2",
					 provider.getCommandFor("how much is glob glob ?").execute());
		assertEquals("3 expected",
					 "glob glob glob is 3",
					 provider.getCommandFor("how much is glob glob glob ?").execute());
		assertEquals("4 expected",
					 "glob prok is 4",
					 provider.getCommandFor("how much is glob prok ?").execute());
		assertEquals("5 expected",
					 "prok is 5",
					 provider.getCommandFor("how much is prok ?").execute());
	}
}