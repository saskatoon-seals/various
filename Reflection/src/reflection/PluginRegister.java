package reflection;

@FunctionalInterface
public interface PluginRegister {

  <T, R> PluginRegister registerPlugin(Plugin<T, R> plugin);
}
