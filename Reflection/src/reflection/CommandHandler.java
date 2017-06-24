package reflection;

public abstract class CommandHandler implements Plugin<String[], String> {

  public CommandHandler(PluginRegister pluginRegister) {
    pluginRegister.registerPlugin(this);
  }
  
  @Override
  public abstract String execute(String... arguments);
}
