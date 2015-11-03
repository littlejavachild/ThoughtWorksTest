import static org.junit.Assert.*;
import org.junit.Test;

public class InvalidTranslateTest{

	@Test
	public void translateUpdatesIntergalacticDictionary(){
		CommandProvider provider = new CommandProvider();
		Command translateCommand = provider.getCommandFor("glob is III");
		IntergalacticDictionary dict = ((TranslateCommand)translateCommand).getDict();
		translateCommand.execute();
		assertTrue("glob was not expected in dictionary",!dict.getTranslatedAlienWords().contains("glob"));
	}
}