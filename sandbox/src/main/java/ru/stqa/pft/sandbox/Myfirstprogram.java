package ru.stqa.pft.sandbox;

public class Myfirstprogram {

  public static void main(String[] args) {
    double l = 5;
    hello("world");
    hello("user");
    hello("Lena");
    System.out.println("Площадь квадрата со строной " + l + " =" + area(l));
    double a=5, b=6;
    System.out.println("Площадь прямоугольника со сторонами "+ a+" и "+b+ " = "+area (a,b));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double len) {
    return len * len;
  }

  public static double area (double a, double b) {
    return a * b;
  }
}

