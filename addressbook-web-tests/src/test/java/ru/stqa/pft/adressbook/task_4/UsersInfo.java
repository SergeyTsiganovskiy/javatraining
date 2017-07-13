package ru.stqa.pft.adressbook.task_4;

public class UsersInfo {
  private final String name;
  private final String lastName;
  private final String company;
  private final String text;
  private final String homePhone;
  private final String mobile;
  private final String email;

  public UsersInfo(String name, String lastName, String company, String text, String homePhone, String mobile, String email) {
    this.name = name;
    this.lastName = lastName;
    this.company = company;
    this.text = text;
    this.homePhone = homePhone;
    this.mobile = mobile;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCompany() {
    return company;
  }

  public String getText() {
    return text;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }
}
