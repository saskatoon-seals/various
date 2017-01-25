package controlapp;
import java.util.AbstractMap;
import java.util.HashMap;

import core.AbstractCommandHandlerRegister;
import core.CommandHandler;

public class ControlApp extends AbstractCommandHandlerRegister {

  private AbstractMap<String, CommandHandler> commands = new HashMap<>();

  @Override
  public void add(String commandName, CommandHandler commandHandler) {
    commands.put(commandName, commandHandler);
  }
}
