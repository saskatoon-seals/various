package core;

public class AbstractCommand implements CommandHandler {
  
  public AbstractCommand(String commandName, AbstractCommandHandlerRegister commandHandlerRegister) {
    commandHandlerRegister.add(commandName, this);
  }

  @Override
  public String execute(String... input) {
    return null;
  }
}
