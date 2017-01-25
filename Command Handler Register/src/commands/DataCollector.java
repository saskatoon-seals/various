package commands;

import java.util.function.Supplier;

import core.AbstractCommand;
import core.AbstractCommandHandlerRegister;

public class DataCollector extends AbstractCommand implements Supplier<Boolean> {

  public DataCollector(AbstractCommandHandlerRegister commandHandlerRegister) {
    super(DataCollector.class.getSimpleName(), commandHandlerRegister);
  }

  @Override
  public Boolean get() {
    return null;
  }
}
