

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Invoker {
	private List<Command> history = new ArrayList<Command>();
	private List<Object> results = new ArrayList<Object>();
	//------------------------------------------------------------------------------
	public void addCommand(Command command){
		history.add(command);
	}
	//------------------------------------------------------------------------------
	public void invoke(Command command){
		history.add(command);
		results.add( command.execute() );
	}
	//------------------------------------------------------------------------------
	public List<Object> getResults(){
		return Collections.unmodifiableList(results);
	}
	//------------------------------------------------------------------------------
	public List<Command> getHistory(){
		return history;
	}
	//------------------------------------------------------------------------------
}
