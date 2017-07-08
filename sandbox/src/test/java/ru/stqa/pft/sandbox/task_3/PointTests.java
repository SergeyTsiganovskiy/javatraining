package ru.stqa.pft.sandbox.task_3;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.task_2.Point;

public class PointTests {

  @Test
  public void testDistance_1(){
    Point p1 = new Point(3,3);
    Point p2 = new Point(1,1);

    Assert.assertEquals(p1.distance(p2),Math.sqrt(Math.pow(2,2) + Math.pow(2,2)));
  }

  @Test
  public void testDistance_2(){
    Point p1 = new Point(3,3);
    Point p2 = new Point(1,1);

    Assert.assertEquals(p1.distance(p2),p2.distance(p1));
  }

  @Test
  public void testDistance_3(){
    Point p1 = new Point(3,3);
    Point p2 = new Point(3,3);

    Assert.assertEquals(p1.distance(p2), 0.0);

  }

}
