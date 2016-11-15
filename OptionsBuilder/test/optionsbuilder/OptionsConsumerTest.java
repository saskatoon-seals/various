package optionsbuilder;
import static org.junit.Assert.*;

import org.apache.commons.cli.*;

import java.util.AbstractMap;

import org.junit.Test;

public class OptionsConsumerTest {
	
	OptionConsumer optionConsumer;
	
	@Test
	public void showsStatsOptionReturnsStatsOptionValue() throws ParseException {
		String[] input = new String[] {"-ethernet", "show"};
		Option option = Option.builder("ethernet")
				.argName("show")
				.hasArg()
				.build();		
		optionConsumer = new OptionConsumer(option);
		
		AbstractMap<String, String[]> arguments = optionConsumer.parse(input);
		
		String[] optionValues = arguments.get("optionValues");
		String[] leftoverArguments = arguments.get("leftoverArguments");		
		assertEquals(1, optionValues.length);
		assertEquals("show", optionValues[0]);
		assertEquals(0, leftoverArguments.length);
	}
	
	@Test
	public void emptyOptionalArgumentReturnsNull() throws ParseException {
		String[] input = new String[] {"-ethernet"};
		Option option = Option.builder("ethernet")
				.argName("show")
				.hasArg()
				.optionalArg(true)
				.build();		
		optionConsumer = new OptionConsumer(option);
		
		AbstractMap<String, String[]> arguments = optionConsumer.parse(input);
		
		String[] optionValues = arguments.get("optionValues");
		String[] leftoverArguments = arguments.get("leftoverArguments");		
		assertNull(optionValues);
		assertEquals(0, leftoverArguments.length);
	}
	
	/*
	 * This is for variable number of arguments. Find a way to restrict number of arguments to 3.
	 * Minimum number of arguments should be 2.
	 */
	@Test
	public void defaultOptionValueWithArgumentsReturnsSettings() throws ParseException {
		String[] input = new String[] {"-ethernet", "1", "2"};
		Option option = Option.builder("ethernet")
				.argName("setting")
				.hasArgs()
				.build();		
		optionConsumer = new OptionConsumer(option);
		
		AbstractMap<String, String[]> arguments = optionConsumer.parse(input);
		
		String[] optionValues = arguments.get("optionValues");
		String[] leftoverArguments = arguments.get("leftoverArguments");		
		assertEquals(2, optionValues.length);
		assertEquals("1", optionValues[0]);
		assertEquals("2", optionValues[1]);
		assertEquals(0, leftoverArguments.length);
	}
}
