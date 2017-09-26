package ru.stqa.pft.point;

public class Point {
  public static void main (String[] args) {
    double x1=2, y1=4;
    double x2=6, y2=7;
    System.out.println("Расстояние между двумя точками с координатами т.1 ("+x1+","+y1+") и т.2 ("+x2+","+y2+") = "+ distance (x1,y1,x2,y2));
  }
  public static double distance(double x1,double y1, double x2, double y2){
  return Math.sqrt(Math.pow((y2-y1),2.)+Math.pow((x2-x1),2.));
  }
}
