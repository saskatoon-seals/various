import java.util.stream.*;
import java.security.InvalidParameterException;
import java.util.HashMap;

public class Message extends HashMap<ParameterName, Object>{
		
	private static final long serialVersionUID = 1L;

	public static Message init() {
		return new Message();
	}
	
	@Override
	public Message put(ParameterName key, Object value) {
		super.put(key, value);
		return this;
	}
	
	@Override
	public String toString() {
		return this.entrySet()
			       .stream()
				   .map(entry -> String.format("%s = %s", entry.getKey(), entry.getValue().toString()))
				   .collect(Collectors.joining("\n"));
	}
	
	//This method isn't needed!
	public void validateNumOfParams(int numOfParam) {
		if (this.size() != numOfParam) {
			throw new InvalidParameterException(
					String.format("Wrong number of parameters. Expected: %d, Actual: %d", 
							      numOfParam, this.size()));
		}
	}		
}
