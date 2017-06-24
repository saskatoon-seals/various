package reflection;

import java.util.LinkedList;
import java.util.List;

public class ControlApp implements PluginRegister, Runnable {
  
  List<CommandHandler> plugins = new LinkedList<>();

  @Override
  public <T, R> PluginRegister registerPlugin(Plugin<T, R> plugin) {
    plugins.add((CommandHandler) plugin);
    
    return null;
  }

  @Override
  public void run() {
    plugins.forEach(CommandHandler::execute);
  }
}
