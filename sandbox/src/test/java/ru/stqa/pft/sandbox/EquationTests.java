package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

  @Test
  public void test0 () {
    Eqaution e = new Eqaution (1,1,1);
    Assert.assertEquals(e.rootNumber(),0);
  }

  @Test
  public void test1 () {
    Eqaution e = new Eqaution (1,2,1);
    Assert.assertEquals(e.rootNumber(),1);
  }

  @Test
  public void test2 () {
    Eqaution e = new Eqaution (1,5,6);
    Assert.assertEquals(e.rootNumber(),2);
  }

  @Test
  public void testLinear () {
    Eqaution e = new Eqaution (0,1,1);
    Assert.assertEquals(e.rootNumber(),1);
  }

  @Test
  public void testConstant () {
    Eqaution e = new Eqaution (0,0,1);
    Assert.assertEquals(e.rootNumber(),0);
  }

  @Test
  public void testZero () {
    Eqaution e = new Eqaution (0,0,0);
    Assert.assertEquals(e.rootNumber(),-1);
  }
}
