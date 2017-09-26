package ru.stqa.pft.point;

public class Point {
  public static void main (String[] args) {
    point1 xy1 = new point1 (2,4);
    point1 xy2 = new point1 (6,7);
    System.out.println("Расстояние между двумя точками с координатами т.1 ("+xy1.x+","+xy1.y+") и т.2 ("+xy2.x+","+xy2.y+") = "+ distance (xy1,xy2));
  }
  public static double distance(point1 xy1, point1 xy2){
  return Math.sqrt(Math.pow((xy2.y-xy1.y),2.)+Math.pow((xy2.x-xy1.x),2.));
  }
}
