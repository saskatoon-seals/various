package core;
@FunctionalInterface
public interface CommandHandler {

  String execute(String... input);
}
