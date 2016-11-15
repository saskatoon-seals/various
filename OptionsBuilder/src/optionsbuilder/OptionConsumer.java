package optionsbuilder;

import java.util.AbstractMap;
import java.util.HashMap;

import org.apache.commons.cli.*;

public class OptionConsumer {
	Option option;
	Options options = new Options();
	CommandLineParser parser = new DefaultParser();
	
	public OptionConsumer(Option option) {
		this.option = option;
		options.addOption(option);
	}

	public AbstractMap<String, String[]> parse(String... args) throws ParseException {
		CommandLine line = parser.parse(options, args);		
		
		AbstractMap<String, String[]> arguments = new HashMap<>();
		
		arguments.put("optionValues", line.getOptionValues(option.getOpt()));
		arguments.put("leftoverArguments", line.getArgs());
		
		return arguments;
	}
}
