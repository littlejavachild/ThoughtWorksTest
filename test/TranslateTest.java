import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TranslateTest{
	@Test
	public void translateShouldUpdateIntergalacticDictionary(){
		CommandProvider provider = new CommandProvider();
		Command translateCommand = provider.getCommandFor("glob is I");
		IntergalacticDictionary dict = ((TranslateCommand)translateCommand).getDict();
		translateCommand.execute();
		assertTrue("glob was expected in dictionary",true);
	}
}

// javac -cp ../lib/junit-4.12.jar:../src/ *.java
// java -cp .:../lib/junit-4.12.jar:../lib/hamcrest-core-1.3.jar TestRunner