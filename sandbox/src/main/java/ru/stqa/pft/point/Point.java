package ru.stqa.pft.point;

public class Point {
  public static void main (String[] args) {
    point1 P1 = new point1 (2,4);
    point1 P2 = new point1 (6,7);
    System.out.println("Расстояние между двумя точками с координатами т.1 ("+P1.x+","+P1.y+") и т.2 ("+P2.x+","+P2.y+") = "+ P1.distance (P2));
  }

}
