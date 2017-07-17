package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Arrays are covariant (Long[] extends Object[])
 *
 * Generics are invariant (List<Long> doesn't extend List<Object>)
 */
public class Inheritance {

  public static List<Object> add(List<Object> list, Object element) {
    list.add(element);

    return list;
  }

  public static Object[] add(Object[] array, Object element, int index) {
    array[index] = element;

    return array;
  }

  public static List<?> wildcardNoop(List<?> list) {
    return list;
  }

  public static void noop(List<Object> list) { }

  /*
   * Safety of a cast can't be checked at runtime - ClassCastException is at risk because
   * down-casting involves a type check.
   */
  private static <T> void reduce(List<T> elements) {
    @SuppressWarnings("unchecked")
    T[] tempElements = (T[]) elements.toArray(); //toArray() returns Object[]
    //That means Object[] is down-casted to e.g. into String[] at runtime

    //solution:
    List<T> tempElements2 = new ArrayList<>(elements);
  }
}
