package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

  @Test
  public void testArea1() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }

  @Test
  public void testArea2() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }

  @Test
  public void testArea3() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }

  @Test
  public void testArea4() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }
}
