package generics;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Arrays enforce types at runtime (reified type)
 *
 * Generics enforce type at compile type and loose type at runtime (erasure)
 *
 */
public class ListsVsArraysTest {
  private static final int SIZE = 1;

  @Test
  public <T> void test1() {
    //Cannot create a generic array of T - to avoid ClassCastException at runtime (covariance)
//    T[] elements = new T[SIZE];
    List<T> elements = new ArrayList<>(); //Allowed
  }

  //Arrays are covariant and reified - PROBLEM!
  @Test(expected=ArrayStoreException.class)
  public void arraysEnforceTypeInRuntime() {
    Object[] elements = new Integer[SIZE];

    elements[0] = "Ales"; //Compiler does not detect trying to put string into an integer
  }


  @Test
  public void genericsEnforceTypeAtCompileTime() {
    List<Integer> elements = new ArrayList<>();

//    elements.add("Ales"); - type is enforced; compiler error
  }

  @Test
  public void collectionOfAnyType() {
//    List<Object> elements = new ArrayList<Integer>(); //compiler error
    List<Object> elements = new ArrayList<>();

    elements.add("Ales");

    Assert.assertEquals("Ales", elements.get(0));
  }

  @Test
  public void listStackCannotFoolTypeSystem() {
//    ListStack<Object> stack = new ListStack<>(new LinkedList<Integer>()); - compiler error

//    stack.push("Ales"); - compiler error (2nd thing)
  }

  @Test(expected=ArrayStoreException.class)
  public void arrayStackCanFullTypeSystem() {
    ArrayStack<Object> stack = new ArrayStack<>(new Integer[1]);

    stack.push("Ales");
  }
}
