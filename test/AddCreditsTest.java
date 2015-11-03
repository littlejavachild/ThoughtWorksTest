import static org.junit.Assert.*;
import org.junit.Test;

public class AddCreditsTest{

	@Test
	public void addCreditUpdatesIntergalacticDictionary(){
		// AddCreditCommand depends on TranslateCommand
		// So we need to simulate a few TranslateCommand first
		CommandProvider provider = new CommandProvider();
		IntergalacticDictionary dict = provider.getDict();
		dict.addTranslation("glob",'I'); // simulates TranslateCommand
		dict.addTranslation("prok",'V'); // simulates TranslateCommand
		provider.getCommandFor("glob glob Gold is 57800 Credits").execute();
		provider.getCommandFor("glob glob Silver is 34 Credits").execute();
		assertTrue("Gold should be in dictionary",dict.getCommodities().contains("Gold"));
		assertTrue("Silver should be in dictionary",dict.getCommodities().contains("Silver"));
	}
}