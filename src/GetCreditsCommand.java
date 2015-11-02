

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCreditsCommand implements Command<String>{
	private String input;
	private IntergalacticDictionary dict;
	private final String queryRegex = "^how many Credits is ((?:[a-zA-Z]+| )+) ([A-Za-z]+)\\s*\\?$";
	private final Pattern queryPattern = Pattern.compile(queryRegex);
	//------------------------------------------------------------------------------
	public GetCreditsCommand(String input,IntergalacticDictionary dict) {
		this.input = input;
		this.dict = dict;
	}
	//------------------------------------------------------------------------------
	@Override
	public String execute() {
		Matcher matcher = queryPattern.matcher(input);
		if(matcher.matches()){
			String alienNumber = matcher.group(1).replaceAll("\\s$", "");
			String commodity = matcher.group(2);
			double quantityAsDecimal = dict.toDecimal(alienNumber);
			// Invalid alien number?
			// No idea what you are talking about
			if(!dict.isValidAlienNumber(alienNumber)){
				return new InvalidCommand().execute();
			}
			// Unknown commodity?
			// No idea what you are talking about
			if(!dict.getCommodities().contains(commodity)){
				return new InvalidCommand().execute();
			}
			// Alien number wasn't a valid Roman number?
			// No idea what you are talking about
			if(quantityAsDecimal == -1){
				return new InvalidCommand().execute();
			}
			double costOfOne = dict.getCreditsMap().get(commodity);
			if( quantityAsDecimal == 1 ){
				return getFormattedOutput(alienNumber,commodity,costOfOne);
			}else{
				return getFormattedOutput(alienNumber,commodity,costOfOne*quantityAsDecimal);
			}
		}
		return new InvalidCommand().execute();
	}
	//------------------------------------------------------------------------------
	private String getFormattedOutput(String alienNumber, String commodity,
			double cost) {
		int costAsInt = (int) cost;
		boolean isInt = costAsInt == cost;
		if(isInt){
			return alienNumber + " " + commodity + " is " + costAsInt +(cost==1?" Credit":" Credits");
		}
		return alienNumber + " " + commodity + " is " + cost +(cost==1?" Credit":" Credits");
	}
	
	//------------------------------------------------------------------------------
}
