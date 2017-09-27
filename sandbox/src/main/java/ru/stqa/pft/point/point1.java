package ru.stqa.pft.point;

public class point1 {
  public double x;
  public double y;

  public point1 (double x, double y) {
    this.x=x;
    this.y=y;
  }
  public double distance(point1 xy2){
    return Math.sqrt(Math.pow((xy2.y-this.y),2.)+Math.pow((xy2.x-this.x),2.));
  }

}
