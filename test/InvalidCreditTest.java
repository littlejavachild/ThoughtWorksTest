import static org.junit.Assert.*;
import org.junit.Test;

public class InvalidCreditTest{

	@Test
	public void InvalidCredit(){
		// GetCreditCommand depends on TranslateCommand and AddCreditCommand
		// So we need to simulate them first
		CommandProvider provider = new CommandProvider();
		IntergalacticDictionary dict = provider.getDict();
		dict.addTranslation("glob",'I'); // simulates TranslateCommand
		dict.addTranslation("prok",'V'); // simulates TranslateCommand
		dict.addCredits("Silver",100.0); // simulates AddCreditCommand
		// glob glob glob glob (IIII) is an invalid number
		assertEquals("We should have no idea",
			         "I have no idea what you are talking about",
			         provider.getCommandFor("how many Credits is glob glob glob glob Silver ?").execute());
	}
}