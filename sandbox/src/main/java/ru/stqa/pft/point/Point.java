package ru.stqa.pft.point;

public class Point {
  public static void main (String[] args) {
    point1 xy1 = new point1 (2,4);
    point1 xy2 = new point1 (6,7);
    System.out.println("Расстояние между двумя точками с координатами т.1 ("+xy1.x+","+xy1.y+") и т.2 ("+xy2.x+","+xy2.y+") = "+ xy1.distance (xy2));
  }

}
