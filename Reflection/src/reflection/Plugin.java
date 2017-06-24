package reflection;

@FunctionalInterface
public interface Plugin<T, R> {

  R execute(T arguments);
}
