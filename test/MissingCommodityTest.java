import static org.junit.Assert.*;
import org.junit.Test;

public class MissingCommodityTest{

	@Test
	public void missingommodityGeneratesError(){
		// GetCreditCommand depends on TranslateCommand and AddCreditCommand
		// So we need to simulate them first
		CommandProvider provider = new CommandProvider();
		IntergalacticDictionary dict = provider.getDict();
		dict.addTranslation("glob",'I'); // simulates TranslateCommand
		dict.addTranslation("prok",'V'); // simulates TranslateCommand
		dict.addCredits("Silver",100.0); // simulates AddCreditCommand
		// We have no cost for Iron
		// So we have no idea what to taalk about
		assertEquals("We should have no idea",
			         "I have no idea what you are talking about",
			         provider.getCommandFor("how many Credits is glob prok Iron ?").execute());
	}
}