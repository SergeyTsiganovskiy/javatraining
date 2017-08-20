package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id  = Integer.MAX_VALUE;
  @Expose
  @Column(name="firstname")
  private String name;
  @Expose
  @Column(name="lastname")
  private String lastName;
  @Expose
  @Column(name="home")
  @Type(type="text")
  private String homePhone;
  @Expose
  @Column(name="mobile")
  @Type(type="text")
  private String mobilePhone;
  @Expose
  @Column(name="work")
  @Type(type="text")
  private String workPhone;
  @Expose
  @Transient
  private String allPhones;
  @Expose
  @Column(name="email")
  @Type(type="text")
  private String email;
  @Expose
  @Column(name="email2")
  @Type(type="text")
  private String email2;
  @Column(name="email3")
  @Type(type="text")
  private String email3;
  @Transient
  private String allEmails;
  @Expose
  @Column(name="address2")
  @Type(type="text")
  private String address;
  @Column(name="photo")
  @Type(type="text")
  private String photo;

  @Expose
  @ManyToMany (fetch=FetchType.EAGER)
  @JoinTable (name = "address_in_groups",
          joinColumns = @JoinColumn (name="id"), inverseJoinColumns = @JoinColumn (name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }


  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withAdress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
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

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getAdress() {
    return address;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public Groups getGroups() {
    return new Groups(groups);
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email='" + email + '\'' +
            ", adress='" + address + '\'' +
            '}';
  }

  public String getAllPhones() {
    return allPhones;
  }


  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
