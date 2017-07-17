package generics;

import java.util.LinkedList;
import java.util.List;

//Compiler proves the class is typesafe
public class ListStack<T> {
  private List<T> elements;

  public ListStack() {
    this(new LinkedList<>());
  }

  public ListStack(List<T> elements) {
    this.elements = elements;
  }

  public void push(T element) {
    elements.add(element);
  }

  //The method add(T) in the type List<T> is not applicable for the arguments (Object)
//  public void brokenPush(Object element) {
//    elements.add(element);
//  }

  public T pop() {
    return elements.remove(elements.size() - 1);
  }
}
