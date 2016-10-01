/*
 * Conclusion: don't use primitive data types for messages, also use enums in order to agree on
 * data contract. Enums also do validation, and they constrain the pool of possible param names. 
 */
public class Server {
	
	private boolean validate;
	
	public static final int NUM_OF_PARAMS = 4;
	
	public Server(boolean validate) {
		this.validate = validate;
	}

	public Message receive(Message message) {		
		/*
		 * Validation of only number of parameters is redundant.
		 * If there are parameters missing, unpacking the params will return null values.
		 * 
		 * In some cases this will throw a NullPointerException (e.g. x.concat(x),..), but always??
		 */
		if (validate) {
			message.validateNumOfParams(NUM_OF_PARAMS);
		}
		
		/*
		 * Unpack parameters from the message.
		 * With that you re-gain the parameters' type info needed for processing.
		 * 
		 * The same happens with normal function calls or constructors, only without casting.
		 */
		Integer x1 = (Integer) message.get(ParameterName.JOE);
		String x2 = (String) message.get(ParameterName.SHANNON);
		Double x3 = (Double) message.get(ParameterName.KYLE);
		Boolean x4 = (Boolean) message.get(ParameterName.ALES);
		
		return process(x1, x2, x3, x4);
	}
	
	/*
	 * If parameters are objects or boxed datatypes, then processing them will always throw a 
	 * NullPointerException.
	 */
	private Message process(Integer x1, String x2, Double x3, Boolean x4) {
		//If any of these is null, NullPointerException will be thrown.
		x1++;
		x2 = x2.concat(x2);
		x3 *= 2;
		x4 = !x4;
		
		return Message.init()
				  .put(ParameterName.JOE, x1)
				  .put(ParameterName.SHANNON, x2)
				  .put(ParameterName.KYLE, x3)
				  .put(ParameterName.ALES, x4);
	}
}
