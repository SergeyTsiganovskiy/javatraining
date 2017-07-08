package ru.stqa.pft.sandbox.task_2;

public class App {
  public static void main(String[] args) {
    Point p1 = new Point(3,3);
    Point p2 = new Point(1,1);

    System.out.println(p1.distance(p2));
    System.out.println(distance(p1,p2));
  }

  public static double distance(Point p1 , Point p2){
    return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y,2));
  }
}
