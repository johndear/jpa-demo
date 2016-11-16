package one2one;

import one2many.Address;

import javax.persistence.*;

@Entity
public class Student2 {
  @Id
  private int id;

  private String name;

  @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
  //  @OneToOne (cascade=CascadeType.ALL)
//  @JoinColumn(name="DEPT_ID", unique= true, nullable=true, insertable=true, updatable=true)
  private Address2 address2;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address2 getAddress2() {
    return address2;
  }

  public void setAddress(Address2 address2) {
    this.address2 = address2;
  }

  public String toString() {
    return "\n\nID:" + id + "\nName:" + name + "\n\n";
  }
}
