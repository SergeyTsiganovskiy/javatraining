package ru.stqa.pft.soap.model;

public class Project {

  public Project withId(int id) {
    this.id = id;
    return this;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }

  public int getId() {

    return id;
  }

  public String getName() {
    return name;
  }

  private int id;
  private String name;
}
