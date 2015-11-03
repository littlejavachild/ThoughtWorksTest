import static org.junit.Assert.*;
import org.junit.Test;

public class TranslateTest{

	@Test
	public void translateUpdatesIntergalacticDictionary(){
		CommandProvider provider = new CommandProvider();
		Command translateCommand = provider.getCommandFor("glob is I");
		IntergalacticDictionary dict = ((TranslateCommand)translateCommand).getDict();
		translateCommand.execute();
		assertTrue("glob was expected in dictionary",dict.getTranslatedAlienWords().contains("glob"));
	}
}

// javac -cp .:../lib/junit-4.12.jar:../bin/ *.java
// java -cp .:../bin/:../lib/junit-4.12.jar:../lib/hamcrest-core-1.3.jar TestRunner