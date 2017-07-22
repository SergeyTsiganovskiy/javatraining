package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String lastName;
  private String group;

  public ContactData(String name, String lastName, String group) {
    this.name = name;
    this.lastName = lastName;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }


  public String getGroup() {
    return group;
  }
}
