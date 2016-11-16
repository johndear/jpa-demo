package one2many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Student {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String name;

  // ������cascade=CascadeType.ALL����student.save()��ʱ��dept = new Dept(),student.setDept(dept),�Ͳ���Ҫִ��em.persist(dept); �ἶ������
  @ManyToOne(cascade=CascadeType.ALL)
  private Department department;

  @OneToMany
  @JoinTable(name="EMP_PHONE",
          joinColumns=@JoinColumn(name="EMP_ID"),
          inverseJoinColumns=@JoinColumn(name="PHONE_ID"))
  private Collection<Phone> phones;



  public Student() {
    phones = new ArrayList<Phone>();
  }

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



  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public void addPhone(Phone phone) {
    if (!getPhones().contains(phone)) {
      getPhones().add(phone);
    }
  }

  public Collection<Phone> getPhones() {
    return phones;
  }


  public String toString() {
    return "\n\nID:" + id + "\nName:" + name + " with " + getPhones().size() + " phones";
  }

}
