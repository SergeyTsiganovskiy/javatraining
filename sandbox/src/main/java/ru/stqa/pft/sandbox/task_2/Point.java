package ru.stqa.pft.sandbox.task_2;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point anotherPoint){
    return Math.sqrt(Math.pow(anotherPoint.x - this.x,2) + Math.pow(anotherPoint.y - this.y,2));
  }
}

