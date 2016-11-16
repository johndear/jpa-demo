package one2one;

import one2many.Department;

import javax.persistence.*;

@Entity
public class Address2 {
  @Id
  private int id;

  private String street;

  private String city;

  private String state;

  private String zip;

  @OneToOne(cascade=CascadeType.ALL)
  private Student2 student;

  //    @OneToOne(cascade= CascadeType.ALL, mappedBy="address") // 表示Department2是关系维护方
  @OneToOne (cascade=CascadeType.ALL) // 可以双向（Bidirectional）关联、也可以单向（Unidirectional）关联
  @JoinColumn(name="DEPT_ID", unique= true, nullable=true, insertable=true, updatable=true) // 表示Address2是关系维护方
  private Department2 department;
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String address) {
    this.street = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public Student2 getStudent() {
    return student;
  }

  public void setStudent(Student2 student) {
    this.student = student;
  }


  public Department2 getDepartment() {
    return department;
  }

  public void setDepartment(Department2 department) {
    this.department = department;
  }

  public String toString() {
    return "Address2 id: " + getId() + ", street: " + getStreet() + ", city: " + getCity()
        + ", state: " + getState() + ", zip: " + getZip();
  }

}
