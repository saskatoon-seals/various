package generics;

/*
 * Compiler can't prove that the program is typesafe. Human must do that.
 * A runtime error ClassCastException is at risk.
 *
 * Requires no dependency injection.
 */
public class ArrayStack<T> {
  private T[] elements;
  private int size = 0;

  //Developer must verify that elements are accessed ONLY through methods with matching generic types
  @SuppressWarnings("unchecked")
  public ArrayStack(int size) {
    this((T[]) new Object[size]);
  }

  //Non-type safe constructor
  public ArrayStack(T[] elements) {
    this.elements = elements;
  }

  //type of input must match the elements' type - compiler takes care of that!
  public void push(T element) {
    elements[size++] = element;
  }

  //Type mismatch: cannot convert from Object to T
//  public void brokenPush(Object element) {
//    elements[size++] = element;
//  }

  //type of return value must match the elements' type - compiler takes care of that
  public T pop() {
    T element = elements[--size];

    elements[size] = null;

    return element;
  }
}
