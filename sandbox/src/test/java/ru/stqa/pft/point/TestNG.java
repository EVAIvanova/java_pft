package ru.stqa.pft.point;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG {
  @Test
  public void testPoint (){
    point1 P1 = new point1 (2,4);
    point1 P2 = new point1 (6,7);
    Assert.assertEquals(P1.distance(P2),5.);
  }
}
