package core;

public abstract class AbstractCommandHandlerRegister {

  public abstract void add(String commandName, CommandHandler commandHandler);
  
  public void initializeCommandHandlers(AbstractCommandHandlerRegister commandHandlerRegister) {
    //@TODO: use reflection to get all the CommandHandlers and call initializeCommandHandler for each of them
  }
  
  private void initializeCommandHandler(CommandHandler commandHandler) {
    //@TODO: get constructor and call it (use this)
  }
}
