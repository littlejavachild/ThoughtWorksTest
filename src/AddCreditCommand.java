import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCreditCommand implements Command<Void> {
	private String input = null;
	private IntergalacticDictionary dict = null;
	private final String commodityCreditRegex = "^((?:[a-zA-Z]+| )+) ([a-zA-Z]+) is (\\d+) Credits\\s*$";
	private final Pattern commodityCreditPattern = Pattern.compile(commodityCreditRegex);
	//------------------------------------------------------------------------------
	public AddCreditCommand(String input, IntergalacticDictionary dict) {
		this.input = input;
		this.dict = dict;
	}
	//------------------------------------------------------------------------------
	@Override
	public Void execute() {
		Matcher matcher = commodityCreditPattern.matcher(input);
		if(matcher.matches()){
			String quantity = matcher.group(1);
			String commodity = matcher.group(2);
			double credits = Double.valueOf( matcher.group(3) );
			// get a decimal representation of alien number
			double quantityAsDecimal = dict.toDecimal(quantity);
			if(quantityAsDecimal != -1.0){
				// if quantity is equal to 1
				if(quantityAsDecimal == 1){
					// we add the credits for the credits directly
					dict.addCredits(commodity, credits);
				}else if(quantityAsDecimal >= 1){
					// if not, we convert it to credits for 1
					double unitCredits = credits / quantityAsDecimal;
					dict.addCredits(commodity, unitCredits);
				}
			}
		}
		return null;
	}
	//------------------------------------------------------------------------------
	public String getInput() {
		return input;
	}
	//------------------------------------------------------------------------------
	public IntergalacticDictionary getDict() {
		return dict;
	}
	//------------------------------------------------------------------------------
}
