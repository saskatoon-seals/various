package commands;
import core.AbstractCommand;
import core.AbstractCommandHandlerRegister;

public class FpgaRegisterRead extends AbstractCommand {

  public FpgaRegisterRead(AbstractCommandHandlerRegister commandHandlerRegister) {
    super(FpgaRegisterRead.class.getSimpleName(), commandHandlerRegister);
  }
}
