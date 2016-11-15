package optionsbuilder;

import static org.junit.Assert.*;

import org.apache.commons.cli.*;
import java.util.AbstractMap;

import org.junit.Before;
import org.junit.Test;

public class UnifiedOptionTest {
	OptionConsumer optionConsumer;
	public static final int MAX_NUM_OF_ARGS = 2;
	
	/*
	 * Combination of optionalArg(true) and numberOfArgs(2) allows between 0 and 2 arguments.
	 */
	@Before
	public void setup() {
		Option option = Option.builder("ethernet")
				.argName("show | setting")
				.numberOfArgs(MAX_NUM_OF_ARGS)
				.optionalArg(true)
				.build();		
		optionConsumer = new OptionConsumer(option);
	}
	
	@Test
	public void showsStatsOptionReturnsStatsOptionValue() throws ParseException {
		String[] input = new String[] {"-ethernet", "show"};
				
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
				
		AbstractMap<String, String[]> arguments = optionConsumer.parse(input);
		
		String[] optionValues = arguments.get("optionValues");
		String[] leftoverArguments = arguments.get("leftoverArguments");		
		assertNull(optionValues);
		assertEquals(0, leftoverArguments.length);
	}
	
	@Test
	public void defaultOptionValueWithArgumentsReturnsSettings() throws ParseException {
		String[] input = new String[] {"-ethernet", "1", "2"};
				
		AbstractMap<String, String[]> arguments = optionConsumer.parse(input);
		
		String[] optionValues = arguments.get("optionValues");
		String[] leftoverArguments = arguments.get("leftoverArguments");		
		assertEquals(2, optionValues.length);
		assertEquals("1", optionValues[0]);
		assertEquals("2", optionValues[1]);
		assertEquals(0, leftoverArguments.length);
	}
	
	@Test
	public void tooManyArgsReturnsLefoverArguments() throws ParseException {
		String[] input = new String[] {"-ethernet", "1", "2", "too many arg"};
		
		AbstractMap<String, String[]> arguments = optionConsumer.parse(input);
		
		String[] optionValues = arguments.get("optionValues");
		String[] leftoverArguments = arguments.get("leftoverArguments");		
		assertEquals(2, optionValues.length);
		assertEquals("1", optionValues[0]);
		assertEquals("2", optionValues[1]);
		assertEquals(1, leftoverArguments.length);
		assertEquals("too many arg", leftoverArguments[0]);
	}
}
