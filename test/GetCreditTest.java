import static org.junit.Assert.*;
import org.junit.Test;

public class GetCreditTest{

	@Test
	public void getCredit(){
		// GetCreditCommand depends on TranslateCommand and AddCreditCommand
		// So we need to simulate them first
		CommandProvider provider = new CommandProvider();
		IntergalacticDictionary dict = provider.getDict();
		dict.addTranslation("glob",'I'); // simulates TranslateCommand
		dict.addTranslation("prok",'V'); // simulates TranslateCommand
		dict.addCredits("Silver",100.0); // simulates AddCreditCommand
		// 1 unit of Silver is 100
		// glob prok (4) units of Silver should be 400
		assertEquals("Silver should be 400 Credits",
			         "glob prok Silver is 400 Credits",
			         provider.getCommandFor("how many Credits is glob prok Silver ?").execute());
	}
}