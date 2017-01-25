package debugdump;

import java.util.function.Supplier;
import java.util.LinkedList;
import java.util.List;

import core.AbstractCommandHandlerRegister;
import core.CommandHandler;

public class DebugDumpManager extends AbstractCommandHandlerRegister {
  private List<Supplier<Boolean>> tasks = new LinkedList<>();

  @SuppressWarnings("unchecked")
  @Override
  public void add(String commandName, CommandHandler commandHandler) {
    tasks.add((Supplier<Boolean>) commandHandler);
  }
}
