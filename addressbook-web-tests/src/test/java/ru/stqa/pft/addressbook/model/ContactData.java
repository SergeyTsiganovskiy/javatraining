package ru.stqa.pft.addressbook.model;

public class ContactData {

  private int id;
  private final String name;
  private final String lastName;
  private String group;

  public ContactData(int id, String name, String lastName, String group) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.group = group;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  public ContactData(String name, String lastName, String group) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.lastName = lastName;
    this.group = group;

  }


  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
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

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }


}
