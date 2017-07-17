package generics;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class InheritanceTest {
  private static final int SIZE = 2;
  private static final String ALES = new String("Ales");
  private static final Integer ONE = new Integer(1);

  private static final int ALES_INDEX = 0;
  private static final int ONE_INDEX = 1;

  @Test
  public void arraysCanStoreObjectsOfAnyType() {
    Object[] objects = new Object[SIZE];
    objects[0] = ALES;
    objects[1] = ONE;

    Assert.assertEquals(ALES, objects[ALES_INDEX]);
    Assert.assertEquals(ONE, objects[ONE_INDEX]);
  }

  @Test
  public void listsCanStoreObjectsOfAnyType() {
    List<Object> objects = new ArrayList<>();

    objects.add(ALES);
    objects.add(ONE);

    Assert.assertEquals(ALES, objects.get(ALES_INDEX));
    Assert.assertEquals(ONE, objects.get(ONE_INDEX));
  }

  @Test
  public void testAdd1() {
    Assert.assertEquals(
        ALES,
        Inheritance.add(new ArrayList<Object>(), ALES)
                   .get(ALES_INDEX)
    );
  }

  /*
   * Compiler error:
   *
   * List<String> is not a subtype of List<Object>
   *
   * Type invariance
   */
//  @Test
//  public void testAdd2() {
//    Assert.assertEquals(
//        ALES,
//        Inheritance.add(new ArrayList<String>(), ALES)
//                   .get(ALES_INDEX)
//    );
//  }

  /*
   * Covariance of arrays:
   *
   * String[SIZE] is a subtype of Object[SIZE]
   */
  @Test
  public void testAdd3() {
    Assert.assertEquals(
        ALES,
        Inheritance.add(new String[SIZE], ALES, ALES_INDEX)
                   [ALES_INDEX]
    );
  }

  @Test
  public void canPutOnlyNullIntoUnboundedWildcardType() {
    List<?> elements = new ArrayList<>();

    //compiler error:
//    elements.add(ALES);

    elements.add(null);
  }

  @Test
  public void wildcardTest2() {
    List<Object> elements = new ArrayList<>();
    elements.add(ALES);
    elements.add(ONE);

    List<?> objects = elements;

    String x1 = (String) objects.get(ALES_INDEX); //casting required
    Object x2 = objects.get(ALES_INDEX); //casting not required

    Assert.assertEquals(ALES, x1);
    Assert.assertEquals(ALES, x2);
  }

  //List<Integer> is a "subtype" of List<?>
  @Test
  public void wildCardNoop() {
    Inheritance.wildcardNoop(new ArrayList<Integer>());
  }

  //List<?> does not extend List<Object>
//  @Test
//  public void noop() {
//    List<?> elements = new ArrayList<>();
//
//    Inheritance.noop(elements);
//  }
}
